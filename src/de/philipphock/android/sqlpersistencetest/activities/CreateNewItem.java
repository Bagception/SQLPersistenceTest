package de.philipphock.android.sqlpersistencetest.activities;

import de.philipphock.android.sqlpersistencetest.R;
import de.philipphock.android.sqlpersistencetest.R.layout;
import de.philipphock.android.sqlpersistencetest.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CreateNewItem extends Activity {

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

}
