package File;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBModule extends SQLiteOpenHelper{

	public static final String TABLE_SCORES = "scores";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_LEVEL = "level";
	public static final String COLUMN_SCORE = "score";

	private static final String DATABASE_NAME = "scores.db";
	private static int DATABASE_VERSION = 1;

	  // Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
	      + TABLE_SCORES + "(" + COLUMN_ID
	      + " integer primary key autoincrement, " + COLUMN_LEVEL
	      + " integer not null, " + COLUMN_SCORE
	      + " integer not null);";

	private Object ourDatabase;

	
	public DBModule(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
		for(int i = 0; i < 3; i++){
			int level = i+1;
			db.execSQL("INSERT INTO " + TABLE_SCORES +
					"(" + COLUMN_LEVEL +", " + COLUMN_SCORE + ") VALUES (" + level + ", " + 0 + ");");	
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
		DATABASE_VERSION = newV;
		onCreate(db);
	}
	
	public ArrayList<Integer> getHighscore(){
		ArrayList<Integer> highscores = new ArrayList<Integer>();
		
		String[] columns = new String[]{COLUMN_LEVEL, COLUMN_SCORE};
		Cursor c = this.getWritableDatabase().query(TABLE_SCORES, columns,  null, null, null, null, null);
		
		int iRow = c.getColumnIndex(COLUMN_SCORE);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			highscores.add(c.getInt(iRow));
		}
		
		return highscores;
	}

	public void setHighscore(int level, int score){
		
		this.getWritableDatabase().execSQL("UPDATE " + TABLE_SCORES 
				+ " SET " + COLUMN_SCORE + " = " + score + " WHERE " 
				+ COLUMN_LEVEL + " = " + level + ";");
		
	}
	
	public int getScore(int level){
		String[] columns = new String[]{COLUMN_LEVEL, COLUMN_SCORE};
		Cursor c = this.getWritableDatabase().query(TABLE_SCORES, columns, COLUMN_LEVEL + " = " + level + "", null, null, null, null);
		
		int iRow = c.getColumnIndex(COLUMN_SCORE);
		
		int index = 0;
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			index = c.getInt(iRow);
		}
		
		
		return index;
	}

}
