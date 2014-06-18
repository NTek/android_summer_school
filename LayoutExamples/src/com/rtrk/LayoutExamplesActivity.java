package com.rtrk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rtrk.R;
import com.rtrk.frame.FrameActivity;
import com.rtrk.linear.LinearActivity;
import com.rtrk.relative.RelativeActivity;
import com.rtrk.tab.TabExampleActivity;
import com.rtrk.table.TableExampleActivity;

public class LayoutExamplesActivity extends Activity {
    Button btnFrame;
    Button btnLinear;
    Button btnRelative;
    Button btnTable;
    Button btnTab;

/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btnFrame = (Button)findViewById(R.id.btnFrame);
        btnLinear = (Button)findViewById(R.id.btnLinear);
        btnRelative = (Button)findViewById(R.id.btnRelative);
        btnTable = (Button)findViewById(R.id.btnTable);
        btnTab = (Button)findViewById(R.id.btnTab);

        btnFrame.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(LayoutExamplesActivity.this, FrameActivity.class);
				startActivity(i); 			}
		});
        btnLinear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(LayoutExamplesActivity.this, LinearActivity.class);
				startActivity(i); 			}
		});
        btnRelative.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(LayoutExamplesActivity.this, RelativeActivity.class);
				startActivity(i); 			}
		});

        btnTable.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(LayoutExamplesActivity.this, TableExampleActivity.class);
				startActivity(i); 			}
		});
        btnTab.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(LayoutExamplesActivity.this, TabExampleActivity.class);
				startActivity(i); 			}
		});
    }
    
    public static final String TAG = "LayoutExamplesActivity";
    public void onStart() {
    	super.onStart();
    	Log.d(TAG, "onStart");
    }
    public void onResume() {
    	super.onResume();
    	Log.d(TAG, "onResume");
    }
    public void onPause() {
    	super.onPause();
    	Log.d(TAG, "onPause");
    }
    public void onStop() {
    	super.onStop();
    	Log.d(TAG, "onStop");
    }
    public void onRestart() {
    	super.onRestart();
    	Log.d(TAG, "onRestart");
    }
    public void onDestroy() {
    	super.onDestroy();
    	Log.d(TAG, "onDestroy");
    }

}