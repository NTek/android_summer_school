package com.rtrk.wevview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class WebViewExamplesActivity extends Activity {
    private static final String TAG = "WebViewExample";
    private EditText mUrl = null;
    private WebView mWeb = null;
    private Button mBtnGo = null;
    private JavaScriptInterface mJavaScriptInterface = null;

    /** Called when the activity is first created. */
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mUrl = (EditText) findViewById(R.id.edtUrl);
        mWeb = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = mWeb.getSettings();
        /** Turn on JavaScript. */
        webSettings.setJavaScriptEnabled(true);
        /**
         * Add showToast() function from Java to be invoked:
         * Android.showToast('asdf');.
         */
        mJavaScriptInterface = new JavaScriptInterface(getApplicationContext());
        mWeb.addJavascriptInterface(mJavaScriptInterface, "myAndroid");
        /**
         * The WebView will handle all hyper links and URLs by using this
         * WebViewClient.
         */
        mWeb.setWebViewClient(new WebViewClient() {
            /** Callback when the page is going to be loaded. */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(getApplication().getClass().getName(), "LOADING URL: "
                        + url);
                /**
                 * We don't want to load page that contains word 'Virus' in the
                 * url.
                 */
                if (url.contains("Virus"))
                    return true;
                else
                    return false;
            }

            /** Callback when the page is loaded. */
            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    /** put the URL in the address textview component. */
                    mUrl.setText(url);
                }
            }
        });
        /** Load HTML from string. */
        String summary = "<html><body>Hello <b>World</b>.<input type=\"button\" value=\"Click here\" onClick=\"myAndroid.showToast('Hello Android!')\" /></body></html>";
        mWeb.loadData(summary, "text/html", "utf-8");
        mBtnGo = (Button) findViewById(R.id.btnGo);
        mBtnGo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mUrl.getText().toString();
                Log.d(TAG, "Loading from URL: " + url);
                mWeb.loadUrl(url);
            }
        });
        mWeb.requestFocus();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /** Check if the key event was the Back button and if there's history. */
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWeb.canGoBack()) {
            mWeb.goBack();
            return true;
        }
        /**
         * If it wasn't the Back key or there's no web page history, use the
         * default system behavior (probably exit the activity).
         */
        return super.onKeyDown(keyCode, event);
    }
}