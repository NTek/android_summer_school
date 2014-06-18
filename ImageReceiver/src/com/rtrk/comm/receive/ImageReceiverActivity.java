package com.rtrk.comm.receive;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ImageReceiverActivity extends Activity {
	public static final String TAG = "ImageReceiverActivity";

	protected static final int IMAGE_RECEIVED = 1;

	public ImageView imageView;
	public TextView txtAddr;
	public Button btnStart;
	ServerSocket ss;

	ImageTask task; 
	boolean listening;

	Handler imageHandler;

	String imgFileName = "primljeno.png";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		imageView = (ImageView) findViewById(R.id.imageView1);
		txtAddr = (TextView) findViewById(R.id.txtAddr);
		btnStart = (Button)findViewById(R.id.button1);

		try {
			ss = new ServerSocket(6000);
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		}

		listening = false;
	}
	
	@Override
	public void onDestroy() {
		try {
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.onDestroy();
	}

	public void startReceivingImage(View view) {
		if (listening == false) {
			listening = true;
			imageView.setImageResource(R.drawable.ic_launcher);
			task = new ImageTask();
			task.execute();
			btnStart.setText(R.string.btnStop);
			Toast.makeText(ImageReceiverActivity.this, "Waiting for the image...",
					Toast.LENGTH_LONG).show();
		} else {
			listening = false;
			task.cancel(true);
			btnStart.setText(R.string.btnStart);
		}
	}

	class ImageTask extends AsyncTask<Void, String, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			int BUFF_SIZE = 65536;
			try {

				publishProgress("My IP address is: "
								+ getLocalIpAddress());

				Socket s = ss.accept();
				DataInputStream in = new DataInputStream(s.getInputStream());

				String outFileName = imgFileName;
				FileOutputStream out = openFileOutput(outFileName,
						Context.MODE_PRIVATE);

				long fileLen = in.readLong();
				Log.d(TAG, "File length: " + fileLen);
				// long noOfPackets = fileLen / BUFF_SIZE;
				// int remainder = (int) (fileLen % BUFF_SIZE);

				byte buff[];
				buff = new byte[BUFF_SIZE];
				/*
				 * if (noOfPackets == 0) { // file size less than buffer size
				 * buff = new byte[(int)fileLen]; } else { buff = new
				 * byte[BUFF_SIZE]; }
				 */
				int read = -1;

				while ((read = in.read(buff, 0, BUFF_SIZE)) != -1) {
					out.write(buff, 0, read);
					Log.d(TAG, "Read " + read + " bytes...");
				}
				/*
				 * for (long i = 0; i < noOfPackets; i++) { read = in.read(buff,
				 * 0, BUFF_SIZE); out.write(buff, 0, BUFF_SIZE); Log.d(TAG,
				 * "Read " + read + " bytes..."); }
				 * 
				 * if (remainder > 0) { read = in.read(buff, 0, remainder);
				 * out.write(buff, 0, remainder); Log.d(TAG, "Read remainder " +
				 * read + " bytes..."); }
				 */
				out.close();

				in.close();
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(String... value) {
			txtAddr.setText(value[0]);
		}
		
		@Override
		protected void onPostExecute(Void result) {
			try {
				FileInputStream in = ImageReceiverActivity.this
						.openFileInput(imgFileName);
				CheckedInputStream cis = new CheckedInputStream(in,
						new Adler32());
				Bitmap image = BitmapFactory.decodeStream(cis);

				imageView.setImageBitmap(image);
				String checksum = "Checksum is: "
						+ cis.getChecksum().getValue();
				txtAddr.setText(checksum);
				Log.d(TAG, "Decoded image");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			listening = false;
			btnStart.setText(R.string.btnStart);
		}
	}

	public String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e(TAG, ex.toString());
		}
		return null;
	}
}