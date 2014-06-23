package com.rtrk.preferences.dialog;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.rtrk.preferences.R;

public class UpdatePreferenceFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_update);
    }
}
