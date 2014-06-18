package com.rtrk.textsender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TextSenderActivity extends Activity {
	private static final String TAG = "TextSenderActivity";
	EditText txtAddress;
	EditText txtText;
	Button btnConnect;
	Button btnSend;
	boolean connected;
	Socket s;
	BufferedReader in;
	PrintWriter out;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		txtAddress = (EditText) findViewById(R.id.editTextAddr);
		txtText = (EditText) findViewById(R.id.editText);
		btnConnect = (Button) findViewById(R.id.btnConnect);
		btnSend = (Button)findViewById(R.id.btnSend);
		connected = false;
	}
	
	public String getLocalIpAddress() {
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
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
	
	
	public void connect(View view) {
		ConnectDisconnectTask cdTask = new ConnectDisconnectTask();
		if (connected == false) {
			cdTask.execute();
			btnConnect.setText(R.string.btnDisconnect);
			btnSend.setEnabled(true);
			txtText.requestFocus();
		} else {
			cdTask.execute();
			btnConnect.setText(R.string.btnConnect);
			btnSend.setEnabled(false);
		}

	}
	
	public void send(View view) {
		SendTask sendTask = new SendTask();
		sendTask.execute(txtText.getText().toString());
	}
	
	class ConnectDisconnectTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				if (connected == false) {
					s = new Socket(txtAddress.getText().toString(), 6000);
					in = new BufferedReader(new InputStreamReader(
							s.getInputStream()));
					out = new PrintWriter(new OutputStreamWriter(
							s.getOutputStream()), true);
					connected = true;
				} else {
					out.println("CLOSE");
					in.close();
					out.close();
					s.close();
					connected = false;
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			} catch (IOException e) {
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
			return null;
		}
	}
	
	class SendTask extends AsyncTask<String, Void, Void> {
		@Override
		protected Void doInBackground(String... params) {
			out.println(params[0]);
			return null;
		} 
	}
}