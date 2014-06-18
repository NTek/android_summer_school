package com.rtrk.service.remote;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class RemoteServiceExample extends Service {
	private static final String TAG = "RemoteServiceExample";
	MediaPlayer player;
	ICallback callback;

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind");
		
		 return new IRemoteServiceExample.Stub() {

			@Override
			public void setMusicFinishedCallback(IBinder binder) throws RemoteException {
				Log.d(TAG, "setMusicFinishedCallback");
				callback = ICallback.Stub.asInterface(binder);
			}

			@Override
			public void startMusic() throws RemoteException{
				Log.d(TAG, "startMusic");
				
				player.start();
				player.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						Log.d(TAG, "MUSIC STOPPED");
						try {
							 callback.notify("Song Ended");
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
				});
			}

			@Override
			public void stopMusic() throws RemoteException {
				Toast.makeText(getApplicationContext(), "RemoteServiceExample Stopped", Toast.LENGTH_LONG).show();
				Log.d(TAG, "stop");
				player.stop();
			}
		 };
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		player = MediaPlayer.create(this, R.raw.buzzingbee);
		player.setLooping(false); // Set looping
		Log.d(TAG, "onCreate");
		Log.d(TAG, "Player duration:" + player.getDuration());
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "RemoteServiceExample Destroyed", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onDestroy");
		player.stop();
	} 
}
