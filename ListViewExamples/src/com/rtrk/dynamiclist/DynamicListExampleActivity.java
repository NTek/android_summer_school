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
    private ArrayList<String> mPlanets = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_list);
        mPlanets = new ArrayList<String>();
        mPlanets.add("Mercury");
        mPlanets.add("Venus");
        mPlanets.add("Mars");
        mPlanets.add("Jupiter");
        mPlanets.add("Saturn");
        mPlanets.add("Uranus");
        mPlanets.add("Neptune");
        mPlanets.add("Pluto");
        mPlanets.add("Krypton");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mPlanets);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getApplicationContext(),
                "SELECTED PLANET " + mPlanets.get(position), Toast.LENGTH_LONG)
                .show();
    }
}
