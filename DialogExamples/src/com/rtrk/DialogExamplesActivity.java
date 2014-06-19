package com.rtrk;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rtrk.big.BigDialog;
import com.rtrk.builtin.AlertDialogExample;

public class DialogExamplesActivity extends Activity {
    private BigDialog mBigDialog = null;
    private EditText mEditText = null;
    /** Called when the activity is first created. */
    private Activity mActivity = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mActivity = this;
        Button btnBig = (Button) findViewById(R.id.btnBig);
        btnBig.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mBigDialog = new BigDialog(mActivity);
                mEditText = (EditText) mBigDialog.findViewById(R.id.edittext);
                Button btnClose = (Button) mBigDialog
                        .findViewById(R.id.btnDialogBigClose);
                btnClose.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        String name = mEditText.getText().toString();
                        Toast.makeText(getApplicationContext(),
                                "Name is: " + name, Toast.LENGTH_LONG).show();
                        mBigDialog.cancel();
                    }
                });
                mBigDialog.show();
            }
        });
        Button btnAlert = (Button) findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AlertDialogExample ad = new AlertDialogExample(mActivity);
                ad.mDialog.show();
            }
        });
    }
}