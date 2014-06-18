package com.rtrk.comm;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ImageSenderActivity extends Activity {
	
	public static final String TAG = "ImageSenderActivity";
	TextView tv;
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = (TextView)findViewById(R.id.textView1);
    }
    
    public void sendImage(View view) {
    	ImageTask task = new ImageTask();
		task.execute();
    }
    
    class ImageTask extends AsyncTask<Void, String, Void> {

		@Override
		protected Void doInBackground(Void... params) {
	    	try {
				int BUFF_SIZE = 1024;
				publishProgress("Sending...");
				File fin = new File(
						"/sdcard/slika.png");
				FileInputStream in = new FileInputStream(fin);
				CheckedInputStream cis = new CheckedInputStream(in, new Adler32());

				Socket s = new Socket("192.168.247.78", 6000);
//				Socket s = new Socket("10.0.2.2", 5000);
				DataOutputStream out = new DataOutputStream(s.getOutputStream());

				long fileLen = fin.length();
				out.writeLong(fileLen);
				
				long noOfPackets = fileLen / BUFF_SIZE;
				int remainder = (int) (fileLen % BUFF_SIZE);
				
				byte buff[];
				if (noOfPackets == 0) { // file size less than buffer size
					 buff = new byte[(int)fileLen];
				} else {
					 buff = new byte[BUFF_SIZE];
				}

				int read = -1;
				for (long i = 0; i < noOfPackets; i++) {
					read = cis.read(buff, 0, BUFF_SIZE);
					out.write(buff, 0, BUFF_SIZE);
					Log.d(TAG, "Read " + read + " bytes...");
				}

				if (remainder > 0) {
					read = cis.read(buff, 0, remainder);
					out.write(buff, 0, remainder);
					Log.d(TAG, "Read remainder " + read + " bytes...");
				}

				String checksum = "Checksum is: " + cis.getChecksum().getValue();
				publishProgress(checksum);

				in.close();
				out.close();
			} catch (FileNotFoundException e) {
				Toast.makeText(getApplicationContext(), "File not found!", Toast.LENGTH_LONG).show();
			} catch (IOException e) {
				Toast.makeText(getApplicationContext(), "IO Exception: " + e.getMessage(), Toast.LENGTH_LONG).show();
			} 
			return null;
		}

		@Override
		protected void onProgressUpdate(String... value) {
			tv.setText(value[0]);
		}
		
		@Override
		protected void onPostExecute(Void result) {
			Toast.makeText(getApplicationContext(), "File sent!", Toast.LENGTH_LONG).show();
		}
	}
}