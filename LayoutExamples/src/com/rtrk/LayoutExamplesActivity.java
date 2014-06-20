package com.rtrk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rtrk.frame.FrameActivity;
import com.rtrk.linear.LinearActivity;
import com.rtrk.relative.RelativeActivity;
import com.rtrk.tab.TabExampleActivity;
import com.rtrk.table.TableExampleActivity;

public class LayoutExamplesActivity extends Activity {
    private Button mBtnFrame = null;
    private Button mBtnLinear = null;
    private Button mBtnRelative = null;
    private Button mBtnTable = null;
    private Button mBtnTab = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mBtnFrame = (Button) findViewById(R.id.btnFrame);
        mBtnLinear = (Button) findViewById(R.id.btnLinear);
        mBtnRelative = (Button) findViewById(R.id.btnRelative);
        mBtnTable = (Button) findViewById(R.id.btnTable);
        mBtnTab = (Button) findViewById(R.id.btnTab);
        mBtnFrame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LayoutExamplesActivity.this,
                        FrameActivity.class);
                startActivity(i);
            }
        });
        mBtnLinear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LayoutExamplesActivity.this,
                        LinearActivity.class);
                startActivity(i);
            }
        });
        mBtnRelative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LayoutExamplesActivity.this,
                        RelativeActivity.class);
                startActivity(i);
            }
        });
        mBtnTable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LayoutExamplesActivity.this,
                        TableExampleActivity.class);
                startActivity(i);
            }
        });
        mBtnTab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LayoutExamplesActivity.this,
                        TabExampleActivity.class);
                startActivity(i);
            }
        });
    }
}