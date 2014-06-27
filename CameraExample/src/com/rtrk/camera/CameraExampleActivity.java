package com.rtrk.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

/**
 * Camera Examples.
 */
public class CameraExampleActivity extends Activity {
	private static final String TAG = "CameraExampleActivity";
	/** Intent Key */
	private static final String DATA_KEY = "data";
	/** Request and Result Codes. */
	private static final int REQUEST_CODE_DEFAULT_CAMERA = 0;
	public static final int REQUEST_CODE_CUSTOM_CAMERA = 1;
	public static final int RESULT_CODE_CUSTOM_CAMERA = 2;
	/** Image Path, Name and Type. */
	public static final String IMAGE_PATH = "/sdcard/";
	public static final String IMAGE_NAME = "image";
	public static final String IMAGE_TYPE = ".jpg";
	private ImageView mImageView = null;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);
		mImageView = (ImageView) findViewById(R.id.imageView);
	}

	/** Start Default Android Camera Application. */
	public void useSystemCamera(View view) {
		Intent lIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(lIntent, REQUEST_CODE_DEFAULT_CAMERA);
	}

	/** Start Custom Android Camera Activity. */
	public void useCustomCamera(View view) {
		Intent i = new Intent(getApplicationContext(),
				CameraPreviewActivity.class);
		startActivityForResult(i, REQUEST_CODE_CUSTOM_CAMERA);
	}

	/** Show Captured Image. */
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == REQUEST_CODE_CUSTOM_CAMERA
				&& resultCode == RESULT_CODE_CUSTOM_CAMERA) {
			Uri lImageLoction = Uri.parse(IMAGE_PATH + IMAGE_NAME + IMAGE_TYPE);
			mImageView.setImageURI(lImageLoction);
		} else if (requestCode == REQUEST_CODE_DEFAULT_CAMERA) {
			Bitmap lCapturedImage = (Bitmap) intent.getExtras().get(DATA_KEY);
			mImageView.setImageBitmap(lCapturedImage);
		}
	}
}
