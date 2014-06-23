package com.rtrk.textreceiver;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class TextReceiverActivity extends Activity {
    private static final String TAG = "TextReceiverActivity";
    private static final int PORT_NUMBER = 6000;
    private ServerSocket mServerSocket = null;
    private Socket mSocket = null;
    private BufferedReader mIn = null;
    private PrintWriter mOut = null;
    private EditText mText = null;
    private Button mStartListening = null;
    private boolean mListening = false;
    private TextTask mTask = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mText = (EditText) findViewById(R.id.editText);
        mStartListening = (Button) findViewById(R.id.btnListen);
        TextView lTextView = (TextView) findViewById(R.id.textView);
        mListening = false;
        try {
            mServerSocket = new ServerSocket(PORT_NUMBER);
            lTextView.setText("My IP addr: " + getLocalIpAddress());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
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

    public void startListening(View view) {
        if (mListening == false) {
            mListening = true;
            mTask = new TextTask();
            mTask.execute();
            mStartListening.setText(R.string.btnStopListen);
        } else {
            mListening = false;
            mTask.cancel(true);
            mStartListening.setText(R.string.btnListen);
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
                    /** Check if address is IPV4. */
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

    private class TextTask extends AsyncTask<Void, String, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            if (mServerSocket != null) {
                try {
                    Log.d(TAG, "WAITING FOR THE CLIENTS");
                    mSocket = mServerSocket.accept();
                    mIn = new BufferedReader(new InputStreamReader(
                            mSocket.getInputStream()));
                    mOut = new PrintWriter(new OutputStreamWriter(
                            mSocket.getOutputStream()), true);
                    Log.d(TAG, "ACCEPTED CLIENT" + mSocket.getInetAddress());
                    while (true) {
                        String line = mIn.readLine();
                        Log.d(TAG, "RECIVED:" + line);
                        if (line.equals("CLOSE")) {
                            break;
                        } else {
                            publishProgress(line);
                        }
                    }
                    mIn.close();
                    mOut.close();
                    mSocket.close();
                } catch (IOException e) {
                    Log.e(TAG, "There was an Exception.", e);
                }
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected void onProgressUpdate(String... value) {
            mText.append(value[0] + "\n");
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                mStartListening.setText(R.string.btnListen);
                mListening = false;
            } else {
                Toast.makeText(getApplicationContext(),
                        "Socket is null, check connection!", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }
}