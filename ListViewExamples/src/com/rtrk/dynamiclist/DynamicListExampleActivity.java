package com.rtrk.dynamiclist;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rtrk.R;

public class DynamicListExampleActivity extends ListActivity {

	ArrayList<String> planets; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_list);
        
        planets = new ArrayList<String>(); 
       
        planets.add("Mercury");
        planets.add("Venus");
        planets.add("Mars");
        planets.add("Jupiter");
        planets.add("Saturn");
        planets.add("Uranus");
        planets.add("Neptune");
        planets.add("Pluto");
        planets.add("Krypton");
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        		this, android.R.layout.simple_list_item_1, planets);
        
        setListAdapter(adapter);
        
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	Toast.makeText(getApplicationContext(), "SELECTED PLANET " + planets.get(position), Toast.LENGTH_LONG).show();
    }
}




