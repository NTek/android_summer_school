package com.rtrk.wevview;

import android.content.Context;
import android.widget.Toast;

public class JavaScriptInterface {
    private Context mContext = null;

    /** Instantiate the interface and set the context */
    public JavaScriptInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
}
