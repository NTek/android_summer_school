package com.rtrk.videoview;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoViewExamplesActivity extends Activity {
    private VideoView mVideo = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mVideo = (VideoView) findViewById(R.id.videoView1);
        Button btnLocal = (Button) findViewById(R.id.btnLocal);
        btnLocal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideo.setVideoURI(Uri
                        .parse("android.resource://com.rtrk.videoview/raw/famous"));
                mVideo.setMediaController(new MediaController(
                        VideoViewExamplesActivity.this));
                mVideo.start();
                mVideo.setOnCompletionListener(new OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(getApplicationContext(),
                                "Playback finished!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        Button btnNet = (Button) findViewById(R.id.btnNet);
        btnNet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideo.setVideoURI(Uri
                        .parse("http://www.pocketjourney.com/downloads/famous.3gp"));
                mVideo.setMediaController(new MediaController(
                        VideoViewExamplesActivity.this));
                mVideo.start();
            }
        });
    }
}