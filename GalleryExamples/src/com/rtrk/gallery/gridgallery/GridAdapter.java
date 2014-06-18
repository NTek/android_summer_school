package com.rtrk.gallery.gridgallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.rtrk.gallery.R;

public class GridAdapter extends BaseAdapter {
	private Context ctx;

	public Integer[] pics = { R.drawable.antartica1, R.drawable.antartica2,
			R.drawable.antartica3, R.drawable.antartica4,
			R.drawable.antartica5, R.drawable.antartica6,
			R.drawable.antartica7, R.drawable.antartica8,
			R.drawable.antartica9, R.drawable.antartica10 };

	public GridAdapter(Context context) {
		ctx = context;
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
		ImageView imageView;
		if (oldView == null) {
			imageView = new ImageView(ctx);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(5, 5, 5, 5);
		} else {
			imageView = (ImageView) oldView;
		}
		imageView.setImageResource(pics[index]);
		return imageView;
	}
}
