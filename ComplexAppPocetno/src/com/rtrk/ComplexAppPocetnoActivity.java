package com.rtrk;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.rtrk.v1.ComplexAppActivity1;
import com.rtrk.v2.ComplexAppActivity2;

public class ComplexAppPocetnoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btnV1 = (Button) findViewById(R.id.btnV1);
        btnV1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComplexAppPocetnoActivity.this,
                        ComplexAppActivity1.class);
                startActivity(i);
            }
        });
        Button btnV2 = (Button) findViewById(R.id.btnV2);
        btnV2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComplexAppPocetnoActivity.this,
                        ComplexAppActivity2.class);
                startActivity(i);
            }
        });
        Button btnContacts = (Button) findViewById(R.id.btnContacts);
        btnContacts.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri
                        .parse("content://contacts/people/"));
                startActivity(i);
            }
        });
    }
}