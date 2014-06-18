package com.rtrk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SummerWeatherPocetnoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btnV1 = (Button)findViewById(R.id.btnV1);
        btnV1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SummerWeatherPocetnoActivity.this, com.rtrk.v1.SummerWeatherActivity.class);
				startActivity(i);
			}
		});


    }
}