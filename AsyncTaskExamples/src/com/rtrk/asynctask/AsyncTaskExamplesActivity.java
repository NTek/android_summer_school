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
	ProgressBar pBar;
	Handler handler;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        pBar = (ProgressBar)findViewById(R.id.progressBar1);
        handler = new Handler();
        
        Button btnThread = (Button)findViewById(R.id.btnThread);
        btnThread.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				threadDemo();
			}

		});

        Button btnAsync = (Button)findViewById(R.id.btnAsync);
        btnAsync.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				asyncDemo();
			}

		});
        
    }
    
	private void threadDemo() {
		Thread t = new Thread() {
			int count = 0;
			public void run() {
				count = 0;
				updateUI();
				
				for (int i = 1; i <= 5; i++) {
					count = i * 20;
					// update UI
					updateUI();
					// sleep
					try { sleep(1000); } catch (InterruptedException e) { e.printStackTrace();}
				}
				toast();
			}
			private void updateUI() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						pBar.setProgress(count);
					}
				});
			}

			private void toast() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "Job completed", Toast.LENGTH_LONG).show();
					}
				});
			}

		};
		t.start();
	}
	
	private void asyncDemo() {
		ProgressBarTask task = new ProgressBarTask();
		task.execute(1);
	}


	/** <InitParameterType, ProgressType, ResultType>
	 * 
	 * @author minja
	 *
	 */
	class ProgressBarTask extends AsyncTask<Integer, Integer, Integer> {
		int count = 0;
		
		@Override
		protected Integer doInBackground(Integer... params) {
			count = 0;
			publishProgress(count);

			int start = params[0];
			for (int i = start; i <= 5; i++) {
				count = i * 20;
				// call onProgressUpdate, which will updateUI
				publishProgress(count);
				// sleep
				try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace();}
			}
			// the value that we return here will go to the onPostExecute() as an argument
			return count;
		}
		
		@Override
		protected void onProgressUpdate(Integer ... value) {
			int current = value[0];
			pBar.setProgress(current);
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			Toast.makeText(getApplicationContext(), "Job completed: " + result, Toast.LENGTH_LONG).show();
		}

	}	
	
}