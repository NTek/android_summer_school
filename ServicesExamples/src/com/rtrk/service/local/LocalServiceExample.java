package com.rtrk.service.local;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LocalServiceExample extends Service {
    public static final String TAG = "LocalServiceExample";
    private Handler mHandler = null;
    private Timer mTimer = null;
    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Timer Task triggered", Toast.LENGTH_SHORT).show();
                }
            });
            Log.d(TAG, "Timer Task triggered");
        }
    };

    @Override
    public IBinder onBind(Intent arg0) {
        Log.d(TAG, "onBind() invoked");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
        mTimer = new Timer("LocalServiceExampleTimer");
        mTimer.schedule(mTimerTask, 1000L, 5 * 1000L);
        Log.d(TAG, "onCreate() finished");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() invoked");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
        mTimer = null;
        Log.d(TAG, "onDestroy() finished");
    }
}
