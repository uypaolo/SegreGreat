package gameobjects;

import android.graphics.Bitmap;

public class BinObject {
	private Bitmap image;
	private String type;
	private String name;
	private int x, y;
	
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
}
