package com.example.student.soundboard;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.File;
import java.io.IOException;

public class Sound_listActivity extends AppCompatActivity {

    private Button mainmenuButton;
    private RecyclerView recyclerView;
    private Button listButton;
    private ImageButton pauseButton;
    MediaPlayer mp;

    FilePath List = new FilePath(Environment.getExternalStorageDirectory().toString()+"/SoundBoard/PlayList");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundlist);

        recyclerView = findViewById(R.id.recycler_file_name);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);

        List.setPath(Environment.getExternalStorageDirectory().toString()+"/SoundBoard/PlayList");
        Log.d("Files", "Path: " + List.getPath());
        // List.setDirectory(new File(List.getPath()));
        //List.setFiles(List.getDirectory().listFiles());
        Log.d("Files", "Size: "+ List.size());

        ListViewAdapter adapter = new ListViewAdapter(List.getFiles());
        recyclerView.setAdapter(adapter);

        mainmenuButton = findViewById(R.id.main_menu);
        mainmenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MainActivity = new Intent(Sound_listActivity.this, MainActivity.class);

                startActivity(MainActivity);
            }
        });
        pauseButton = findViewById(R.id.pause_button);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                mp.stop();
                try {
                    mp.setDataSource(List.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.start();
                pauseButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop();
                pauseButton.setVisibility(View.GONE);
            }
        });
    }
}