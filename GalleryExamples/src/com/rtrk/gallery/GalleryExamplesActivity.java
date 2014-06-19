package com.rtrk.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rtrk.gallery.db.DBGalleryExampleActivity;
import com.rtrk.gallery.device.DeviceGalleryExampleActivity;
import com.rtrk.gallery.gallery.GalleryExampleActivity;
import com.rtrk.gallery.gridgallery.GridGalleryExampleActivity;
import com.rtrk.gallery.viewpager.ViewPagerExampleActivity;

/**
 * Gallery Examples.
 */
public class GalleryExamplesActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void showGallery(View view) {
        Intent i = new Intent(getApplicationContext(),
                GalleryExampleActivity.class);
        startActivity(i);
    }

    public void showGridGallery(View view) {
        Intent i = new Intent(getApplicationContext(),
                GridGalleryExampleActivity.class);
        startActivity(i);
    }

    public void showDeviceImages(View view) {
        Intent i = new Intent(getApplicationContext(),
                DeviceGalleryExampleActivity.class);
        startActivity(i);
    }

    public void showImagesFromDB(View view) {
        Intent i = new Intent(getApplicationContext(),
                DBGalleryExampleActivity.class);
        startActivity(i);
    }

    public void showViewPager(View view) {
        Intent i = new Intent(getApplicationContext(),
                ViewPagerExampleActivity.class);
        startActivity(i);
    }
}