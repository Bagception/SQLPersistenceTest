package de.philipphock.android.sqlpersistencetest.activities;

import de.philipphock.android.sqlpersistencetest.R;
import de.philipphock.android.sqlpersistencetest.R.layout;
import de.philipphock.android.sqlpersistencetest.R.menu;
import de.philipphock.android.sqlpersistencetest.data.TodoElement;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class CreateNewItem extends Activity {
	public static final String RESULT_TODONAME = "result";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_new_item, menu);
		return true;
	}
	
	public void onOk(View v) {
		String text = ((TextView)findViewById(R.id.todoname)).getText().toString();
		TodoElement newEntry = new TodoElement(text);
		
		Intent returnIntent = new Intent();
		returnIntent.putExtra(RESULT_TODONAME,text);
		setResult(RESULT_OK,returnIntent);     
		finish();
	}

}
