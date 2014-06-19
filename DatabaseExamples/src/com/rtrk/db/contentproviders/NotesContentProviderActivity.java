package com.rtrk.db.contentproviders;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.rtrk.db.R;
import com.rtrk.db.notes.NotesDbManager;

public class NotesContentProviderActivity extends Activity implements
        LoaderCallbacks<Cursor> {
    private static final int NO_FLAGS = 0;
    private SimpleCursorAdapter mAdapter = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cntprvnotes);
        mAdapter = new SimpleCursorAdapter(this, R.layout.list_item, null,
                new String[] { NotesDbManager.TIMESTAMP, NotesDbManager.TITLE,
                        NotesDbManager.TEXT }, new int[] { R.id.time_stamp,
                        R.id.title, R.id.text }, NO_FLAGS);
        ListView mainList = (ListView) findViewById(R.id.cntmain_list);
        mainList.setAdapter(mAdapter);
        getLoaderManager().initLoader(1, null, this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(this,
                NotesContentProvider.CONTENT_URI, new String[] {
                        NotesDbManager._ID, NotesDbManager.TIMESTAMP,
                        NotesDbManager.TITLE, NotesDbManager.TEXT }, null,
                null, null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}