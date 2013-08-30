package de.philipphock.android.sqlpersistencetest.db;

import android.database.sqlite.SQLiteDatabase;

public class TodoTable {

	  // Database table
	  public static final String TABLE_TODO = "todo";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_CATEGORY = "category";
	  public static final String COLUMN_SUMMARY = "summary";
	  public static final String COLUMN_DESCRIPTION = "description";
	  
	  
	private static final String DATABASE_CREATE = "CREATE TABLE"
		+ TABLE_TODO
		+ "("
		+ COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ COLUMN_CATEGORY + " TEXT NOT NULL, "
		+ COLUMN_SUMMARY + " TEXT NOT NULL, "
		+ COLUMN_DESCRIPTION + " TEXT NOT NULL );";
		
	
	public static void onCreate(SQLiteDatabase database){
		database.execSQL(DATABASE_CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
		onCreate(database);
	}
}
