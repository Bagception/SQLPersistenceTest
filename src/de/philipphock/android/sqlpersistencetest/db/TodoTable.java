package de.philipphock.android.sqlpersistencetest.db;

import android.database.sqlite.SQLiteDatabase;

public class TodoTable {

	  // Database table
	  public static final String TABLE_TODO = "todo";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_TEXT = "text";
	  public static final String[] allColumns = new String[]{COLUMN_ID,COLUMN_TEXT};
	  
	private static final String DATABASE_CREATE = "CREATE TABLE "
		+ TABLE_TODO
		+ "("
		+ COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ COLUMN_TEXT + " TEXT NOT NULL );";
		
	
	public static void onCreate(SQLiteDatabase database){
		database.execSQL(DATABASE_CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
		onCreate(database);
	}
	
	interface QUERIES{
		String GETALLENTRIES = "SELECT " + COLUMN_ID + ", " + COLUMN_TEXT + " FROM " + TABLE_TODO+";";
		
	}
}
