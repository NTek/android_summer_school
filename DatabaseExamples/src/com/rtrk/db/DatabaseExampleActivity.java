package com.rtrk.db;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rtrk.db.bookmarks.BookmarksExampleActivity;
import com.rtrk.db.callhistory.CallHistoryExampleActivity;
import com.rtrk.db.contentproviders.NotesContentProviderActivity;
import com.rtrk.db.gallery.DBGalleryExampleActivity;
import com.rtrk.db.notes.NotesExampleActivity;

/**
 * DataBase Example.
 */
public class DatabaseExampleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void showNotesDemo(View view) {
        Intent i = new Intent(getApplicationContext(),
                NotesExampleActivity.class);
        startActivity(i);
    }

    public void showBookmarks(View view) {
        Intent i = new Intent(getApplicationContext(),
                BookmarksExampleActivity.class);
        startActivity(i);
    }

    public void showCallHistory(View view) {
        Intent i = new Intent(getApplicationContext(),
                CallHistoryExampleActivity.class);
        startActivity(i);
    }

    public void showGallery(View view) {
        Intent i = new Intent(getApplicationContext(),
                DBGalleryExampleActivity.class);
        startActivity(i);
    }

    public void showContentProvider(View view) {
        Intent i = new Intent(getApplicationContext(),
                NotesContentProviderActivity.class);
        startActivity(i);
    }
}