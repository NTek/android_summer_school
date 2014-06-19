package com.rtrk.db.gallery;

import android.app.Activity;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.rtrk.db.R;

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
        /** Set up an array of the Media Image ID column we want. */
        String[] columns = { MediaStore.Images.Media._ID };
        /** Create the cursor pointing to the SDCard. */
        cursor = activity.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                /**
                 * Which columns to return.
                 */
                columns,
                /**
                 * Return all rows.
                 */
                null, null, null);
        /** Get the column index of the Media Image ID. */
        columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int index) {
        return index;
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int index, View oldView, ViewGroup arg2) {
        ImageView lImageView = null;
        if (oldView == null) {
            lImageView = new ImageView(activity);
            lImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            lImageView.setLayoutParams(new Gallery.LayoutParams(250, 220));
            lImageView
                    .setBackgroundResource(android.R.drawable.alert_light_frame);
        } else {
            lImageView = (ImageView) oldView;
        }
        /** Move cursor to current position. */
        cursor.moveToPosition(index);
        /** Get the current value for the requested column. */
        int imageID = cursor.getInt(columnIndex);
        /** Set the content of the image based on the provided URI. */
        lImageView.setImageURI(Uri.withAppendedPath(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + imageID));
        return lImageView;
    }
}
