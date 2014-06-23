package com.rtrk.comm;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

import org.w3c.dom.ls.LSOutput;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ImageSenderActivity extends Activity {
    public static final String TAG = "ImageSenderActivity";
    private static final int PORT_NUMBER = 6000;
    private static final int BUFF_SIZE = 1024;
    /** Image Path, Name and Type. */
    public static final String IMAGE_PATH = "/sdcard/";
    public static final String IMAGE_NAME = "image";
    public static final String IMAGE_TYPE = ".jpg";
    private TextView mTextView = null;
    private EditText mEditTextIPAddress = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTextView = (TextView) findViewById(R.id.textView);
        mEditTextIPAddress = (EditText) findViewById(R.id.edittext_ip_address);
    }

    public void sendImage(View view) {
        ImageTask task = new ImageTask();
        task.execute();
    }

    private class ImageTask extends AsyncTask<Void, String, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                publishProgress("Sending...");
                File fin = new File(IMAGE_PATH + IMAGE_NAME + IMAGE_TYPE);
                FileInputStream in = new FileInputStream(fin);
                CheckedInputStream cis = new CheckedInputStream(in,
                        new Adler32());
                Socket lSocket = new Socket(mEditTextIPAddress.getText()
                        .toString().trim(), PORT_NUMBER);
                DataOutputStream out = new DataOutputStream(
                        lSocket.getOutputStream());
                long fileLen = fin.length();
                out.writeLong(fileLen);
                long noOfPackets = fileLen / BUFF_SIZE;
                int remainder = (int) (fileLen % BUFF_SIZE);
                byte buff[];
                /** File size less than buffer size. */
                if (noOfPackets == 0) {
                    buff = new byte[(int) fileLen];
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
                String checksum = "Checksum is: "
                        + cis.getChecksum().getValue();
                publishProgress(checksum);
                in.close();
                out.close();
                lSocket.close();
            } catch (FileNotFoundException e) {
                Log.i(TAG, "File not found!", e);
            } catch (IOException e) {
                Log.i(TAG, "There was an IO Execption!", e);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... value) {
            mTextView.setText(value[0]);
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getApplicationContext(), "File sent!",
                    Toast.LENGTH_LONG).show();
        }
    }
}