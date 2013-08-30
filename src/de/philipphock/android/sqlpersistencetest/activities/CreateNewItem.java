package de.philipphock.android.sqlpersistencetest.activities;

import de.philipphock.android.sqlpersistencetest.R;
import de.philipphock.android.sqlpersistencetest.R.layout;
import de.philipphock.android.sqlpersistencetest.R.menu;
import de.philipphock.android.sqlpersistencetest.data.TodoElement;
import de.philipphock.android.sqlpersistencetest.db.ErrorCreatingItem;
import de.philipphock.android.sqlpersistencetest.db.TodoData;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
 
public class CreateNewItem extends Activity {
	public static final String RESULT_TODONAME = "result";
	private TodoData todoData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_item);
		todoData = new TodoData(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_new_item, menu);
		return true;
	}
	
	public void onOk(View v) {
		String status = "";
		int resultCode = RESULT_CANCELED;
		String text = ((TextView)findViewById(R.id.todoname)).getText().toString();
		try {
			todoData.openWrite();
			//TodoElement newEntry = 
			todoData.createTodo(text);
			todoData.close();
			status = "item created";
			resultCode = RESULT_OK;
		} catch (ErrorCreatingItem e) {
			resultCode = RESULT_CANCELED;
			status = "could not create item";
			e.printStackTrace();
		}
		
		Intent returnIntent = new Intent();
		returnIntent.putExtra(RESULT_TODONAME,status);
		setResult(resultCode,returnIntent);     
		finish();
	}

}
