package gamemodels;

import java.util.ArrayList;

public class ScoresModel {
	
	public static final String SCORES_PATH = "Levels/highscore.xml";
	
	private ArrayList<Integer> scoreList;
	
	private static ScoresModel instance;
	
	public ScoresModel getInstance(){
		if(instance == null){
			instance = new ScoresModel();
		}
		return instance;
	}
	
	public ScoresModel(){
		initScores();
	}
	
	public void initScores(){
		
	}
	
	public void updateScore(int level, int score){
	}

	public ArrayList<Integer> getScoreList() {
		return scoreList;
	}

	public void setScoreList(ArrayList<Integer> scoreList) {
		this.scoreList = scoreList;
	}
}
