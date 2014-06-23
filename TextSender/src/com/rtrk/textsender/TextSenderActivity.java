package com.rtrk.textsender;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class TextSenderActivity extends Activity {
    private static final String TAG = "TextSenderActivity";
    private static final int PORT_NUMBER = 6000;
    private EditText mAddress = null;
    private EditText mText = null;
    private Button mConnect = null;
    private Button mSend = null;
    private boolean mConnected = false;
    private Socket mSocket = null;
    private BufferedReader mIn = null;
    private PrintWriter mOut = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mAddress = (EditText) findViewById(R.id.editTextAddr);
        mText = (EditText) findViewById(R.id.editText);
        mConnect = (Button) findViewById(R.id.btnConnect);
        mSend = (Button) findViewById(R.id.btnSend);
        mConnected = false;
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

    public void connect(View view) {
        ConnectDisconnectTask cdTask = new ConnectDisconnectTask();
        if (mConnected == false) {
            cdTask.execute();
            mConnect.setText(R.string.btnDisconnect);
            mSend.setEnabled(true);
            mText.requestFocus();
        } else {
            cdTask.execute();
            mConnect.setText(R.string.btnConnect);
            mSend.setEnabled(false);
        }
    }

    public void send(View view) {
        SendTask sendTask = new SendTask();
        sendTask.execute(mText.getText().toString());
    }

    class ConnectDisconnectTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                if (mConnected == false) {
                    mSocket = new Socket(mAddress.getText().toString().trim(),
                            PORT_NUMBER);
                    mIn = new BufferedReader(new InputStreamReader(
                            mSocket.getInputStream()));
                    mOut = new PrintWriter(new OutputStreamWriter(
                            mSocket.getOutputStream()), true);
                    mConnected = true;
                } else {
                    mOut.println("CLOSE");
                    mIn.close();
                    mOut.close();
                    mSocket.close();
                    mConnected = false;
                }
            } catch (UnknownHostException e) {
                Log.e(TAG, "There was an error.", e);
            } catch (IOException e) {
                Log.e(TAG, "There was an IO Execption.", e);
            }
            return null;
        }
    }

    class SendTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            mOut.println(params[0]);
            return null;
        }
    }
}