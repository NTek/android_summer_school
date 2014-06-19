package com.rtrk.db.notes;

import android.text.format.Time;

public class NoteItem {
    private String mTitle = "";
    private Time mTime = null;
    private String mText = "";

    public NoteItem() {
        this.mTitle = "NO TITLE";
        this.mTime = new Time();
        this.mText = "NO TEXT";
    }

    public NoteItem(String title, Time time, String text) {
        this.mTitle = title;
        this.mTime = time;
        this.mText = text;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public Time getTime() {
        return mTime;
    }

    public void setTime(Time time) {
        this.mTime = time;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public void setTime(String time) {
        this.mTime.parse(time);
    }

    public String getTimeString() {
        String timeString = new String(mTime.year + "/" + mTime.month + "/"
                + mTime.monthDay + " " + mTime.hour + ":" + mTime.minute);
        return timeString;
    }
}
