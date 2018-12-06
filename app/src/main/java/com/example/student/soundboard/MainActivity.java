
package com.example.student.soundboard;
import android.content.Intent;
import android.view.View;
import android.widget.*;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;


public class MainActivity extends AppCompatActivity {

    private ImageButton playButton;
    private ImageButton recordButton;
    private ImageButton editboardButton;

    private ImageButton editlistButton;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(this, R.raw.test);
/*        playButton = findViewById(R.id.imageButtonPlay);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    mp.pause();
                    playButton.setImageResource(R.drawable.main_play);
                }
                else {
                    mp.start();
                    playButton.setImageResource(R.drawable.main_pause);
                }
            }
        });*/
        editlistButton = findViewById(R.id.imageButtonEditSongs);
        editlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Soundlist = new Intent(MainActivity.this, Sound_listActivity.class);
                startActivity(Soundlist);
            }
        });

        recordButton = findViewById(R.id.imageButtonRecord);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecordActivity.class));
            }
        });

        editboardButton = findViewById(R.id.imageButtonEditBoard);
 /*       editboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BoardActivity.class));
            }
        });*/


}

    @Override
    protected void onPause() {
        super.onPause();
        if(mp.isPlaying()) {
            mp.pause();
            playButton.setImageResource(R.drawable.main_play);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mp.isPlaying()) {
            mp.stop();
        }
    }
