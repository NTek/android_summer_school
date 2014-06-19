package com.rtrk.db.callhistory;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.rtrk.db.R;

public class CallHistoryExampleActivity extends Activity {
    private static final int NO_FLAGS = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_history);
        String[] columns = new String[] { Calls._ID, Calls.NUMBER,
                Calls.CACHED_NAME, Calls.DURATION };
        Cursor cur = getContentResolver().query(CallLog.Calls.CONTENT_URI,
                columns, null, null, null);
        ListAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.call_history_item, cur, new String[] { Calls.NUMBER,
                        Calls.CACHED_NAME, Calls.DURATION }, new int[] {
                        R.id.call_number, R.id.call_name, R.id.call_duration },
                NO_FLAGS);
        ListView mainList = (ListView) findViewById(R.id.call_history_list);
        mainList.setAdapter(adapter);
    }
}
