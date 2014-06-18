package com.rtrk.complexlist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.rtrk.R;

public class ComplexListExampleActivity extends Activity {
	
	ArrayList<Planet> planets; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complex_list);
        
        planets = new ArrayList<Planet>(); 
        planets.add(new Planet("Mercury", "small"));
        planets.add(new Planet("Venus", "medium"));
        planets.add(new Planet("Mars", "medium"));
        planets.add(new Planet("Jupiter", "big"));
        planets.add(new Planet("Saturn", "big"));
        planets.add(new Planet("Uranus", "big"));
        planets.add(new Planet("Neptune", "big"));
        planets.add(new Planet("Pluto", "very small"));
        planets.add(new Planet("Krypton", "big"));
        
        PlanetsAdapter adapter = new PlanetsAdapter(
        		this, R.layout.complex_row, planets);
        
        ListView lv = (ListView)findViewById(R.id.listView4);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				Planet p = planets.get(position);
				Toast.makeText(getApplicationContext(), "SELECTED PLANET " + p.getName() + "(" + p.getVolume() + ")", Toast.LENGTH_LONG).show();
			}
		});
        
    }
}