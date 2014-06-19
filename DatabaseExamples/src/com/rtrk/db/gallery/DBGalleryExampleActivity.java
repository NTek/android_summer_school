package com.rtrk.db.gallery;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.rtrk.db.R;

public class DBGalleryExampleActivity extends Activity {
	ImageView image;
	DBImageAdapter ia;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);

		Gallery gallery = (Gallery) findViewById(R.id.gallery1);
		ia = new DBImageAdapter(this);
		gallery.setAdapter(ia);
		image = (ImageView) findViewById(R.id.imageView1);

		gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				Toast.makeText(
						getBaseContext(),
						"You have selected picture " + (index + 1), Toast.LENGTH_SHORT).show();
				// Get the data location of the image
				String[] cols = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
						cols, // Which columns to return
						null, // Return all rows
						null, 
						null);
				int columnIndex = cursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				cursor.moveToPosition(index);
				// Get image filename
				String imagePath = cursor.getString(columnIndex);
				// Use this path to do further processing, i.e. full screen
				// display
				image.setImageURI(Uri.parse(imagePath));
				//MediaStore.Images.Thumbnails.getThumbnail(getContentResolver(), imageID, MediaStore.Images.Thumbnails.MINI_KIND, null);
			}

		});

	}
}