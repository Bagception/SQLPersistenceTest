package de.philipphock.android.sqlpersistencetest.adapter;


import java.util.List;

import de.philipphock.android.sqlpersistencetest.R;
import de.philipphock.android.sqlpersistencetest.data.TodoElement;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TodoListAdapter extends ArrayAdapter<TodoElement>{

	
	public TodoListAdapter(Context context,List<TodoElement> model) {
		super(context, R.layout.listviewlayout,model);
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View listviewlayout = inflater.inflate(R.layout.listviewlayout, parent,false);
		TextView heading = (TextView) listviewlayout.findViewById(R.id.listHeading);
		ImageView imageView = (ImageView) listviewlayout.findViewById(R.id.icon);
		TextView second = (TextView) listviewlayout.findViewById(R.id.listSecondLine);
		heading.setText(getItem(position).getText());
		return listviewlayout;
	}

}
