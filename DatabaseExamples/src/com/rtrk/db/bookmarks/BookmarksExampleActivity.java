package com.rtrk.db.bookmarks;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Browser;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.rtrk.db.R;

/**
 * Bookmarks Example.
 */
public class BookmarksExampleActivity extends Activity {
    private static final int NO_FLAGS = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmarks);
        String[] columns = new String[] { Browser.BookmarkColumns._ID,
                Browser.BookmarkColumns.TITLE, Browser.BookmarkColumns.URL,
                Browser.BookmarkColumns.CREATED };
        Cursor cur = getContentResolver().query(
                android.provider.Browser.BOOKMARKS_URI, columns, null, null,
                null);
        ListAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.bookmark_item, cur, new String[] {
                        Browser.BookmarkColumns.TITLE,
                        Browser.BookmarkColumns.URL,
                        Browser.BookmarkColumns.CREATED }, new int[] {
                        R.id.bookmark_title, R.id.bookmark_url,
                        R.id.bookmark_created }, NO_FLAGS);
        ListView mainList = (ListView) findViewById(R.id.bookmarks_list);
        mainList.setAdapter(adapter);
    }
}
