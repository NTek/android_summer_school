package com.rtrk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.rtrk.complexlist.ComplexListExampleActivity;
import com.rtrk.dynamiclist.DynamicListExampleActivity;
import com.rtrk.staticlist.StaticListExampleActivity;
import com.rtrk.stringlist.StringListExampleActivity;

public class ListExamplesActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btnStatic = (Button) findViewById(R.id.btnStatic);
        btnStatic.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ListExamplesActivity.this,
                        StaticListExampleActivity.class);
                startActivity(i);
            }
        });
        Button btnStringList = (Button) findViewById(R.id.btnStringList);
        btnStringList.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ListExamplesActivity.this,
                        StringListExampleActivity.class);
                startActivity(i);
            }
        });
        Button btnDynamicList = (Button) findViewById(R.id.btnDynamic);
        btnDynamicList.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ListExamplesActivity.this,
                        DynamicListExampleActivity.class);
                startActivity(i);
            }
        });
        Button btnComplexList = (Button) findViewById(R.id.btnComplex);
        btnComplexList.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ListExamplesActivity.this,
                        ComplexListExampleActivity.class);
                startActivity(i);
            }
        });
    }
}