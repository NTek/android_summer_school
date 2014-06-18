package com.rtrk.db.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;

public class NotesDbManager {

	public static final String DATABASE_TABLE = "Notes";

	// The primary key column name for use in where clauses.
	public static final String _ID = "_id";
	// The name of each column in your database.
	public static final String TITLE = "title";
	public static final String TIMESTAMP = "timestamp";
	public static final String TEXT = "text";

	// Variable to hold the database instance
	private SQLiteDatabase db;
	// Context of the application using the database.
	// Database open/upgrade helper
	private MyDbHelper dbHelper;

	public NotesDbManager(Context context) {
		dbHelper = new MyDbHelper(context);
	}

	// Open database
	public NotesDbManager open() throws SQLException {
		try {
			db = dbHelper.getWritableDatabase();
		} catch (SQLiteException ex) {
			db = dbHelper.getReadableDatabase();
		}
		return this;
	}

	// Close database
	public void close() {
		db.close();
	}

	public int insertEntry(NoteItem newItem) {
		// Create a new row of values to insert.
		ContentValues newEntry = new ContentValues();
		// Assign values for each row.
		newEntry.put(TITLE, newItem.getTitle());
		newEntry.put(TIMESTAMP, newItem.getTimeString());
		newEntry.put(TEXT, newItem.getText());
		// Insert the row.
		return (int) db.insert(DATABASE_TABLE, null, newEntry);
	}

	public boolean updateEntry(long rowIndex, NoteItem newItem) {
		ContentValues newValue = new ContentValues();
		newValue.put(TITLE, newItem.getTitle());
		newValue.put(TIMESTAMP, newItem.getTimeString());
		newValue.put(TEXT, newItem.getText());
		return db.update(DATABASE_TABLE, newValue, _ID + "=" + rowIndex, null) > 0;
	}

	public int removeEntry(long index) {
		return db.delete(DATABASE_TABLE, _ID + "=" + index, null);
	}

	public int removeAllEntries() {
		return db.delete(DATABASE_TABLE, null, null);
	}

	public Cursor getAllEntries() {
		return db.query(DATABASE_TABLE, new String[] { _ID, TITLE, TIMESTAMP,
				TEXT }, null, null, null, null, null);
	}
	
	
	/** This method is invoked from the ContentProvider
	 * 
	 * @param columns
	 * @param selection
	 * @param selectionArgs
	 * @param sortOrder
	 * @param additionalWhere Holds ID of a single note
	 * @return
	 */
	public Cursor getCursor(String[] columns, String selection,
			String[] selectionArgs, String sortOrder, String additionalWhere) {

		 // Uisng SQLiteQueryBuilder instead of query() method
	    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

	    // Set the table
	    queryBuilder.setTables(DATABASE_TABLE);

	    if (additionalWhere != null) {
	    	// Adding the ID to the original query
	        queryBuilder.appendWhere(_ID + "="
	            + additionalWhere);
	    }
	    
		Cursor cursor = queryBuilder.query(db, columns, selection, selectionArgs, 
				null, null, sortOrder);
	    
		return cursor;
	}
	
	public NoteItem getEntry(long rowIndex) {
		Cursor cursor = db.query(true, DATABASE_TABLE, new String[] { _ID,
				TITLE, TIMESTAMP, TEXT }, _ID + "=" + rowIndex, null, null,
				null, null, null);
		if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
			throw new SQLException("Item not found for the row: " + rowIndex);
		}

		// Create new note item
		NoteItem result = new NoteItem();
		// Set title
		int titleColumnId = cursor.getColumnIndex(TITLE);
		result.setTitle(cursor.getString(titleColumnId));
		// Set time stamp
		int timeStampColumnId = cursor.getColumnIndex(TIMESTAMP);
		result.setTime(cursor.getString(timeStampColumnId));
		// Set text
		int textColumnId = cursor.getColumnIndex(TEXT);
		result.setText(cursor.getString(textColumnId));

		return result;
	}

	public Cursor setCursorToNoteItem(long rowIndex) throws SQLException {
		Cursor result = db.query(true, DATABASE_TABLE, new String[] { _ID,
				TITLE, TIMESTAMP, TEXT }, _ID + "=" + rowIndex, null, null,
				null, null, null);
		if ((result.getCount() == 0) || !result.moveToFirst()) {
			throw new SQLException("Item not found for the row: " + rowIndex);
		}
		return result;
	}

}
