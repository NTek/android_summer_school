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
	private Context ctx;
	int imageBackground;

	public Integer[] pics = { R.drawable.antartica1, R.drawable.antartica2,
			R.drawable.antartica3, R.drawable.antartica4,
			R.drawable.antartica5, R.drawable.antartica6,
			R.drawable.antartica7, R.drawable.antartica8,
			R.drawable.antartica9, R.drawable.antartica10 };

	public ImageAdapter(Context context) {
		ctx = context;
		TypedArray ta = context
				.obtainStyledAttributes(R.styleable.HelloGallery);
		imageBackground = ta.getResourceId(
				R.styleable.HelloGallery_android_galleryItemBackground, 1);
		ta.recycle();
	}

	@Override
	public int getCount() {
		return pics.length;
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
		ImageView iv = new ImageView(ctx);
		iv.setImageResource(pics[index]);
		iv.setScaleType(ImageView.ScaleType.FIT_XY);
		iv.setLayoutParams(new Gallery.LayoutParams(250, 220));
		// iv.setBackgroundResource(imageBackground);
		iv.setBackgroundResource(android.R.drawable.alert_light_frame);
		return iv;
	}
}
