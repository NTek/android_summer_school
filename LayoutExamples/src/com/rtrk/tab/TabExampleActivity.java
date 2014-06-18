package com.rtrk.tab;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.rtrk.R;

public class TabExampleActivity extends TabActivity {

	TabHost tabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);

		init();

	}

	private void init() {
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
		TabSpec secondTabSpec = tabHost.newTabSpec("tid1");
		// podesavamo naziv i ikonu
		firstTabSpec.setIndicator("Artists", getResources().getDrawable(R.anim.artists));
		secondTabSpec.setIndicator("Search", getResources().getDrawable(R.anim.search));
		// kada se odabere tab, aktivirace se zadati Activity
		firstTabSpec.setContent(new Intent(this, FirstTab.class));
		secondTabSpec.setContent(new Intent(this, SecondTab.class));

		tabHost.addTab(firstTabSpec);
		tabHost.addTab(secondTabSpec);

		tabHost.getTabWidget().setCurrentTab(0);
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			public void onTabChanged(String tabId) {
//			for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
//				tabHost.getTabWidget().getChildAt(i)
//						.setBackgroundColor(Color.WHITE);
//			}
//
//			tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab())
//					.setBackgroundColor(Color.GRAY);
		}
	});
	
	// Setting BackGround
//	for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
//		tabHost.getTabWidget().getChildAt(i)
//				.setBackgroundColor(Color.WHITE);
//	}
//
//	tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.GRAY);
	}
		
	
}
