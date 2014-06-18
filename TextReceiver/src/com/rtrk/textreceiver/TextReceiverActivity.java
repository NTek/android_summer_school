package com.rtrk.textreceiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TextReceiverActivity extends Activity {
	public static final String TAG = "TextReceiverActivity";
	ServerSocket ss;
	Socket s;
	BufferedReader in;
	PrintWriter out;
	EditText txtText;
	Button btnStartListening;
	boolean listening;
	TextTask task;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		txtText = (EditText) findViewById(R.id.editText);
		btnStartListening = (Button) findViewById(R.id.btnListen);
		TextView textView1 = (TextView)findViewById(R.id.textView1);

		listening = false;
		try {
			ss = new ServerSocket(6000);
			textView1.setText("My IP addr: " + getLocalIpAddress());
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
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

	public void startListening(View view) {
		if (listening == false) {
			listening = true;
			task = new TextTask();
			task.execute();
			btnStartListening.setText(R.string.btnStopListen);
		} else {
			listening = false;
			task.cancel(true);
			btnStartListening.setText(R.string.btnListen);
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

	class TextTask extends AsyncTask<Void, String, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {
				Log.d(TAG, "WAITING FOR THE CLIENTS");
				s = ss.accept();
				in = new BufferedReader(new InputStreamReader(
						s.getInputStream()));
				out = new PrintWriter(new OutputStreamWriter(
						s.getOutputStream()), true);

				Log.d(TAG, "ACCEPTED CLIENT" + s.getInetAddress());
				while (true) {
					String line = in.readLine();
					Log.d(TAG, "RECIVED:" + line);
					if (line.equals("CLOSE"))
						break;
					else
						publishProgress(line);
				}
				in.close();
				out.close();
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();
			}

			return null;
		}
		@Override
		protected void onProgressUpdate(String ... value) {
			txtText.append(value[0] + "\n");
		}
		@Override
		protected void onPostExecute(Void result) {
			btnStartListening.setText(R.string.btnListen);
			listening = false;
		}
	}
}