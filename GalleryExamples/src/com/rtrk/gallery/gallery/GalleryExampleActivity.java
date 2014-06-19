package com.rtrk.gallery.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.rtrk.gallery.R;

public class GalleryExampleActivity extends Activity {
    private ImageView mImage = null;
    private ImageAdapter mImageAdapter = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        mImageAdapter = new ImageAdapter(this);
        gallery.setAdapter(mImageAdapter);
        mImage = (ImageView) findViewById(R.id.imageView);
        gallery.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index,
                    long arg3) {
                Toast.makeText(
                        getBaseContext(),
                        "You have selected picture " + (index + 1)
                                + " of Antartica", Toast.LENGTH_SHORT).show();
                mImage.setImageResource(mImageAdapter.mPics[index]);
            }
        });
    }
}