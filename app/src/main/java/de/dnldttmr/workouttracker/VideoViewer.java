package de.dnldttmr.workouttracker;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewer extends AppCompatActivity {

    private VideoView vv_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_viewer);

        vv_video = (VideoView) findViewById(R.id.vv_videoViewer);
        Intent intent = getIntent();
        String video = intent.getStringExtra("videoName");

        vv_video.setMediaController(new MediaController(this));
        String file = "android.resource://" + getPackageName() + "/raw/" + video;
        vv_video.setVideoURI(Uri.parse(file));
        vv_video.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        vv_video.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        vv_video.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vv_video.pause();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
