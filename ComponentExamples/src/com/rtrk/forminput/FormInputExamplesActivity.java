package com.rtrk.forminput;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.rtrk.R;

public class FormInputExamplesActivity extends Activity {
	
	ToggleButton tglBtn;
    ProgressBar pb;
    SeekBar sb;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_input);
        
        Button btnBig = (Button)findViewById(R.id.btnNormal);
        btnBig.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Kliknuo na dugme", Toast.LENGTH_LONG).show();
			}
		});

        tglBtn = (ToggleButton)findViewById(R.id.tglBtn);
        tglBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Stanje toggle dugmeta je: " + tglBtn.isChecked(), Toast.LENGTH_LONG).show();
			}
		});
 
        pb = (ProgressBar)findViewById(R.id.progressBar1);
        sb = (SeekBar)findViewById(R.id.seekBar1);
               sb.setOnSeekBarChangeListener(new  OnSeekBarChangeListener() {

			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				pb.setProgress(sb.getProgress());
			}
			public void onStartTrackingTouch(SeekBar arg0) {
			}
			public void onStopTrackingTouch(SeekBar arg0) {
			}
        }
        );

    }
    
    
   
	
    
}