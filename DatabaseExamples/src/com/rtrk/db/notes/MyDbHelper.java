package com.rtrk.db.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDbHelper extends SQLiteOpenHelper {
    private static final String TAG = MyDbHelper.class.getName();
    private static final String DATABASE_NAME = "NotesDatabase.db";
    public static final int DATABASE_VERSION = 1;
    /** SQL Statement to create a new database. */
    private static final String DATABASE_CREATE = "create table "
            + NotesDbManager.DATABASE_TABLE + " (" + NotesDbManager._ID
            + " integer primary key autoincrement, " + NotesDbManager.TITLE
            + " text not null, " + NotesDbManager.TIMESTAMP
            + " text not null, " + NotesDbManager.TEXT + " text not null);";

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when no database exists in disk and the helper class needs to
     * create a new one.
     */
    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(DATABASE_CREATE);
        Log.d(TAG, "Created a new database.");
    }

    /**
     * Called when there is a database version mismatch meaning that the version
     * of the database on disk needs to be upgraded to the current version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        /** The simplest case is to drop the old table and create a new one. */
        _db.execSQL("DROP TABLE IF EXISTS " + NotesDbManager.DATABASE_TABLE);
        /** Log the version upgrade. */
        Log.d(TAG, "Upgrading from version " + _oldVersion + " to "
                + _newVersion + ", which will destroy all old data");
        /** Create a new one. */
        onCreate(_db);
    }
}