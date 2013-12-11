package gamemodels;

import gameobjects.PlasticCup;
import gameobjects.TinCan;
import gameobjects.TrashObject;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import view.GameView;
import File.XMLReader;
import android.graphics.Bitmap;

public class TrashListModel {
	
	public static final String TRASH_LIST_PATH = "Trash/TrashList.xml";
	
	private ArrayList<TrashObject> trashList;
	private XMLReader xmlReader;
	private GameView gameView;
	
	private static TrashListModel instance;
	
	public TrashListModel getInstance(GameView gw){
		if(instance == null){
			instance = new TrashListModel(gw);
		}
		return instance;
	}
	
	public TrashListModel(GameView gw){
		this.xmlReader = new XMLReader(gw);
		this.gameView = gw;
		initTrashList();
	}
	
	private void initTrashList(){
		this.setTrashList(new ArrayList<TrashObject>());
		Element root = this.xmlReader.readXML(TRASH_LIST_PATH);
		List<Element> list = root.getChildren();
		for(Element trash: list){
			String name = trash.getAttributeValue("name");
			String type = trash.getAttributeValue("type");
			int speed = Integer.parseInt(trash.getChild("speed").getAttributeValue("value"));
			int score = Integer.parseInt(trash.getChild("score").getAttributeValue("value"));
			List<Element> states = trash.getChild("states").getChildren();
			ArrayList<Bitmap> stateList = new ArrayList<Bitmap>();
			if(name.equalsIgnoreCase("tin can")){
				TinCan ro = new TinCan();
				ro.setName(name);
				ro.setType(type);
				ro.setySpeed(speed);
				ro.setScore(score);
				for(Element state: states){
					stateList.add(gameView.getResourceBitmap(gameView.getResourceID(state.getAttributeValue("dir"))));
				}
				ro.setStates(stateList);
				this.getTrashList().add(ro);
			}
			else if(name.equalsIgnoreCase("plastic cup")){
				PlasticCup ro = new PlasticCup();
				ro.setName(name);
				ro.setType(type);
				ro.setySpeed(speed);
				ro.setScore(score);
				for(Element state: states){
					stateList.add(gameView.getResourceBitmap(gameView.getResourceID(state.getAttributeValue("dir"))));
				}
				ro.setStates(stateList);
				this.getTrashList().add(ro);
			}
			else{
				TrashObject ro = new TrashObject();
				ro.setName(name);
				ro.setType(type);
				ro.setySpeed(speed);
				ro.setScore(score);
				for(Element state: states){
					stateList.add(gameView.getResourceBitmap(gameView.getResourceID(state.getAttributeValue("dir"))));
				}
				ro.setStates(stateList);
				this.getTrashList().add(ro);
			}
		}
		
	}

	public ArrayList<TrashObject> getTrashList() {
		return trashList;
	}

	public void setTrashList(ArrayList<TrashObject> trashList) {
		this.trashList = trashList;
	}
	
	public TrashObject getTrash(String name){
		TrashObject to = null;
		for(TrashObject t: this.trashList){
			if(t.getName().equals(name)){
				if(name.equalsIgnoreCase("tin can")){
					to = (TinCan)t;
				}
				else if(name.equalsIgnoreCase("plastic cup")){
					to = (PlasticCup)t;
				}
				else{
					to = t;
				}
			}
		}
		return to;
	}
	
}
