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
    protected static final String TAG = "WebViewExample";
	EditText edtUrl; 
    WebView  web;
    Button   btnGo;
    JavaScriptInterface jsi;

	/** Called when the activity is first created. */
    @SuppressLint("SetJavaScriptEnabled")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        edtUrl = (EditText)findViewById(R.id.edtUrl);
        web = (WebView)findViewById(R.id.webView1);
        WebSettings webSettings = web.getSettings();
        // turn on JavaScript
        webSettings.setJavaScriptEnabled(true);
        // add showToast() function from Java to be invoked: Android.showToast('asdf');
        jsi = new JavaScriptInterface(getApplicationContext());
        web.addJavascriptInterface(jsi, "myAndroid");
               
        // The WebView will handle all hyper links and URLs by using this WebViewClient
        web.setWebViewClient(new WebViewClient() {

        	// callback when the page is going to be loaded
        	@Override
        	public boolean shouldOverrideUrlLoading(WebView view, String url) {
        		Log.d(getApplication().getClass().getName(), "LOADING URL: " + url);
            	// We don't want to load page that contains word 'Virus' in the url
        		if (url.contains("Virus"))
            		return true;
        		else return false;
        	}
        	
        	// callback when the page is loaded
        	@Override
            public void onPageFinished(WebView view, String url) {
            	if (url.startsWith("http:") || url.startsWith("https:") ) {
            		// put the URL in the address textview component 
            		edtUrl.setText(url);
            	}
            }
        });

        // Load HTML from string
        String summary = "<html><body>Hello <b>World</b>.<input type=\"button\" value=\"Click here\" onClick=\"myAndroid.showToast('Hello Android!')\" /></body></html>";
        web.loadData(summary, "text/html", "utf-8");
        
        btnGo = (Button)findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String url = edtUrl.getText().toString();
				Log.d(TAG, "Loading from URL: " + url);
				web.loadUrl(url);
				
			}
		});
        web.requestFocus();
    }
    
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, use the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}