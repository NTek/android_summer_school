package com.rtrk.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AsyncTaskExamplesActivity extends Activity {
    /** Fields. */
    private ProgressBar mBar = null;;
    private Handler mHandler = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mBar = (ProgressBar) findViewById(R.id.progressBar);
        /** Handler has to be defined outside thread, not it thread! */
        mHandler = new Handler();
        /** Get Button Instance. */
        Button btnThread = (Button) findViewById(R.id.btnThread);
        /** Add ClickListener. */
        btnThread.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                threadDemo();
            }
        });
        Button btnAsync = (Button) findViewById(R.id.btnAsync);
        btnAsync.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncDemo();
            }
        });
    }

    /** Create and Start Thread. */
    private void threadDemo() {
        Thread lThread = new Thread() {
            int mThreadCount = 0;

            public void run() {
                mThreadCount = 0;
                updateUI();
                for (int i = 1; i <= 5; i++) {
                    mThreadCount = i * 20;
                    /** Update UI. */
                    updateUI();
                    /** Sleep a second. */
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                toast();
            }

            /** Update UI. */
            private void updateUI() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        /** Update Progress Bar. */
                        mBar.setProgress(mThreadCount);
                    }
                });
            }

            /** Show Toast. */
            private void toast() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Job completed", Toast.LENGTH_LONG).show();
                    }
                });
            }
        };
        lThread.start();
    }

    private void asyncDemo() {
        ProgressBarTask task = new ProgressBarTask();
        task.execute(1);
    }

    /** Update UI with AsyncTask. */
    private class ProgressBarTask extends AsyncTask<Integer, Integer, Integer> {
        int mAsyncCount = 0;

        @Override
        protected Integer doInBackground(Integer... params) {
            mAsyncCount = 0;
            publishProgress(mAsyncCount);
            int start = params[0];
            for (int i = start; i <= 5; i++) {
                mAsyncCount = i * 20;
                /** Call onProgressUpdate, which will updateUI. */
                publishProgress(mAsyncCount);
                /** Sleep. */
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            /**
             * The value that we return here will go to the onPostExecute() as
             * an argument.
             */
            return mAsyncCount;
        }

        @Override
        protected void onProgressUpdate(Integer... value) {
            int current = value[0];
            mBar.setProgress(current);
        }

        @Override
        protected void onPostExecute(Integer result) {
            Toast.makeText(getApplicationContext(), "Job completed: " + result,
                    Toast.LENGTH_LONG).show();
        }
    }
}