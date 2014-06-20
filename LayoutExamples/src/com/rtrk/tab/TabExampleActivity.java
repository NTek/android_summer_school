package com.rtrk.tab;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.rtrk.R;

public class TabExampleActivity extends TabActivity {
    private TabHost mTabHost = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        init();
    }

    private void init() {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        TabSpec firstTabSpec = mTabHost.newTabSpec("tid1");
        TabSpec secondTabSpec = mTabHost.newTabSpec("tid1");
        // podesavamo naziv i ikonu
        firstTabSpec.setIndicator("Artists",
                getResources().getDrawable(R.anim.artists));
        secondTabSpec.setIndicator("Search",
                getResources().getDrawable(R.anim.search));
        // kada se odabere tab, aktivirace se zadati Activity
        firstTabSpec.setContent(new Intent(this, FirstTab.class));
        secondTabSpec.setContent(new Intent(this, SecondTab.class));
        mTabHost.addTab(firstTabSpec);
        mTabHost.addTab(secondTabSpec);
        mTabHost.getTabWidget().setCurrentTab(0);
        mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
            public void onTabChanged(String tabId) {
            }
        });
    }
}
