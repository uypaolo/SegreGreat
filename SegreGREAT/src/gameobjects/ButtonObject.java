package gameobjects;

import view.GameView;
import android.graphics.Bitmap;

public class ButtonObject {
	private Bitmap image;
	private int x;
	private int y=-45;
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
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
	public boolean isCollition(float x2, float y2){
		return x2>x && x2<x+image.getWidth() && y2>y && y2<y+image.getHeight();
	}
}
