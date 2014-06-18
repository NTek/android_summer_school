package com.rtrk.complexlist;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rtrk.R;

public class PlanetsAdapter extends ArrayAdapter<Planet> {

	Context context;
	int textViewResourceId;
	List<Planet> planets;

	public PlanetsAdapter(Context context, int textViewResourceId,
			List<Planet> planets) {
		super(context, textViewResourceId, planets);

		this.context = context;
		this.textViewResourceId = textViewResourceId;
		this.planets = planets;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		PlanetHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(textViewResourceId, parent, false);

			holder = new PlanetHolder();
			holder.txtViewName = (TextView) row.findViewById(R.id.textName);
			holder.txtViewVolume = (TextView) row.findViewById(R.id.textVolume);

			row.setTag(holder);
		} else {
			holder = (PlanetHolder) row.getTag();
		}

		Planet planet = planets.get(position);
		holder.txtViewName.setText(planet.getName());
		holder.txtViewVolume.setText(planet.getVolume());

		return row;
	}

	class PlanetHolder {
		TextView txtViewName;
		TextView txtViewVolume;
	}

}
