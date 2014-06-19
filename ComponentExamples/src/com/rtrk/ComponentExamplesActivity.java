package com.rtrk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.rtrk.forminput.FormInputExamplesActivity;
import com.rtrk.textinput.TextInputExamplesActivity;

public class ComponentExamplesActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btnForm = (Button) findViewById(R.id.btnForm);
        btnForm.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ComponentExamplesActivity.this,
                        FormInputExamplesActivity.class);
                startActivity(i);
            }
        });
        Button btnBig = (Button) findViewById(R.id.btnText);
        btnBig.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ComponentExamplesActivity.this,
                        TextInputExamplesActivity.class);
                startActivity(i);
            }
        });
    }
}