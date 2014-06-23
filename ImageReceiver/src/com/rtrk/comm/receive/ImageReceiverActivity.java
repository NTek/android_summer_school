package com.rtrk.comm.receive;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

public class ImageReceiverActivity extends Activity {
    public static final String TAG = "ImageReceiverActivity";
    public ImageView mImageView = null;
    public TextView mAddr = null;
    public Button mStart = null;
    private ServerSocket mServerSocket = null;
    private ImageTask mImageTask = null;
    boolean mListening = false;
    private String mImageFileName = "primljeno.png";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mImageView = (ImageView) findViewById(R.id.imageView1);
        mAddr = (TextView) findViewById(R.id.txtAddr);
        mStart = (Button) findViewById(R.id.button1);
        try {
            mServerSocket = new ServerSocket(6000);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        mListening = false;
    }

    @Override
    public void onDestroy() {
        try {
            mServerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    public void startReceivingImage(View view) {
        if (mListening == false) {
            mListening = true;
            mImageView.setImageResource(R.drawable.ic_launcher);
            mImageTask = new ImageTask();
            mImageTask.execute();
            mStart.setText(R.string.btnStop);
            Toast.makeText(ImageReceiverActivity.this,
                    "Waiting for the image...", Toast.LENGTH_LONG).show();
        } else {
            mListening = false;
            mImageTask.cancel(true);
            mStart.setText(R.string.btnStart);
        }
    }

    private class ImageTask extends AsyncTask<Void, String, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            int BUFF_SIZE = 65536;
            try {
                publishProgress("My IP address is: " + getLocalIpAddress());
                Socket s = mServerSocket.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                String outFileName = mImageFileName;
                FileOutputStream out = openFileOutput(outFileName,
                        Context.MODE_PRIVATE);
                long fileLen = in.readLong();
                Log.d(TAG, "File length: " + fileLen);
                byte buff[];
                buff = new byte[BUFF_SIZE];
                int read = -1;
                while ((read = in.read(buff, 0, BUFF_SIZE)) != -1) {
                    out.write(buff, 0, read);
                    Log.d(TAG, "Read " + read + " bytes...");
                }
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
            mAddr.setText(value[0]);
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                FileInputStream in = ImageReceiverActivity.this
                        .openFileInput(mImageFileName);
                CheckedInputStream cis = new CheckedInputStream(in,
                        new Adler32());
                Bitmap image = BitmapFactory.decodeStream(cis);
                mImageView.setImageBitmap(image);
                String checksum = "Checksum is: "
                        + cis.getChecksum().getValue();
                mAddr.setText(checksum);
                Log.d(TAG, "Decoded image");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            mListening = false;
            mStart.setText(R.string.btnStart);
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
                    if (!inetAddress.isLoopbackAddress()
                    /** Check is address IPV4. */
                    && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e(TAG, "There was a Scoket Execption.", ex);
        }
        return null;
    }
}