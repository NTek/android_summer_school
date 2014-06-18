package com.rtrk.staticlist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rtrk.R;

public class StaticListExampleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.static_list);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        		this, R.array.planets_array, android.R.layout.simple_list_item_1);
        
        ListView lv = (ListView)findViewById(R.id.listView1);
        lv.setAdapter(adapter);
        
    }
}