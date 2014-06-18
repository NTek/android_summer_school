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
	private Context ctx;
	private Uri[] urls;

	int imageBackground;

	public DeviceImageAdapter(Context context, Uri[] urls) {
		ctx = context;
		this.urls = urls;

		TypedArray ta = context
				.obtainStyledAttributes(R.styleable.HelloGallery);
		imageBackground = ta.getResourceId(
				R.styleable.HelloGallery_android_galleryItemBackground, 1);
		ta.recycle();
	}

	@Override
	public int getCount() {
		return urls.length;
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
			iv = new ImageView(ctx);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			iv.setLayoutParams(new Gallery.LayoutParams(250, 220));
			// iv.setBackgroundResource(imageBackground);
			iv.setBackgroundResource(android.R.drawable.alert_light_frame);
		} else {
			iv = (ImageView) oldView;
		}
		iv.setImageURI(urls[index]);
		return iv;
	}
}
