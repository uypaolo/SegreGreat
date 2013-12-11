package gameobjects;

import view.GameView;

public class PlasticCup extends TrashObject{
	private int stackLimit = 5;
	private int currStack = 1;
	private boolean selected = false;
	
	public void selectThis(){
		if(!isSelected()){
			selectTime = System.currentTimeMillis();
		}
		this.setSelected(true);
	}
	
	public void deslectThis(){
		
	}
	
	public void stack(int stack2){
		//GameView.tin = ""+(System.currentTimeMillis()-selectTime);
		
		if(getCurrStack()<stackLimit){		
			if(stack2>currImage){
				currStack = stack2;
			}
			currImage = ((currStack-1)*2);
			setImage(states.get(currImage+1));
			setScore((int)(8*(1+(.2*getCurrStack()))));
			GameView.v.vibrate(200);
		
		}
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getCurrStack() {
		return currStack;
	}

	public void setCurrStack(int currStack) {
		this.currStack = currStack;
	}
}
