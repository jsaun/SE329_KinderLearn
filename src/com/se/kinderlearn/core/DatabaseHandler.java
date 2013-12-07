package com.se.kinderlearn.core;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;
import android.util.Log;

public class DatabaseHandler {
	private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "kinderlearn_db";
    private static final String DATABASE_TABLE_NAME = "score";
    
	class DatabaseHelper extends SQLiteOpenHelper {

	    private static final String SCORES_TABLE_CREATE =
	                "CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
	                "value" + " INTEGER, " +
	                "grade" + " TEXT, " +
	                "date" + " TEXT);";

	    DatabaseHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SCORES_TABLE_CREATE);		
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}

	}
	DatabaseHelper dbh;
	
	public DatabaseHandler(Context context){
		dbh = new DatabaseHelper(context);
	}
	
	boolean saveHighScore(int score, String grade){
		Time now = new Time();
		now.setToNow();
		SQLiteDatabase db = dbh.getWritableDatabase();
		Log.v("blah", ""+now.monthDay);
		String sql = "INSERT INTO " + DATABASE_TABLE_NAME + " " +
				"(value, grade, date) VALUES ( " + score + ", " + grade + "," + now.monthDay + " )";
		db.execSQL(sql);
		db.close();
		return true;
	}
	
	//Change void to some way to store the results.
	void getHighScores(String grade){
		SQLiteDatabase db = dbh.getReadableDatabase();
		String sql = "SELECT * FROM scores";
		Cursor c = db.rawQuery(sql, null);
		Log.v("Blah", "" + c.getColumnCount());
		db.close();
	}
}
