package com.example.isaac.recordactivity;

import android.support.v7.app.AppCompatActivity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import java.io.IOException;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class RecordActivity extends AppCompatActivity {

    private Button record;
    private Button stop;
    private Button play;
    private Button stopplay;
    private MediaRecorder recorder;
    private MediaPlayer player;
    private static final String LOG_TAG = "AudioRecording";
    private String outputFile;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        stop = (Button) findViewById(R.id.stopButton);
        record = (Button) findViewById(R.id.recordButton);
        stopplay = (Button) findViewById(R.id.stopPlayButton);
        play = (Button) findViewById(R.id.playButton);

        stop.setEnabled(false);
        play.setEnabled(false);
        stopplay.setEnabled(false);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/userRecordings.3gp";

        recorder = new MediaRecorder();

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        recorder.setOutputFile(outputFile);

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckPermissions()) {
                    record.setEnabled(false);
                    stop.setEnabled(true);
                    play.setEnabled(false);
                    stopplay.setEnabled(false);
                    try {
                        recorder.prepare();
                    } catch (IOException e) {
                        Log.e(LOG_TAG, "prepare() failed");
                    }
                    recorder.start();
                    Toast.makeText(getApplicationContext(),
                            "Recording Started", Toast.LENGTH_LONG).show();
                } else {
                    RequestPermissions();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recorder.stop();
                recorder.release();
                recorder = null;

                record.setEnabled(true);
                stop.setEnabled(false);
                play.setEnabled(true);
                stopplay.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Recording Stopped",
                        Toast.LENGTH_LONG).show();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setEnabled(false);
                record.setEnabled(true);
                play.setEnabled(false);
                stopplay.setEnabled(true);
                player = new MediaPlayer();
                try {
                    player.setDataSource(outputFile);
                    player.prepare();
                    player.start();
                    Toast.makeText(getApplicationContext(), "Recording Started Playing",
                            Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "prepare() failed");
                }
            }
        });

        stopplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.release();
                player = null;
                stop.setEnabled(false);
                record.setEnabled(true);
                play.setEnabled(true);
                stopplay.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Playing Audio Stopped",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length> 0) {
                    boolean permissionToRecord = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;

                    boolean permissionToStore = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(),
                                "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
    public boolean CheckPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);

        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);

        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }
    private void RequestPermissions() {
        ActivityCompat.requestPermissions(RecordActivity.this,
                new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }
}
