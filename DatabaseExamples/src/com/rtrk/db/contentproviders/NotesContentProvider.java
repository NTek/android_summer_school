package com.rtrk.db.contentproviders;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.rtrk.db.notes.NotesDbManager;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Database Example.
 */
public class NotesContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.rtrk.db.contentproviders.notescontentprovider";
    private static final String BASE_PATH = "notes";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);
    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    /** Used for the UriMacher */
    private static final int NOTES = 10;
    private static final int NOTE_ID = 20;
    static {
        /**
         * All notes. URI ends with '/notes' The last argument (NOTES) is a
         * constant that is returned by the match() method if the pattern is
         * recognized.
         */
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, NOTES);
        /**
         * Single note: URI ends with '/notes/<number>'. Wild card for number is
         * '#' The last argument (NOTE_ID) is a constant that is returned by the
         * match() method if the pattern is recognized.
         */
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", NOTE_ID);
    }
    /** NotesDbManager from the com.rtrk.db.notes package */
    NotesDbManager manager;

    @Override
    public boolean onCreate() {
        manager = new NotesDbManager(getContext());
        return false;
    }

    @Override
    public String getType(Uri arg0) {
        return null;
    }

    /**
     * Throws IllegalArgumentException if non-existing columns are used.
     * 
     * @param columns
     */
    private void checkColumns(String[] columns) {
        String[] available = { NotesDbManager._ID, NotesDbManager.TITLE,
                NotesDbManager.TIMESTAMP, NotesDbManager.TEXT };
        if (columns != null) {
            HashSet<String> requestedColumns = new HashSet<String>(
                    Arrays.asList(columns));
            HashSet<String> availableColumns = new HashSet<String>(
                    Arrays.asList(available));
            /** Check if all columns which are requested are available */
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException(
                        "Unknown columns in projection");
            }
        }
    }

    @Override
    public Cursor query(Uri uri, String[] columns, String selection,
            String[] selectionArgs, String sortOrder) {
        Log.d("NotesContentProvider", "Query invoked");
        /**
         * Check if the caller has requested a column which does not exists.
         * Will throw IllegalArgumentException if wrong columns are used.
         */
        checkColumns(columns);
        manager.open();
        Cursor cursor;
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case NOTES:
                /** Fetching all notes. */
                cursor = manager.getCursor(columns, selection, selectionArgs,
                        sortOrder, null);
                break;
            case NOTE_ID:
                /** Fetching single note. */
                cursor = manager.getCursor(columns, selection, selectionArgs,
                        sortOrder,
                        NotesDbManager._ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        /** Make sure that potential listeners are getting notified. */
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(Uri arg0, ContentValues arg1) {
        /** Method Not Implemented. */
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        /** Method Not Implemented. */
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(getClass().getName(), "Delete invoked with URI: " + uri);
        int uriType = sURIMatcher.match(uri);
        manager.open();
        int rowsDeleted = 0;
        switch (uriType) {
            case NOTES:
                Log.d(getClass().getName(), "Deleting all notes.");
                rowsDeleted = manager.removeAllEntries();
                break;
            case NOTE_ID:
                String id = uri.getLastPathSegment();
                Log.d(getClass().getName(), "Deleting note with ID: " + id);
                rowsDeleted = manager.removeEntry(Integer.parseInt(id));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }
}
