package com.rtrk.preferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rtrk.preferences.dialog.PreferencesDialog;

public class PreferencesExamplesActivity extends Activity {
    private SharedPreferences mPreferences = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        updateFieldsFromPreferences();
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mPreferences.getString("username", "n/a");
                String password = mPreferences.getString("password", "n/a");
                Toast.makeText(getApplicationContext(),
                        "Login with " + username + "/" + password,
                        Toast.LENGTH_LONG).show();
            }
        });
        Button btnChange = (Button) findViewById(R.id.btnChangePreferences);
        btnChange.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),
                        PreferencesDialog.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();
        updateFieldsFromPreferences();
    }

    private void updateFieldsFromPreferences() {
        String username = mPreferences.getString("username", "n/a");
        String password = mPreferences.getString("password", "n/a");
        Toast.makeText(getApplicationContext(), username, Toast.LENGTH_SHORT)
                .show();
        EditText edtUsername = (EditText) findViewById(R.id.edtUsername);
        EditText edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtUsername.setText(username);
        edtPassword.setText(password);
    }
}