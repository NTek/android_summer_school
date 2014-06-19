package com.rtrk.camera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Custom Camera.
 */
public class CameraPreviewActivity extends Activity implements
        SurfaceHolder.Callback, OnClickListener {
    private static final String TAG = "CameraPreviewActivity";
    private static final int PICTURE_QUALITY = 50;
    private Camera mCamera = null;
    private SurfaceView mSurfaceView = null;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.preview);
        mSurfaceView = (SurfaceView) findViewById(com.rtrk.camera.R.id.surface_camera);
        mSurfaceView.setOnClickListener(this);
        mSurfaceView.getHolder().addCallback(this);
    }

    /** CallBack, notify that picture has been taken. */
    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] imageData, Camera c) {
            if (imageData != null) {
                storeByteImage(getApplicationContext(), imageData,
                        PICTURE_QUALITY);
                setResult(CameraExampleActivity.RESULT_CODE_CUSTOM_CAMERA);
                /** Picture has been taken, close current Activity. */
                finish();
            }
        }
    };

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCamera = Camera.open();
        if (mCamera != null) {
            try {
                mCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                Log.e(TAG, "There was an error in setting Disaplay Holder.", e);
            }
            mCamera.startPreview();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        /**
         * After Surface is Created, change camera preview look (size,
         * rotate,zoom...).
         */
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    /** Take a picture. */
    public void onClick(View view) {
        mCamera.takePicture(null, mPictureCallback, mPictureCallback);
    }

    /** Save picture to Image path. */
    private boolean storeByteImage(Context mContext, byte[] imageData,
            int quality) {
        FileOutputStream lFileOutputStream = null;
        try {
            BitmapFactory.Options lOptions = new BitmapFactory.Options();
            lOptions.inSampleSize = 5;
            Bitmap lImage = BitmapFactory.decodeByteArray(imageData, 0,
                    imageData.length, lOptions);
            lFileOutputStream = new FileOutputStream(
                    CameraExampleActivity.IMAGE_PATH
                            + CameraExampleActivity.IMAGE_NAME
                            + CameraExampleActivity.IMAGE_TYPE);
            BufferedOutputStream lBufferedOutputStream = new BufferedOutputStream(
                    lFileOutputStream);
            lImage.compress(CompressFormat.JPEG, quality, lBufferedOutputStream);
            lBufferedOutputStream.flush();
            lBufferedOutputStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "The specified path was not found.", e);
        } catch (IOException e) {
            Log.e(TAG,
                    "There was an error in Flushing or Closing BufferedOutputStream.",
                    e);
        }
        return true;
    }
}