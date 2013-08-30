package de.philipphock.android.sqlpersistencetest.data;

public class TodoElement {
	private final String text;

	public TodoElement(String text) {
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
}
