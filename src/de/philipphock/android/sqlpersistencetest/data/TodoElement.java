package de.philipphock.android.sqlpersistencetest.data;

public class TodoElement {
	private final String text;
	private final long id;
	public TodoElement(long id,String text) {
		this.text = text;
		this.id=id;
	}
	
	public String getText(){
		return text;
	}
}
