package com.rtrk.forminput;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.rtrk.R;

public class FormInputExamplesActivity extends Activity {
    private ToggleButton mToggleButton = null;
    private ProgressBar mProgressBar = null;
    private SeekBar mSeekBar = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_input);
        Button btnBig = (Button) findViewById(R.id.btnNormal);
        btnBig.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Kliknuo na dugme",
                        Toast.LENGTH_LONG).show();
            }
        });
        mToggleButton = (ToggleButton) findViewById(R.id.tglBtn);
        mToggleButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(
                        getApplicationContext(),
                        "Stanje toggle dugmeta je: "
                                + mToggleButton.isChecked(), Toast.LENGTH_LONG)
                        .show();
            }
        });
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
        mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                mProgressBar.setProgress(mSeekBar.getProgress());
            }

            public void onStartTrackingTouch(SeekBar arg0) {
            }

            public void onStopTrackingTouch(SeekBar arg0) {
            }
        });
    }
}