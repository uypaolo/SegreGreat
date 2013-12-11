package ropamau.dlsu.segregreat;

import java.io.ObjectOutputStream.PutField;

import view.GameView;
import android.app.Activity;
import android.os.Bundle;

public class GameAreaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new GameView(this, getIntent().getIntExtra("number", 1), this));
	}
	
	
	
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_area, menu);
		return true;
	}
*/
}
