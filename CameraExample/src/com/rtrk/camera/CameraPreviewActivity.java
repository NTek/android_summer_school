package com.rtrk.camera;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

public class CameraPreviewActivity extends Activity implements
		SurfaceHolder.Callback, OnClickListener {
	static final int RESULT_CODE = 321;
	private static final String TAG = "CameraExample";
	Camera mCamera;
	boolean mPreviewRunning = false;
	private Context mContext = this;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		Log.e(TAG, "onCreate");
		setContentView(R.layout.preview);
		mSurfaceView = (SurfaceView) findViewById(com.rtrk.camera.R.id.surface_camera);
		mSurfaceView.setOnClickListener(this);
		mSurfaceHolder = mSurfaceView.getHolder();
		mSurfaceHolder.addCallback(this);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); // is deprecated on v3.0 and newer
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
		public void onPictureTaken(byte[] imageData, Camera c) {

			if (imageData != null) {

				Intent mIntent = new Intent();

				StoreByteImage(mContext, imageData, 50, "ImageName");
				mCamera.startPreview();

				setResult(RESULT_CODE, mIntent);

				// Close this preview window
				finish();

			}
		}
	};

	protected void onResume() {
		Log.e(TAG, "onResume");
		super.onResume();
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	protected void onStop() {
		Log.e(TAG, "onStop");
		super.onStop();
	}

	public void surfaceCreated(SurfaceHolder holder) {
		Log.e(TAG, "surfaceCreated");
		mCamera = Camera.open();
		// mCamera.setDisplayOrientation(90);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		Log.e(TAG, "surfaceChanged");

		// XXX stopPreview() will crash if preview is not running
		if (mPreviewRunning) {
			mCamera.stopPreview();
		}

		Camera.Parameters p = mCamera.getParameters();
		// get the supported preview sizes
		List<Camera.Size> list = p.getSupportedPreviewSizes();
		// get the first one that is supported
		Camera.Size size = list.get(0);

		// p.setPreviewSize(w, h);
		// set the size
		p.setPreviewSize(size.width, size.height);

		mCamera.setParameters(p);
		try {
			// set where the preview will be displayed
			mCamera.setPreviewDisplay(holder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mCamera.startPreview();
		mPreviewRunning = true;
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.e(TAG, "surfaceDestroyed");
		mCamera.stopPreview();
		mPreviewRunning = false;
		mCamera.release();
	}

	private SurfaceView mSurfaceView;
	private SurfaceHolder mSurfaceHolder;

	public void onClick(View arg0) {

		mCamera.takePicture(null, mPictureCallback, mPictureCallback);
	}

	public static boolean StoreByteImage(Context mContext, byte[] imageData,
			int quality, String expName) {

		File sdImageMainDirectory = new File("/sdcard");
		FileOutputStream fileOutputStream = null;
		String nameFile;
		try {

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 5;

			Bitmap myImage = BitmapFactory.decodeByteArray(imageData, 0,
					imageData.length, options);

			fileOutputStream = new FileOutputStream(
					sdImageMainDirectory.toString() + "/image.jpg");

			BufferedOutputStream bos = new BufferedOutputStream(
					fileOutputStream);

			myImage.compress(CompressFormat.JPEG, quality, bos);

			bos.flush();
			bos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}