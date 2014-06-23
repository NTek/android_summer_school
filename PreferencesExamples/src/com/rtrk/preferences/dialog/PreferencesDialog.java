package com.rtrk.preferences.dialog;

import android.preference.PreferenceActivity;

import com.rtrk.preferences.R;

import java.util.List;

public class PreferencesDialog extends PreferenceActivity {
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preferences_headres, target);
    }
}