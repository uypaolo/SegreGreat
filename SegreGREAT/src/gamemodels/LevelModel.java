package gamemodels;

import gameobjects.PlasticCup;
import gameobjects.TinCan;
import gameobjects.TrashObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jdom2.Element;

import view.GameView;
import File.XMLReader;
import android.graphics.Bitmap;

public class LevelModel {
	public static final String LEVELS_PATH = "Levels/";
	
	private int number;
	private int unlocked;
	private int nextLevel;
	private float multiplier;
	private String name;
	private Bitmap background;
	private ArrayList<TrashObject> trashList;
	private XMLReader xmlReader;
	private GameView gameView;
	private TrashListModel listModel;
	
	public LevelModel(GameView gw, int number){
		this.xmlReader = new XMLReader(gw);
		this.listModel = new TrashListModel(gw);
		this.trashList = new ArrayList<TrashObject>();
		this.gameView = gw;
		initLevel(number);
	}
	
	public void initLevel(int number){
		Element root = this.xmlReader.readXML(LEVELS_PATH+""+number+".xml");
		setName(root.getChild("name").getAttributeValue("value"));
		setNumber(Integer.parseInt(root.getChild("number").getAttributeValue("value")));
		setUnlocked(Integer.parseInt(root.getChild("unlocked").getAttributeValue("value")));
		setNextLevel(Integer.parseInt(root.getChild("nextlevel").getAttributeValue("value")));
		setMultiplier(Float.parseFloat(root.getChild("multiplier").getAttributeValue("value")));
		List<Element> trashNames = root.getChild("trash").getChildren();
		for(Element tn: trashNames){
			this.trashList.add(this.listModel.getTrash(tn.getAttributeValue("value")));
		}
		
		
	}
	
	public ArrayList<TrashObject> generateTrash(GameView gw){
		ArrayList<TrashObject> randTrash =  new ArrayList<TrashObject>();
		
		Random r = new Random();
		int column = gw.getWidth()/3;
		int yLocation;
		
		int trashNum = 40*number;
		
		for(int counter = 0 ; counter<trashNum; counter++){
			
			int i = r.nextInt(trashList.size());
			int columnF = (column*(r.nextInt(3)))+(column/2);
			if(randTrash.size()<=0){
				yLocation = -45;
			}
			else{
				yLocation = randTrash.get(randTrash.size()-1).getY();
			}
			
			TrashObject temp = trashList.get(i);
			TrashObject to;
			
			if(temp.getName().equalsIgnoreCase("tin can")){
				to = new TinCan();
			}
			else if(temp.getName().equalsIgnoreCase("plastic cup")){
				to = new PlasticCup();
			}
			else{
				to = new TrashObject();
			}
			
			to.setName(temp.getName());
			ArrayList<Bitmap> tList = new ArrayList<Bitmap>();
			for(Bitmap b:temp.getStates()){
				tList.add(b);
			}
			to.setStates(tList);
			to.setType(temp.getType());
			to.setySpeed(temp.getySpeed());
			to.setScore(temp.getScore());
			to.setY(yLocation - (to.getImage().getHeight()+20));
			to.setX(columnF-(to.getImage().getWidth()/2));
	
			randTrash.add(to);
		}
		return randTrash;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int isUnlocked() {
		return unlocked;
	}
	
	public void setUnlocked(int unlocked) {
		this.unlocked = unlocked;
	}
	
	public int getNextLevel() {
		return nextLevel;
	}
	
	public void setNextLevel(int nextLevel) {
		this.nextLevel = nextLevel;
	}
	
	public float getMultiplier() {
		return multiplier;
	}
	
	public void setMultiplier(float multiplier) {
		this.multiplier = multiplier;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Bitmap getBackground() {
		return background;
	}
	
	public void setBackground(Bitmap background) {
		this.background = background;
	}

	public ArrayList<TrashObject> getTrashList() {
		return trashList;
	}

	public void setTrashList(ArrayList<TrashObject> trashList) {
		this.trashList = trashList;
	}
	
}
