package de.philipphock.android.sqlpersistencetest.activities;

import java.util.ArrayList;
import java.util.List;

import de.philipphock.android.sqlpersistencetest.R;
import de.philipphock.android.sqlpersistencetest.adapter.TodoListAdapter;
import de.philipphock.android.sqlpersistencetest.data.TodoElement;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.Toast;

public class TodoOverview extends ListActivity {

	public static final int REQUEST_CREATENEW=0;
	
	private BaseAdapter todoListAdapter;
	private List<TodoElement> model;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		model = new ArrayList<TodoElement>(5);
		for (int i=0;i<5;i++)
			model.add(new TodoElement("Element"+i));
		
		todoListAdapter = new TodoListAdapter(this,model);
		
		setListAdapter(todoListAdapter);
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
}
