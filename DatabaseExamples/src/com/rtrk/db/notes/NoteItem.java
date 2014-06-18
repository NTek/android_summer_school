package com.rtrk.db.notes;

import android.text.format.Time;

public class NoteItem {
	private String title;
	private Time time;
	private String text;
	
	public NoteItem() {
		this.title = "NO TITLE";
		this.time = new Time();
		this.text = "NO TEXT";
	}
	
	public NoteItem(String title, Time time, String text) {
		this.title = title;
		this.time = time;
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public void setTime(String time) {
		this.time.parse(time);
	}
	
	public String getTimeString() {
		String timeString = new String(time.year + "/" + time.month + "/"
				+ time.monthDay + " " + time.hour + ":" + time.minute);
		return timeString;
	}
	
}
