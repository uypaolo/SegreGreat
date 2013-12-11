package view;

import android.graphics.Canvas;


public class GameLoopThread extends Thread{
	private GameView gameView;
	private boolean running = false;
	private boolean paused = false;
	private long FPS = 10;
	
	public GameLoopThread(GameView gv){
		this.gameView = gv;
	}
	
	public void setRunning(boolean run){
		this.running = run;
	}
	
	@Override
	public void run(){
		long ticksPS = 1000/FPS;
		long startTime;
		long sleepTime;
		while(running){
			if(!isPaused()){
				Canvas c = null;
				startTime = System.currentTimeMillis();
				try{
					c = gameView.getHolder().lockCanvas();
					
					synchronized(gameView.getHolder()){
						if(c!=null){
							gameView.onDraw(c);
						}
					}
				}finally{
					if(c!=null){
						gameView.getHolder().unlockCanvasAndPost(c);
					}
				}
				
				sleepTime = ticksPS - (System.currentTimeMillis()-startTime);
				
				try{
					if(sleepTime>0){
						sleep(sleepTime);
					}
					else{
						sleep(10);
					}
				}catch(Exception e){}
			}
			else{
				GameView.startTime = System.currentTimeMillis();
			}
		}
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
