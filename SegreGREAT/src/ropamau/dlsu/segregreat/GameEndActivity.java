package ropamau.dlsu.segregreat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class GameEndActivity extends Activity {
	
	private ImageView homeButton;
	private ImageView repeatButton;
	private ImageView nextLevelButton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_end);
		
		homeButton = (ImageView) findViewById(R.id.home);
		repeatButton = (ImageView) findViewById(R.id.next_level);
		nextLevelButton = (ImageView) findViewById(R.id.repeat);
		
		
		homeButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GameEndActivity.this, LevelSelectActivity.class);
				startActivity(intent);
			}			
		});
		
		repeatButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GameEndActivity.this, GameAreaActivity.class);
				startActivity(intent);
			}		
		});
		
		nextLevelButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GameEndActivity.this, GameAreaActivity.class);
				startActivity(intent);
			}			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_end, menu);
		return true;
	}

}
