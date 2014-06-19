package com.rtrk.menus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditPlanetActivity extends Activity {
    private EditText mName = null;
    private int mIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_planet);
        mName = (EditText) findViewById(R.id.editTextName);
        Bundle extras = getIntent().getExtras();
        String planetName = extras != null ? extras
                .getString("com.rtrk.v3.NAME") : null;
        mName.setText(planetName);
        mIndex = extras != null ? extras.getInt("com.rtrk.v3.INDEX", -1) : -1;
    }

    public void btnSaveClick(View view) {
        Intent i = new Intent();
        Bundle b = new Bundle();
        String planetName = mName.getText().toString();
        b.putString("com.rtrk.v3.NAME", planetName);
        b.putInt("com.rtrk.v3.INDEX", mIndex);
        i.putExtras(b);
        setResult(RESULT_OK, i);
        finish();
    }

    public void btnCancelClick(View view) {
        Intent i = new Intent();
        setResult(RESULT_CANCELED, i);
        finish();
    }
}
