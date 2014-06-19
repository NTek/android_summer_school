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
    private ArrayList<Planet> mPlanets = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complex_list);
        mPlanets = new ArrayList<Planet>();
        mPlanets.add(new Planet("Mercury", "small"));
        mPlanets.add(new Planet("Venus", "medium"));
        mPlanets.add(new Planet("Mars", "medium"));
        mPlanets.add(new Planet("Jupiter", "big"));
        mPlanets.add(new Planet("Saturn", "big"));
        mPlanets.add(new Planet("Uranus", "big"));
        mPlanets.add(new Planet("Neptune", "big"));
        mPlanets.add(new Planet("Pluto", "very small"));
        mPlanets.add(new Planet("Krypton", "big"));
        PlanetsAdapter adapter = new PlanetsAdapter(this, R.layout.complex_row,
                mPlanets);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view,
                    int position, long id) {
                Planet p = mPlanets.get(position);
                Toast.makeText(
                        getApplicationContext(),
                        "SELECTED PLANET " + p.getName() + "(" + p.getVolume()
                                + ")", Toast.LENGTH_LONG).show();
            }
        });
    }
}