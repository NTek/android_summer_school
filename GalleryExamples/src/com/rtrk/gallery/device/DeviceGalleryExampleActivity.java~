package com.rtrk.gallery.device;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.rtrk.gallery.R;

import java.io.File;
import java.io.FilenameFilter;

public class DeviceGalleryExampleActivity extends Activity {
    private static final String IMAGE_PATH = "/sdcard/Pictures";
    private ImageView mImage = null;
    private DeviceImageAdapter mDeviceImageAdapter = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        File images = new File(IMAGE_PATH);
        File[] imageList = images.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return (name.endsWith(".jpg")) || (name.endsWith(".png"));
            }
        });
        final Uri[] mUrls = new Uri[imageList.length];
        for (int i = 0; i < imageList.length; i++) {
            mUrls[i] = Uri.parse(imageList[i].getAbsolutePath());
        }
        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        mDeviceImageAdapter = new DeviceImageAdapter(this, mUrls);
        gallery.setAdapter(mDeviceImageAdapter);
        mImage = (ImageView) findViewById(R.id.imageView);
        gallery.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index,
                    long arg3) {
                Toast.makeText(getBaseContext(),
                        "You have selected picture " + (index + 1),
                        Toast.LENGTH_SHORT).show();
                /** Show Images. */
                mImage.setImageURI(mUrls[index]);
            }
        });
    }
}