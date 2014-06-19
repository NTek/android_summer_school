package com.rtrk.complexlist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rtrk.R;

import java.util.List;

public class PlanetsAdapter extends ArrayAdapter<Planet> {
    private Context mContext = null;
    private int mTextViewResourceId = 0;
    private List<Planet> mPlanets = null;

    public PlanetsAdapter(Context context, int textViewResourceId,
            List<Planet> planets) {
        super(context, textViewResourceId, planets);
        mContext = context;
        mTextViewResourceId = textViewResourceId;
        mPlanets = planets;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlanetHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mTextViewResourceId, parent, false);
            holder = new PlanetHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (PlanetHolder) convertView.getTag();
        }
        updateView(holder, position);
        return convertView;
    }

    private void updateView(PlanetHolder holder, int position) {
        Planet planet = mPlanets.get(position);
        holder.getName().setText(planet.getName());
        holder.getVolume().setText(planet.getVolume());
    }

    private class PlanetHolder {
        private TextView mName = null;
        private TextView mVolume = null;

        public PlanetHolder(View rootView) {
            mName = (TextView) rootView.findViewById(R.id.textName);
            mVolume = (TextView) rootView.findViewById(R.id.textVolume);
        }

        public TextView getName() {
            return mName;
        }

        public TextView getVolume() {
            return mVolume;
        }
    }
}
