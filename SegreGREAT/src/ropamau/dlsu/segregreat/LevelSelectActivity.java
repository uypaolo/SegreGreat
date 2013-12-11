package ropamau.dlsu.segregreat;

import java.util.ArrayList;

import File.DBModule;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class LevelSelectActivity extends Activity {

	private ImageView level1;
	private ImageView level2;
	private ImageView level3;
	
	private static TextView hw1;
	private static TextView hw2;
	private static TextView hw3;
	
	public static DBModule scoresDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_select);
		
		scoresDB = new DBModule(this);
		
		ArrayList<Integer> hs = scoresDB.getHighscore();
		
		level1 = (ImageView) findViewById(R.id.level1);
		level2 = (ImageView) findViewById(R.id.level2);
		level3 = (ImageView) findViewById(R.id.level3);
		
		hw1 = (TextView) findViewById(R.id.hw1);
		hw2 = (TextView) findViewById(R.id.hw2);
		hw3 = (TextView) findViewById(R.id.hw3);
		
		hw1.setTextColor(Color.RED);
		hw2.setTextColor(Color.RED);
		hw3.setTextColor(Color.RED);
		
		hw1.setText(""+hs.get(0));
		hw2.setText(""+hs.get(1));
		hw3.setText(""+hs.get(2));
		
		level1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LevelSelectActivity.this, GameAreaActivity.class);
				intent.putExtra("number", 1);
				startActivity(intent);
			}			
		});
		
		level2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LevelSelectActivity.this, GameAreaActivity.class);
				intent.putExtra("number", 2);
				startActivity(intent);
			}		
		});
		
		level3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LevelSelectActivity.this, GameAreaActivity.class);
				intent.putExtra("number", 3);
				startActivity(intent);
			}			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level_select, menu);
		return true;
	}
	
	public static void reloadScores(int level, int score){
		
		int prev = scoresDB.getScore(level);
		
		if(prev<score){
			scoresDB.setHighscore(level, score);
		}
		
		ArrayList<Integer> hs = scoresDB.getHighscore();
		
		hw1.setText(""+hs.get(0));
		hw2.setText(""+hs.get(1));
		hw3.setText(""+hs.get(2));
	}
	

}
