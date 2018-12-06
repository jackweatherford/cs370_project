package com.example.student.soundboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Sound_listActivity extends AppCompatActivity {

    private Button mainmenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundlist);

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