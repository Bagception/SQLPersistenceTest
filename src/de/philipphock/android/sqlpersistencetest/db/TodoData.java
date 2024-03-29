package de.philipphock.android.sqlpersistencetest.db;

import java.util.ArrayList;

import de.philipphock.android.sqlpersistencetest.data.TodoElement;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TodoData {
	
	private DatabaseHelper dbhelper;
	private SQLiteDatabase db;
	
	public TodoData(Context context) {
		dbhelper = new DatabaseHelper(context);
	}
	public void openRead(){
		Log.d("db","openRead");
		db = dbhelper.getReadableDatabase();
	}
	
	
	
	public void openWrite(){
		Log.d("db","openWrite");
		db = dbhelper.getWritableDatabase();
	}
	public void close(){
		Log.d("db","close");
		dbhelper.close();
	}
	public TodoElement createTodo(String text) throws ErrorCreatingItem{
		ContentValues values = new ContentValues();
		values.put(TodoTable.COLUMN_TEXT, text);
		long id = db.insert(TodoTable.TABLE_TODO, null, values);
		TodoElement ret;
		try {
			ret = getElement(id);
		} catch (NoDBEntryFoundException e) {
			e.printStackTrace();
			throw new ErrorCreatingItem();
		}
		return ret;
	}
	
	
	public ArrayList<TodoElement> getAllTodoElements() throws NoDBEntryFoundException{
		ArrayList<TodoElement> ret = new ArrayList<TodoElement>();
		Cursor cursor = db.query(TodoTable.TABLE_TODO,TodoTable.allColumns,null,null,null,null,null,null);
		if (cursor.moveToFirst()){
			while(!cursor.isAfterLast()){
				ret.add(cursorToElement(cursor));
				cursor.moveToNext();
			}				
			return ret;
		}else{
			throw new NoDBEntryFoundException();
		}
		
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
	
	public void deleteElement(TodoElement e){
		db.delete(TodoTable.TABLE_TODO, "_id = "+e.getId(), null);
	}
	
	private TodoElement cursorToElement(Cursor c){
		long id = c.getLong(0);
		String text = c.getString(1);
		TodoElement ret = new TodoElement(id,text);
		return ret;
	}
	
	
}
