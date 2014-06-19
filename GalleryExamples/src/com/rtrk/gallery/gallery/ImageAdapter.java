package com.rtrk.gallery.gallery;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.rtrk.gallery.R;

public class ImageAdapter extends BaseAdapter {
    public Integer[] mPics = { R.drawable.antartica1, R.drawable.antartica2,
            R.drawable.antartica3, R.drawable.antartica4,
            R.drawable.antartica5, R.drawable.antartica6,
            R.drawable.antartica7, R.drawable.antartica8,
            R.drawable.antartica9, R.drawable.antartica10 };
    private Context mContext = null;

    public ImageAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mPics.length;
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
            lImageView = new ImageView(mContext);
            lImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            lImageView.setLayoutParams(new Gallery.LayoutParams(250, 220));
            lImageView
                    .setBackgroundResource(android.R.drawable.alert_light_frame);
        } else {
            lImageView = (ImageView) oldView;
        }
        lImageView.setImageResource(mPics[index]);
        return lImageView;
    }
}
