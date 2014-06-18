package com.rtrk.stringlist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rtrk.R;

public class StringListExampleActivity extends Activity {
	private static final String[] PLANETS = new String[] {
	       "Mercury", "Venus", "Mars", "Jupiter", "Saturn",
	       "Uranus", "Neptune", "Pluto", "Krypton"
	  };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.string_list);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        		this, android.R.layout.simple_list_item_1, PLANETS);
        
        ListView lv = (ListView)findViewById(R.id.listView2);
        lv.setAdapter(adapter);
        
    }
}






