package de.philipphock.android.sqlpersistencetest.activities;

import java.util.List;

import de.philipphock.android.sqlpersistencetest.R;
import de.philipphock.android.sqlpersistencetest.adapter.TodoListAdapter;
import de.philipphock.android.sqlpersistencetest.data.TodoElement;
import de.philipphock.android.sqlpersistencetest.db.NoDBEntryFoundException;
import de.philipphock.android.sqlpersistencetest.db.TodoData;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class TodoOverview extends ListActivity implements OnItemLongClickListener{

	public static final int REQUEST_CREATENEW=0;
	private TodoData todoData;
	private BaseAdapter todoListAdapter;
	private List<TodoElement> model;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		//getListView()savedInstanceState.//setOnItemLongClickListener(this);
		todoData = new TodoData(this);
		registerForContextMenu(getListView());
	}
	
	
	
	private void loadData(){
		try {
			model = todoData.getAllTodoElements();
		} catch (NoDBEntryFoundException e) {
			e.printStackTrace();
		}
		
		todoListAdapter = new TodoListAdapter(this,model);
		
		setListAdapter(todoListAdapter);
		todoListAdapter.notifyDataSetChanged();
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
			todoData.deleteElement(model.get(info.position));
			loadData();

		}
		return super.onContextItemSelected(item);

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		todoData.openWrite();
		loadData();
		
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
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		Log.d("delete",model.get(arg2).getText());
		return false;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		todoData.close();

	}
	@Override
	protected void onStop() {
		super.onStop();
	}
}
