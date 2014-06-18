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
	ImageView image;
	ImageAdapter ia;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        
        Gallery gallery = (Gallery)findViewById(R.id.gallery1);
        
        ia = new ImageAdapter(this); 
        gallery.setAdapter(ia);
        image = (ImageView)findViewById(R.id.imageView1);
        
        gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Toast.makeText(getBaseContext(), 
						"You have selected picture " + (index+1) + " of Antartica", 
						Toast.LENGTH_SHORT).show();
				image.setImageResource(ia.pics[index]);
				
			}
        	
        }); 
        
    }
}