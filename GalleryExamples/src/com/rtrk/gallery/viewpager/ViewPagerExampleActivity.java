package com.rtrk.gallery.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.rtrk.gallery.R;

public class ViewPagerExampleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);
        ViewPager lViewPager = (ViewPager) findViewById(R.id.viewpager);
        lViewPager.setAdapter(new ViewPagerAdapter(this));
    }
}
