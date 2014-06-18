package com.rtrk.gallery.db;

import android.app.Activity;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.rtrk.gallery.R;

public class DBImageAdapter extends BaseAdapter {
	private Activity activity;
	private Cursor cursor;
	int columnIndex;

	int imageBackground;

	public DBImageAdapter(Activity activity) {
		this.activity = activity;

		TypedArray ta = activity
				.obtainStyledAttributes(R.styleable.HelloGallery);
		imageBackground = ta.getResourceId(
				R.styleable.HelloGallery_android_galleryItemBackground, 1);
		ta.recycle();

		// Set up an array of the Thumbnail Image ID column we want
		String[] columns = { MediaStore.Images.Thumbnails._ID };
		// Create the cursor pointing to the SDCard
		cursor = activity.managedQuery(
				MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, 
				columns, // Which columns to return
				null, // Return all rows
				null, 
				MediaStore.Images.Thumbnails.IMAGE_ID);
		// Get the column index of the Thumbnails Image ID
		columnIndex = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
	}

	@Override
	public int getCount() {
		return cursor.getCount();
	}

	@Override
	public Object getItem(int arg0) {

		return arg0;
	}

	@Override
	public long getItemId(int arg0) {

		return arg0;
	}

	@Override
	public View getView(int index, View oldView, ViewGroup arg2) {
		System.gc();
		ImageView iv;
		if (oldView == null) {
			iv = new ImageView(activity);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			iv.setLayoutParams(new Gallery.LayoutParams(250, 220));
			// iv.setBackgroundResource(imageBackground);
			iv.setBackgroundResource(android.R.drawable.alert_light_frame);
			
			// Move cursor to current position
			cursor.moveToPosition(index);
			// Get the current value for the requested column
			int imageID = cursor.getInt(columnIndex);
			// Set the content of the image based on the provided URI
			iv.setImageURI(Uri.withAppendedPath(
					MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + imageID));
		} else {
			iv = (ImageView) oldView;
		}
		return iv;
	}
}
