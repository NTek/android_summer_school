package com.rtrk.gridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class GridViewExamplesActivity extends Activity {
	
	private static final String[] PLANETS = new String[] {
	       "Mercury", "Venus", "Mars", "Jupiter", "Saturn",
	       "Uranus", "Neptune", "Pluto", "Krypton"
	  };
	
	TextView txtSelected;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        txtSelected = (TextView) findViewById(R.id.txtSelected);

		GridView gv = (GridView) findViewById(R.id.gridView1);

		ArrayAdapter<String> aa = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1, 
				PLANETS);

		gv.setAdapter(aa);
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				txtSelected.setText("Selected: " + PLANETS[position]);
			}
			
		});
        
    }
}