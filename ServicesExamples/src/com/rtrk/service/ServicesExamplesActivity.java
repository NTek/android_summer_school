package com.rtrk.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rtrk.service.local.LocalServiceExample;
import com.rtrk.service.remote.ICallback;
import com.rtrk.service.remote.IRemoteServiceExample;

public class ServicesExamplesActivity extends Activity {

	public static final String TAG = "ServicesExamplesActivity";
	private IRemoteServiceExample service = null;
	private BinderServiceConnection conn = null;

	Intent localServiceIntent;
	Intent remoteServiceIntent;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		localServiceIntent = new Intent(getApplicationContext(), LocalServiceExample.class);
//		remoteServiceIntent =  new Intent();
//		remoteServiceIntent.setClassName("com.rtrk.service.remote", "com.rtrk.service.remote.RemoteServiceExample");
		remoteServiceIntent = new Intent("com.rtrk.service.remote.REMOTE_SERVICE");
	}
	
	public void startLocal(View view) {
		Log.d(TAG, "startLocalService");
		startService(localServiceIntent);
	}
	
	public void stopLocal(View view) {
		Log.d(TAG, "stopLocalService");
		stopService(localServiceIntent);
	}
	
	public void bindRemote(View view) {
		Log.d(TAG, "bindRemote");
		conn = new BinderServiceConnection();
		bindService(remoteServiceIntent, conn, Context.BIND_AUTO_CREATE);
	}

	public void invokeRemote(View view) {
		Log.d(TAG, "invokeRemote");
		try {
			service.startMusic();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void unbindRemote(View view) {
		Log.d(TAG, "unbindRemote");
		unbindService(conn);
		conn = null;
	}

	class BinderServiceConnection implements ServiceConnection {
		public static final String TAG = "BinderServiceConnection";

		public void onServiceConnected(ComponentName className,
				IBinder boundService) {
			service = IRemoteServiceExample.Stub
					.asInterface((IBinder) boundService);
			Log.d(TAG, "onServiceConnected");
			try {
				service.setMusicFinishedCallback(callback.asBinder()); 
			} catch (DeadObjectException ex) {
				Log.e(TAG, "DeadObjectException");
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		public void onServiceDisconnected(ComponentName className) {
			service = null;
			Log.d(TAG, "onServiceDisconnected");
		}
	};
	

	private Handler handler = new Handler();
	private ICallback.Stub callback = new ICallback.Stub() {
		
		@Override
		public void notify(final String text) throws RemoteException {
			Log.d(TAG, "notify(" + text + ")");

			handler.post(new Runnable() {
				public void run() {
					Toast.makeText(getApplicationContext(), text,
							Toast.LENGTH_LONG).show();
				}
			}); 			
		}
	};
}