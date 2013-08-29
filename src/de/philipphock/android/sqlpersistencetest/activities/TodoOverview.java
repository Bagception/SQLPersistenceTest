package de.philipphock.android.sqlpersistencetest.activities;

import java.util.ArrayList;
import java.util.List;

import de.philipphock.android.sqlpersistencetest.R;
import de.philipphock.android.sqlpersistencetest.adapter.TodoListAdapter;
import de.philipphock.android.sqlpersistencetest.data.TodoElement;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.BaseAdapter;

public class TodoOverview extends ListActivity {

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

}
