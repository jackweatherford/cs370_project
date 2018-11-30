package com.example.student.soundboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.io.File;

public class Sound_listActivity extends AppCompatActivity {

    private Button mainmenuButton;
    private RecyclerView recyclerView;
    FilePath List = new FilePath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundlist);

        recyclerView = findViewById(R.id.recycler_file_name);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);

        List.setPath(Environment.getExternalStorageDirectory().toString()+"/Pictures");
        Log.d("Files", "Path: " + List.getPath());
        List.setDirectory(new File(List.getPath()));
        List.setFiles(List.getDirectory().listFiles());
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

    }
}