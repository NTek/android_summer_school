package com.rtrk.tab;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import com.rtrk.R;

public class TabExampleActivity extends FragmentActivity {
    private static final String FIRST_TAB_TAG = "first";
    private static final String SECOND_TAB_TAG = "second";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        initializeTabs();
    }

    /** Initialize Tabs */
    private void initializeTabs() {
        FragmentTabHost lTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        lTabHost.setup(this, getSupportFragmentManager(), R.id.tab_contenier);
        lTabHost.addTab(
                lTabHost.newTabSpec(FIRST_TAB_TAG).setIndicator(
                        getResources().getString(R.string.tabs_first_tab)),
                FirstTabFragment.class, null);
        lTabHost.addTab(
                lTabHost.newTabSpec(SECOND_TAB_TAG).setIndicator(
                        getResources().getString(R.string.tabs_second_tab)),
                SecondTabFragment.class, null);
    }
}
