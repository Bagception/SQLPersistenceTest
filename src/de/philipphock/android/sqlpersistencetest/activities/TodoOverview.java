package de.philipphock.android.sqlpersistencetest.activities;

import java.util.List;

import com.commonsware.cwac.loaderex.SQLiteCursorLoader;

import de.philipphock.android.sqlpersistencetest.R;
import de.philipphock.android.sqlpersistencetest.adapter.TodoListAdapter;
import de.philipphock.android.sqlpersistencetest.data.TodoElement;
import de.philipphock.android.sqlpersistencetest.db.LoaderHelper;
import de.philipphock.android.sqlpersistencetest.db.NoDBEntryFoundException;
import de.philipphock.android.sqlpersistencetest.db.TodoData;
import de.philipphock.android.sqlpersistencetest.db.TodoTable;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class TodoOverview extends ListActivity implements LoaderCallbacks<Cursor>{

	public static final int REQUEST_CREATENEW=0;
	
	private LoaderHelper loaderHelper;
	
	private SimpleCursorAdapter todoCursorAdapter;
	
	private SQLiteCursorLoader loader;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		//getListView()savedInstanceState.//setOnItemLongClickListener(this);
		loaderHelper = new LoaderHelper(this);
		registerForContextMenu(getListView());
		fillData();
	}
	
	

	

	private void fillData() {
		String[] from = new String[] {TodoTable.COLUMN_TEXT};
		int[] to = new int[] {android.R.id.text1};
		getLoaderManager().initLoader(0, null, this);
		todoCursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,null,from,to,0);
		setListAdapter(todoCursorAdapter);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Delete Item");
		menu.add(0,0,0,"yes");
		menu.add(0,1,0,"no");
	
	}
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		if (item.getItemId()==0){
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
			//todoData.deleteElement(model.get(info.position));
			//loadData();

		}
		return super.onContextItemSelected(item);

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		//todoData.openWrite();
		//loadData();
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent createNewIntent = new Intent(this,CreateNewItem.class);
		startActivityForResult(createNewIntent,REQUEST_CREATENEW);
		return super.onOptionsItemSelected(item);
	}
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CREATENEW){
			String todoname = data.getStringExtra(CreateNewItem.RESULT_TODONAME);
			Toast.makeText(TodoOverview.this, "CREATED: "+todoname,
			        Toast.LENGTH_SHORT).show();
		}
	}


	@Override
	protected void onPause() {
		super.onPause();
		//todoData.close();

	}
	@Override
	protected void onStop() {
		super.onStop();
	}



	//########### loader callbacks ######################/
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		loader = loaderHelper.getTodoLoader(this);
		return loader;
	}



	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		todoCursorAdapter.changeCursor(arg1);
	}



	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		todoCursorAdapter.changeCursor(null);
	}
	//########### /loader callbacks ######################/
}
