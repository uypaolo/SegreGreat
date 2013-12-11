package gameobjects;

import java.util.ArrayList;

import view.GameView;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class TrashObject {
	protected ArrayList<Bitmap> states;
	private Bitmap image;
	private String type;
	private String name;
	private GameView gameView;
	private boolean done = false;
	private int score;
	private int ySpeed;
	private int x;
	private int y=-45;
	protected int currImage = 0;
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	protected long selectTime;
	
	
	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public TrashObject(){

	}
	
	private void update(float multiplier, int height){
		if(y < height-130){
			y+=(ySpeed*multiplier);
		}
		else{
			GameView.score-=2;
			this.done = true;
		}
	}
	
	public void onDraw(Canvas canvas, float multiplier, int height){
		update(multiplier, height);
		canvas.drawBitmap(image, x, y, null);
	}
	
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isCollition(float x2, float y2){
		return x2>x && x2<x+image.getWidth() && y2>y && y2<y+image.getHeight();
	}

	public ArrayList<Bitmap> getStates() {
		return states;
	}

	public void setStates(ArrayList<Bitmap> states) {
		this.states = states;
		this.image = this.states.get(0);
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
	public void select(){
		this.image = states.get(currImage+1);
		
	}
	
	public void deselect(){
		this.image = states.get(currImage);
		
	}
}
