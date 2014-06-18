package com.rtrk.menus;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuExamplesActivity extends Activity {

	public static final int OPTIONS_MENU_ADD_ID = 0;
	public static final int CONTEXT_MENU_DELETE_ID = 1;
	public static final int CONTEXT_MENU_EDIT_ID = 2;

	ArrayList<String> planets;
	ArrayAdapter<String> adapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

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

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, planets);

		ListView lv = (ListView) findViewById(R.id.listView2);
		lv.setAdapter(adapter);

		// Initiate adding context menu
		registerForContextMenu(lv);

	}
	

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, CONTEXT_MENU_DELETE_ID, 0, R.string.context_menu_delete);
		menu.add(0, CONTEXT_MENU_EDIT_ID, 1, R.string.context_menu_edit);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case CONTEXT_MENU_EDIT_ID:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();
//			Toast.makeText(
//					getApplicationContext(),
//					"CONTEXT MENU: Item ID: " + info.id + ", position: "
//							+ info.position + ", PLANET: "
//							+ planets.get(info.position), Toast.LENGTH_LONG).show();
			Intent i = new Intent(getApplicationContext(),
					EditPlanetActivity.class);
			i.putExtra("com.rtrk.v3.NAME", planets.get(info.position));
			i.putExtra("com.rtrk.v3.INDEX", info.position);

			startActivityForResult(i, CONTEXT_MENU_EDIT_ID);
			return true;
		}
		return super.onContextItemSelected(item);
	}

	/** Options menu. */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, OPTIONS_MENU_ADD_ID, 0, R.string.options_menu_add);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case OPTIONS_MENU_ADD_ID:
			Toast.makeText(getApplicationContext(), "OPTIONS MENU: ADD PLANET",
					Toast.LENGTH_LONG).show();
			return true;
		}

		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		switch (resultCode) {
		case RESULT_OK:
			Bundle extras = intent.getExtras();
			String planetName = extras != null ? extras.getString("com.rtrk.v3.NAME"): null;
			int index = extras != null ? extras.getInt("com.rtrk.v3.INDEX", -1): -1;
			if (index != -1) {
				planets.set(index, planetName);
				adapter.notifyDataSetChanged(); 
			}
			break;
		case RESULT_CANCELED:
			Toast.makeText(getApplicationContext(), "RESULT CANCELED ",
					Toast.LENGTH_LONG).show();
		}
	} 
}