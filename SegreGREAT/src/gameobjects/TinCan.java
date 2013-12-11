package gameobjects;

import view.GameView;

public class TinCan extends TrashObject{
	private boolean crushed = false;
	private long crushTime = 2;
	private boolean selected = false;
	
	
	public void selectThis(){
		if(!isSelected()){
			selectTime = System.currentTimeMillis();
		}
		else{
			if(!crushed){
				crushTime--;
			}
		}
		this.setSelected(true);
	}
	
	public void deslectThis(){
		
	}
	
	public void doPressDown(){
		//GameView.tin = ""+(System.currentTimeMillis()-selectTime);
		
		if(crushTime<=0 && !crushed){
		
			this.crushed = true;
			currImage = 2;
			setImage(states.get(currImage+1));
			setScore(18);
			GameView.v.vibrate(200);
		
		}
	}
	
	/*public void doPressRelease(){
		this.setSelected(false);
		crushTime = crushTime-(System.currentTimeMillis()-selectTime);
		
	}*/

	public boolean isCrushed() {
		return crushed;
	}

	public void setCrushed(boolean crushed) {
		this.crushed = crushed;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
