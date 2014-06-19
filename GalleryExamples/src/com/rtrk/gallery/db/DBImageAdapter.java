package com.rtrk.gallery.db;

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

import com.rtrk.gallery.R;

public class DBImageAdapter extends BaseAdapter {
    private Activity mActivity = null;
    private Cursor mCursor = null;
    private int mColumnIndex = 0;
    private int mImageBackground = 0;

    public DBImageAdapter(Activity activity) {
        this.mActivity = activity;
        TypedArray ta = activity
                .obtainStyledAttributes(R.styleable.HelloGallery);
        mImageBackground = ta.getResourceId(
                R.styleable.HelloGallery_android_galleryItemBackground, 1);
        ta.recycle();
        /** Set up an array of the Media Image ID column we want. */
        String[] columns = { MediaStore.Images.Media._ID };
        /** Create the cursor pointing to the SDCard. */
        mCursor = activity.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, MediaStore.Images.Media.TITLE);
        /** Get the column index of the Media Image ID. */
        mColumnIndex = mCursor
                .getColumnIndexOrThrow(MediaStore.Images.Media._ID);
    }

    @Override
    public int getCount() {
        return mCursor.getCount();
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
        ImageView lImageView;
        if (oldView == null) {
            lImageView = new ImageView(mActivity);
            lImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            lImageView.setLayoutParams(new Gallery.LayoutParams(250, 220));
            lImageView
                    .setBackgroundResource(android.R.drawable.alert_light_frame);
        } else {
            lImageView = (ImageView) oldView;
        }
        /** Move cursor to current position. */
        mCursor.moveToPosition(index);
        /** Get the current value for the requested column. */
        int imageID = mCursor.getInt(mColumnIndex);
        /** Set the content of the image based on the provided URI. */
        lImageView.setImageURI(Uri.withAppendedPath(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + imageID));
        return lImageView;
    }
}
