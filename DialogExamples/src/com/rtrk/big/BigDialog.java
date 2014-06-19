package com.rtrk.big;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;

import com.rtrk.R;

public class BigDialog extends Dialog {
    public BigDialog(Activity activity) {
        super((Context) activity);
        setContentView(R.layout.big_dialog);
        setCancelable(false);
        setTitle("This is a title");
        Point lDisplayPoint = new Point();
        /** Get Display Size. */
        activity.getWindowManager().getDefaultDisplay().getSize(lDisplayPoint);
        int screenWidth = lDisplayPoint.x;
        int screenHeight = lDisplayPoint.y;
        getWindow().getAttributes().x = screenWidth / 2;
        getWindow().getAttributes().width = screenWidth - 100;
        getWindow().getAttributes().height = screenHeight - 50;
    }
}
