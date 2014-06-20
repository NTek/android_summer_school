package com.rtrk.preferences.dialog;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.rtrk.preferences.R;

public class PreferencesDialog extends PreferenceActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}