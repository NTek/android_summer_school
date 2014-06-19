package com.rtrk.db.notes;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.rtrk.db.R;

public class NotesExampleActivity extends Activity {

	NotesDbManager notesManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notes);

		notesManager = new NotesDbManager(this);
		notesManager.open();

		notesManager.removeAllEntries();
		populateDatabase();

		// Bind data to list
		Cursor cur = notesManager.getAllEntries();
		startManagingCursor(cur);

		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_item,
				cur, new String[] { NotesDbManager.TIMESTAMP,
						NotesDbManager.TITLE, NotesDbManager.TEXT }, new int[] {
						R.id.time_stamp, R.id.title, R.id.text }, 0);

		ListView mainList = (ListView) findViewById(R.id.main_list);
		mainList.setAdapter(adapter);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// Close the database
		notesManager.close();
	}

	void populateDatabase() {
		// Populate database with some dummy data
		Time now = new Time();
		now.setToNow();

		NoteItem item = new NoteItem("Note 1", now, "This is note 1");
		notesManager.insertEntry(item);

		item = new NoteItem("Note 2", now, "This is note 2");
		notesManager.insertEntry(item);

		item = new NoteItem(
				"Note 3 - This is note with long title, should ellipsize",
				now,
				"Note 3 is note with longer title, now, Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
		notesManager.insertEntry(item);
	}

}