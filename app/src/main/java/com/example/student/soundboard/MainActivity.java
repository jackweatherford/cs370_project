
package com.example.student.soundboard;
import android.view.View;
import android.widget.*;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;


public class MainActivity extends AppCompatActivity {

    private ImageButton playButton;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(this, R.raw.test);
        playButton = findViewById(R.id.imageButtonPlay);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    mp.pause();
                    playButton.setImageResource(R.drawable.play);
                }
                else {
                    mp.start();
                    playButton.setImageResource(R.drawable.pause);
                }
            }
        });

}

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
