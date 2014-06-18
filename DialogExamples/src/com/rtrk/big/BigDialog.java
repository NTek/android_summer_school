package com.rtrk.big;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import com.rtrk.R;

public class BigDialog extends Dialog {
	public BigDialog(Activity act) {
		super((Context)act);
		
		setContentView(R.layout.big_dialog);
		
		setCancelable(false);
		setTitle("This is a title");
		
		
		int screenWidth  = act.getWindowManager().getDefaultDisplay().getWidth();
		int screenHeight = act.getWindowManager().getDefaultDisplay().getHeight();

		getWindow().getAttributes().x = screenWidth / 2;
		getWindow().getAttributes().width  = screenWidth - 100;
		getWindow().getAttributes().height = screenHeight - 50;
		
	}
	
}
