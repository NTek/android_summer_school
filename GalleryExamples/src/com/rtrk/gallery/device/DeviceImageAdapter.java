package com.rtrk.gallery.device;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.rtrk.gallery.R;

public class DeviceImageAdapter extends BaseAdapter {
    private Context mContext = null;
    private Uri[] mUrls = null;

    public DeviceImageAdapter(Context context, Uri[] urls) {
        mContext = context;
        this.mUrls = urls;
    }

    @Override
    public int getCount() {
        return mUrls.length;
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
        lImageView.setImageURI(mUrls[index]);
        return lImageView;
    }
}
