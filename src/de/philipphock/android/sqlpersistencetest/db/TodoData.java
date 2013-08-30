package de.philipphock.android.sqlpersistencetest.db;

import de.philipphock.android.sqlpersistencetest.data.TodoElement;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TodoData {
	
	private DatabaseHelper dbhelper;
	private SQLiteDatabase db;
	
	public TodoData(Context context) {
		dbhelper = new DatabaseHelper(context);
	}
	
	
	
	public void openWrite(){
		db = dbhelper.getWritableDatabase();
	}
	public void close(){
		dbhelper.close();
	}
	public TodoElement createTodo(String text){
		ContentValues values = new ContentValues();
		values.put(TodoTable.COLUMN_TEXT, text);
		long id = db.insert(TodoTable.TABLE_TODO, null, values);
		
	}
	
	private TodoElement getElement(long id) throws NoDBEntryFoundException{
		Cursor cursor = db.query(TodoTable.TABLE_TODO,TodoTable.allColumns,TodoTable.COLUMN_ID + " = " + id,null,null,null,null,"1");
		if (cursor.moveToFirst()){
			TodoElement ret = cursorToElement(cursor);	
			return ret;
		}else{
			throw new NoDBEntryFoundException();
		}
				
				
	}
	
	private TodoElement cursorToElement(Cursor c){
		long id = c.getLong(0);
		String text = c.getString(1);
		TodoElement ret = new TodoElement(id,text);
		return ret;
	}
	
	
}
