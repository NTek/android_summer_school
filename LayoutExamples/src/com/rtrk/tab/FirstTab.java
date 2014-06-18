package com.rtrk.tab;

import com.rtrk.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FirstTab extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textView = new TextView(this);
		textView.setText(R.string.tabs_first_tab);
		setContentView(textView);
	}
}