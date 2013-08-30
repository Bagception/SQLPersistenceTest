package de.philipphock.android.sqlpersistencetest.db;

import android.content.Context;

import com.commonsware.cwac.loaderex.SQLiteCursorLoader;

public class LoaderHelper {

	private DatabaseHelper dbhelper;
	
	public LoaderHelper(Context context) {
		dbhelper = new DatabaseHelper(context);
	}
	
	public SQLiteCursorLoader getTodoLoader(Context context){
		return new SQLiteCursorLoader(context,dbhelper,TodoTable.QUERIES.GETALLENTRIES,null);
	}
}
