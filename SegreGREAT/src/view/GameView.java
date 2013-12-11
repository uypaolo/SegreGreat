package view;

import gamemodels.LevelModel;
import gameobjects.BinObject;
import gameobjects.ButtonObject;
import gameobjects.TinCan;
import gameobjects.TrashObject;

import java.util.ArrayList;

import ropamau.dlsu.segregreat.LevelSelectActivity;
import ropamau.dlsu.segregreat.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView{
	private GameLoopThread gameLoop;
	private SurfaceHolder holder;
	private Activity srcActivity;
	private LevelModel currLevel;
	private Bitmap background;
	private Bitmap wall;
	private ButtonObject check;
	private Bitmap scoreWindow;
	private Bitmap head;
	private ButtonObject pause;
	private ButtonObject resume;
	private Bitmap pausedScreen;
	private BinObject bioBin;
	private BinObject nonbioBin;
	private BinObject recycBin;
	private TrashObject selected;
	private int currTime;
	public static int score = 0;
	public static long limit = 60000;
	public static long startTime;
	public static Vibrator v;
	private boolean paused = false;
	private boolean end = false;
	ArrayList<TrashObject> trashList;
	
	public GameView(Context context, int number, Activity src) {
		super(context);
		
		this.srcActivity = src;
		
		limit = 60000;
		
		GameView.v = (Vibrator) srcActivity.getSystemService(Context.VIBRATOR_SERVICE);
		
		holder = getHolder();
		holder.addCallback(new Callback(){

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				gameLoop.setRunning(true);
				gameLoop.start();
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				boolean retry = true;
				score = 0;
				gameLoop.setRunning(false);
				try{
					while(retry){
						gameLoop.join();
						retry = false;
					}
				}catch(InterruptedException e){}
			}
			
		});				
		gameLoop = new GameLoopThread(this);
		
		bioBin = new BinObject();
		bioBin.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.biobin));
		bioBin.setType("biodegradable");
		
		
		nonbioBin = new BinObject();
		nonbioBin.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.nonbiobin));
		nonbioBin.setType("non-biodegradable");
		
		
		recycBin = new BinObject();
		recycBin.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.recyclablebin));
		recycBin.setType("recyclable");
		
		wall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
		head = BitmapFactory.decodeResource(getResources(), R.drawable.head);
		
		pause = new ButtonObject();
		pause.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.button_pause));
		
		resume = new ButtonObject();
		resume.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.button_resume));
		
		pausedScreen = BitmapFactory.decodeResource(getResources(), R.drawable.pause_screen);
		
		scoreWindow = BitmapFactory.decodeResource(getResources(), R.drawable.score_window);
		
		check = new ButtonObject();
		check.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.checkmark));
		
		currLevel = new LevelModel(this, number);
		
		startTime = System.currentTimeMillis();
		
	}

	@Override
	protected void onDraw(Canvas canvas){
		canvas.drawColor(Color.WHITE);
		
        if(trashList == null){
        	trashList = currLevel.generateTrash(this);
        }
        
		//bananaPeel.onDraw(canvas);
		//canvas.drawBitmap(bioBin.getImage(), 10, y, null);
		//canvas.drawBitmap(nonbioBin.getImage(), 10, 50, null);
		//canvas.drawBitmap(recycBin.getImage(), 10, 90, null);
		Paint paint = new Paint(); 
		paint.setColor(Color.WHITE); 
		paint.setTextSize(20); 
		
		int column = this.getWidth()/3;
		
		
		if(selected!=null){
			if(selected.getName().equalsIgnoreCase("tin can")){
				if(((TinCan)selected).isSelected() && !((TinCan)selected).isCrushed()){
					((TinCan)selected).doPressDown();
				}
			}
		}

		for(TrashObject to: trashList){
			if(!to.isDone()){
			to.onDraw(canvas, currLevel.getMultiplier(), this.getHeight());
			}
			//canvas.drawBitmap(to.getImage(), to.getX(), to.getY(), null);
			/*if(trashList.indexOf(to)<=0){
				to.setY(this.getHeight()-to.getImage().getHeight());
				canvas.drawBitmap(to.getImage(), to.getX(), to.getY(), null);
			}
			else{
				to.setY(trashList.get(trashList.indexOf(to)-1).getY()-to.getImage().getHeight());
				canvas.drawBitmap(to.getImage(), to.getX(),to.getY(), null);
			}*/
		}
		
		check.setX(10);
		check.setY(400);
		
		bioBin.setX((column/2)-(bioBin.getImage().getWidth()/2));
		bioBin.setY(this.getHeight()-bioBin.getImage().getHeight());
		
		nonbioBin.setX((column+(column/2))-(nonbioBin.getImage().getWidth()/2));
		nonbioBin.setY(this.getHeight()-nonbioBin.getImage().getHeight());
		
		recycBin.setX(((column*2)+(column/2))-(recycBin.getImage().getWidth()/2));
		recycBin.setY(this.getHeight()-recycBin.getImage().getHeight());
		
		pause.setX(430);
		pause.setY(5);
		
		canvas.drawBitmap(wall, 0, this.getHeight()-wall.getHeight(), null);
		canvas.drawBitmap(head, 0, 0, null);
		canvas.drawBitmap(bioBin.getImage(), bioBin.getX(), bioBin.getY(), null);
		canvas.drawBitmap(nonbioBin.getImage(), nonbioBin.getX(), nonbioBin.getY(), null);
		canvas.drawBitmap(recycBin.getImage(), recycBin.getX(), recycBin.getY(), null);
		
		long temp = (limit-(System.currentTimeMillis()-startTime))/1000;
		long currTime = 0;
		if(temp>=0){		 
			currTime = temp;
		}
		
		canvas.drawText(""+currTime, (this.getWidth()/2)-5, 25, paint); 
		canvas.drawText(""+score, 30, 30, paint); 
				
		//canvas.drawText(""+tin, 60, 30, paint); 
		
		canvas.drawBitmap(pause.getImage(), pause.getX(), pause.getY(), null);
		
		if(paused){
			resume.setX((this.getWidth()/2)-(resume.getImage().getWidth()/2));
			resume.setY((this.getHeight()/2)-(resume.getImage().getHeight()/2));
			
			canvas.drawBitmap(pausedScreen, 0, 0, null);
			canvas.drawBitmap(resume.getImage(), resume.getX(), resume.getY(), null);
			//canvas.drawText(""+limit, 30, 30, paint); 
			
			this.gameLoop.setPaused(true);
		}
		
		if(currTime<=0){ 
			paint.setColor(Color.BLACK); 
			paint.setTextSize(60);
			canvas.drawBitmap(scoreWindow, 0, 0, null);
			canvas.drawText("SCORE", (this.getWidth()/2)-90, 260, paint); 
			String a = ""+score;
			//int finalScore = Integer.parseInt(a);
			canvas.drawText(a, (this.getWidth()/2), 340, paint); 
			canvas.drawBitmap(check.getImage(), check.getX(), check.getY(), null);
			this.end = true;
			gameLoop.setRunning(false);
		}
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		
		

		if(bioBin.isCollition(event.getX(), event.getY())){
			if(selected!=null){
				
				selected.deselect();
				
				if(selected.getType().equals(bioBin.getType())){					
					score+=selected.getScore();		
				}
				else{
					score-=10;
					v.vibrate(200);
				}
				selected.setDone(true);
				selected = null;
			}
		}
		else if(nonbioBin.isCollition(event.getX(), event.getY())){
			if(selected!=null){
				
				selected.deselect();
				
				if(selected.getType().equals(nonbioBin.getType())){
					
					score+=selected.getScore();
				}
				else{
					score-=10;
					v.vibrate(200);
				}
				selected.setDone(true);
				selected = null;
			}
		}
		else if(recycBin.isCollition(event.getX(), event.getY())){
			if(selected!=null){
				
				selected.deselect();
				
				if(selected.getType().equals(recycBin.getType())){
					
					score+=selected.getScore();
					
				}
				else{
					score-=10;
					v.vibrate(200);
				}
				selected.setDone(true);
				selected = null;
			}
		}
		else if(resume.isCollition(event.getX(), event.getY())){
			this.paused = false;
			this.gameLoop.setPaused(false);
		}
		else if(pause.isCollition(event.getX(), event.getY())){
			this.paused = true;
			limit = limit-(System.currentTimeMillis()-startTime);
			startTime = System.currentTimeMillis();
		}
		
		else if(check.isCollition(event.getX(), event.getY()) && end){
			LevelSelectActivity.reloadScores(currLevel.getNumber(), score);
			srcActivity.finish();
		}
		else{
			for(TrashObject to: trashList){
				if(to.isCollition(event.getX(), event.getY())){
					if(selected!=null){
						selected.deselect();
					}
					
					selected = to;
					
					if(selected.getName().equalsIgnoreCase("tin can")){
							if (event.getAction()==MotionEvent.ACTION_DOWN){
								//this.tin = "Tincan";
						        ((TinCan)selected).selectThis();          
						    }
						    else if (event.getAction()==MotionEvent.ACTION_UP){
						    	//this.tin = "Nag up naman ah";
						        //((TinCan)selected).doPressRelease();
						    }
						
					}
							
					to.select();
								
				}
			}
		}
		return true;

	}
	
	public int getResourceID(String name){
		return getResources().getIdentifier(name, "drawable", this.getContext().getPackageName());
	}
	
	public Bitmap getResourceBitmap(int rID){
		return BitmapFactory.decodeResource(getResources(), rID);
	}
	
}
