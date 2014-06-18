package com.rtrk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rtrk.big.BigDialog;
import com.rtrk.builtin.AlertDialogExample;

public class DialogExamplesActivity extends Activity {
	
	BigDialog bd;
	EditText edTxtName;
	
    /** Called when the activity is first created. */
	Activity thiz;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thiz = this;
        
        // This will turn off the window title
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.main);
        
        Button btnBig = (Button)findViewById(R.id.btnBig);
        btnBig.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				bd = new BigDialog(thiz);

				edTxtName = (EditText)bd.findViewById(R.id.editText1);
				
				Button btnClose = (Button)bd.findViewById(R.id.btnDialogBigClose);
				btnClose.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						String name = edTxtName.getText().toString();
						Toast.makeText(getApplicationContext(), "Name is: " + name, Toast.LENGTH_LONG).show();
						bd.cancel();
					}
				});
				
				bd.show();
			}
		});
        
        Button btnAlert = (Button)findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				AlertDialogExample ad = new AlertDialogExample(thiz);
				ad.dialog.show();
			}
		});

    }
    
    public static final String TAG = "DialogExamplesActivity";
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