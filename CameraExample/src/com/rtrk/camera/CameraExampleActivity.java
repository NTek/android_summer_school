package com.rtrk.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CameraExampleActivity extends Activity {
	ImageView imageView;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);

		imageView = (ImageView) findViewById(R.id.imageView1);
	}

	public void useSystemCamera(View view) {
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, 124);
	}

	public void showPreview(View view) {
		Intent i = new Intent(getApplicationContext(),
				CameraPreviewActivity.class);
		startActivityForResult(i, 123);
	}

	@Override
	public void onRestart() {
		super.onRestart();
		// imageView.setImageURI(Uri.parse("/sdcard/image.jpg"));
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 123 && resultCode == 321)
			imageView.setImageURI(Uri.parse("/sdcard/image.jpg"));
		if (requestCode == 124) {
			if (intent != null && intent.getExtras() != null) {
				Bitmap photo = (Bitmap) intent.getExtras().get("data");

				if (photo != null)
					imageView.setImageBitmap(photo);
			}
		}
	}

}
