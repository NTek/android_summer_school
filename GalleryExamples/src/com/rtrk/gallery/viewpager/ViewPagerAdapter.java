package com.rtrk.gallery.viewpager;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.rtrk.gallery.R;

public class ViewPagerAdapter extends PagerAdapter {

	private Integer[] pics = { R.drawable.antartica1, R.drawable.antartica2,
			R.drawable.antartica3, R.drawable.antartica4,
			R.drawable.antartica5, R.drawable.antartica6,
			R.drawable.antartica7, R.drawable.antartica8,
			R.drawable.antartica9, R.drawable.antartica10 };

	private Activity activity;
	private ImageView imageView;

	public ViewPagerAdapter(Activity activity) {
		this.activity = activity;
		imageView = (ImageView) activity.findViewById(R.id.imageView1);
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		ImageView iv = new ImageView(activity);
		iv.setImageResource(pics[position]);
		iv.setScaleType(ImageView.ScaleType.FIT_XY);
		iv.setLayoutParams(new LayoutParams(250, 220));
		// iv.setBackgroundResource(imageBackground);
		iv.setBackgroundResource(android.R.drawable.alert_light_frame);
		iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				imageView.setImageResource(pics[position]);
			}
		});
		((ViewPager) container).addView(iv);
		return iv;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
		object = null;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return ((View) arg1).equals(arg0);
	}

	@Override
	public int getCount() {
		return pics.length;
	}

}
