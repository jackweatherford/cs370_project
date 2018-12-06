package com.example.student.soundboard;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    /* Button Layout:
       1   2   3
       4   5   6
       7   8   9
     */
    private ImageButton button1;
    private TextView button1text;
    private boolean button1_enabled;
    private MediaPlayer mp1;

    private ImageButton button2;
    private TextView button2text;
    private boolean button2_enabled;
    private MediaPlayer mp2;

    private ImageButton button3;
    private TextView button3text;
    private boolean button3_enabled;
    private MediaPlayer mp3;

    private ImageButton button4;
    private TextView button4text;
    private boolean button4_enabled;
    private MediaPlayer mp4;
    private ImageButton button5;
    private TextView button5text;
    private boolean button5_enabled;
    private MediaPlayer mp5;
    private ImageButton button6;
    private TextView button6text;
    private boolean button6_enabled;
    private MediaPlayer mp6;

    private ImageButton button7;
    private TextView button7text;
    private boolean button7_enabled;
    private MediaPlayer mp7;
    private ImageButton button8;
    private TextView button8text;
    private boolean button8_enabled;
    private MediaPlayer mp8;
    private ImageButton button9;
    private TextView button9text;
    private boolean button9_enabled;
    private MediaPlayer mp9;

    private ImageButton addButton;
    private ImageButton removeButton;
    private boolean removemode = false;

    private ImageButton settingsButton;
    private boolean editmode = true;

    private SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        final SharedPreferences.Editor editor = sharedpreferences.edit();

        mp1 = new MediaPlayer();
        mp2 = new MediaPlayer();
        mp3 = new MediaPlayer();
        mp4 = new MediaPlayer();
        mp5 = new MediaPlayer();
        mp6 = new MediaPlayer();
        mp7 = new MediaPlayer();
        mp8 = new MediaPlayer();
        mp9 = new MediaPlayer();

        Intent newIntent = getIntent();
        try {
            if (newIntent.getStringExtra("MP_1") != null) {
                mp1.release();
                mp1 = new MediaPlayer();
                mp1.setDataSource(newIntent.getStringExtra("MP_1"));
                mp1.prepare();
                editor.putString("mp1_filepath", newIntent.getStringExtra("MP_1"));
                editor.apply();
            } else if (newIntent.getStringExtra("MP_2") != null) {
                mp2.release();
                mp2 = new MediaPlayer();
                mp2.setDataSource(newIntent.getStringExtra("MP_2"));
                mp2.prepare();
                editor.putString("mp2_filepath", newIntent.getStringExtra("MP_2"));
                editor.apply();
            } else if (newIntent.getStringExtra("MP_3") != null) {
                mp3.release();
                mp3 = new MediaPlayer();
                mp3.setDataSource(newIntent.getStringExtra("MP_3"));
                mp3.prepare();
                editor.putString("mp3_filepath", newIntent.getStringExtra("MP_3"));
                editor.apply();
            } else if (newIntent.getStringExtra("MP_4") != null) {
                mp4.release();
                mp4 = new MediaPlayer();
                mp4.setDataSource(newIntent.getStringExtra("MP_4"));
                mp4.prepare();
                editor.putString("mp4_filepath", newIntent.getStringExtra("MP_4"));
                editor.apply();
            } else if (newIntent.getStringExtra("MP_5") != null) {
                mp5.release();
                mp5 = new MediaPlayer();
                mp5.setDataSource(newIntent.getStringExtra("MP_5"));
                mp5.prepare();
                editor.putString("mp5_filepath", newIntent.getStringExtra("MP_5"));
                editor.apply();
            } else if (newIntent.getStringExtra("MP_6") != null) {
                mp6.release();
                mp6 = new MediaPlayer();
                mp6.setDataSource(newIntent.getStringExtra("MP_6"));
                mp6.prepare();
                editor.putString("mp6_filepath", newIntent.getStringExtra("MP_6"));
                editor.apply();
            } else if (newIntent.getStringExtra("MP_7") != null) {
                mp7.release();
                mp7 = new MediaPlayer();
                mp7.setDataSource(newIntent.getStringExtra("MP_7"));
                mp7.prepare();
                editor.putString("mp7_filepath", newIntent.getStringExtra("MP_7"));
                editor.apply();
            } else if (newIntent.getStringExtra("MP_8") != null) {
                mp8.release();
                mp8 = new MediaPlayer();
                mp8.setDataSource(newIntent.getStringExtra("MP_8"));
                mp8.prepare();
                editor.putString("mp8_filepath", newIntent.getStringExtra("MP_8"));
                editor.apply();
            } else if (newIntent.getStringExtra("MP_9") != null) {
                mp9.release();
                mp9 = new MediaPlayer();
                mp9.setDataSource(newIntent.getStringExtra("MP_9"));
                mp9.prepare();
                editor.putString("mp9_filepath", newIntent.getStringExtra("MP_9"));
                editor.apply();
            }
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare() failed ONCREATE");
        }

        button1text = findViewById(R.id.button1text);
        button2text = findViewById(R.id.button2text);
        button3text = findViewById(R.id.button3text);
        button4text = findViewById(R.id.button4text);
        button5text = findViewById(R.id.button5text);
        button6text = findViewById(R.id.button6text);
        button7text = findViewById(R.id.button7text);
        button8text = findViewById(R.id.button8text);
        button9text = findViewById(R.id.button9text);

        button1 = findViewById(R.id.imageButton1);
        button2 = findViewById(R.id.imageButton2);
        button3 = findViewById(R.id.imageButton3);
        button4 = findViewById(R.id.imageButton4);
        button5 = findViewById(R.id.imageButton5);
        button6 = findViewById(R.id.imageButton6);
        button7 = findViewById(R.id.imageButton7);
        button8 = findViewById(R.id.imageButton8);
        button9 = findViewById(R.id.imageButton9);


        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setVisibility(View.VISIBLE);
        removeButton = findViewById(R.id.removeButton);
        removeButton.setVisibility(View.VISIBLE);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removemode = false;
                removeButton.setImageResource(R.drawable.remove);
                editmodebutton(button1, button1_enabled, button1text);
                editmodebutton(button2, button2_enabled, button2text);
                editmodebutton(button3, button3_enabled, button3text);
                editmodebutton(button4, button4_enabled, button4text);
                editmodebutton(button5, button5_enabled, button5text);
                editmodebutton(button6, button6_enabled, button6text);
                editmodebutton(button7, button7_enabled, button7text);
                editmodebutton(button8, button8_enabled, button8text);
                editmodebutton(button9, button9_enabled, button9text);
                if (button1_enabled)
                    button1text.setText("\nEDIT");
                else if (button2_enabled)
                    button2text.setText("\nEDIT");
                else if (button3_enabled)
                    button3text.setText("\nEDIT");
                else if (button4_enabled)
                    button4text.setText("\nEDIT");
                else if (button5_enabled)
                    button5text.setText("\nEDIT");
                else if (button6_enabled)
                    button6text.setText("\nEDIT");
                else if (button7_enabled)
                    button7text.setText("\nEDIT");
                else if (button8_enabled)
                    button8text.setText("\nEDIT");
                else if (button9_enabled)
                    button9text.setText("\nEDIT");

                if (!button1_enabled)
                    button1_enabled = handleButton(button1, button1text, button1_enabled, mp1);
                else if (!button2_enabled)
                    button2_enabled = handleButton(button2, button2text, button2_enabled, mp2);
                else if (!button3_enabled)
                    button3_enabled = handleButton(button3, button3text, button3_enabled, mp3);
                else if (!button4_enabled)
                    button4_enabled = handleButton(button4, button4text, button4_enabled, mp4);
                else if (!button5_enabled)
                    button5_enabled = handleButton(button5, button5text, button5_enabled, mp5);
                else if (!button6_enabled)
                    button6_enabled = handleButton(button6, button6text, button6_enabled, mp6);
                else if (!button7_enabled)
                    button7_enabled = handleButton(button7, button7text, button7_enabled, mp7);
                else if (!button8_enabled)
                    button8_enabled = handleButton(button8, button8text, button8_enabled, mp8);
                else if (!button9_enabled)
                    button9_enabled = handleButton(button9, button9text, button9_enabled, mp9);
                settingsButton.setVisibility(View.VISIBLE);
                removeButton.setVisibility(View.VISIBLE);
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!removemode) {
                    removeButton.setImageResource(R.drawable.edit);
                    removemodebutton(button1, button1_enabled, button1text);
                    removemodebutton(button2, button2_enabled, button2text);
                    removemodebutton(button3, button3_enabled, button3text);
                    removemodebutton(button4, button4_enabled, button4text);
                    removemodebutton(button5, button5_enabled, button5text);
                    removemodebutton(button6, button6_enabled, button6text);
                    removemodebutton(button7, button7_enabled, button7text);
                    removemodebutton(button8, button8_enabled, button8text);
                    removemodebutton(button9, button9_enabled, button9text);
                }
                else {
                    removeButton.setImageResource(R.drawable.remove);
                    editmodebutton(button1, button1_enabled, button1text);
                    editmodebutton(button2, button2_enabled, button2text);
                    editmodebutton(button3, button3_enabled, button3text);
                    editmodebutton(button4, button4_enabled, button4text);
                    editmodebutton(button5, button5_enabled, button5text);
                    editmodebutton(button6, button6_enabled, button6text);
                    editmodebutton(button7, button7_enabled, button7text);
                    editmodebutton(button8, button8_enabled, button8text);
                    editmodebutton(button9, button9_enabled, button9text);
                }
                removemode = !removemode;
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editmode) {
                    settingsButton.setImageResource(R.drawable.cog);
                    addButton.setVisibility(View.GONE);
                    removeButton.setVisibility(View.GONE);
                }
                else {
                    removeButton.setImageResource(R.drawable.remove);
                    if(mp1.isPlaying())
                        mp1.stop();
                    if(mp2.isPlaying())
                        mp2.stop();
                    if(mp3.isPlaying())
                        mp3.stop();
                    if(mp4.isPlaying())
                        mp4.stop();
                    if(mp5.isPlaying())
                        mp5.stop();
                    if(mp6.isPlaying())
                        mp6.stop();
                    if(mp7.isPlaying())
                        mp7.stop();
                    if(mp8.isPlaying())
                        mp8.stop();
                    if(mp9.isPlaying())
                        mp9.stop();
                    settingsButton.setImageResource(R.drawable.save);
                    addButton.setVisibility(View.VISIBLE);
                    if (button1_enabled | button2_enabled | button3_enabled | button4_enabled |
                            button5_enabled | button6_enabled | button7_enabled | button8_enabled |
                            button9_enabled)
                        removeButton.setVisibility(View.VISIBLE);
                }
                removemode = false;
                editmode = !editmode;
                normalmodebutton(button1, button1_enabled, button1text);
                normalmodebutton(button2, button2_enabled, button2text);
                normalmodebutton(button3, button3_enabled, button3text);
                normalmodebutton(button4, button4_enabled, button4text);
                normalmodebutton(button5, button5_enabled, button5text);
                normalmodebutton(button6, button6_enabled, button6text);
                normalmodebutton(button7, button7_enabled, button7text);
                normalmodebutton(button8, button8_enabled, button8text);
                normalmodebutton(button9, button9_enabled, button9text);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1_enabled = handleButton(button1, button1text, button1_enabled, mp1);
                editor.putBoolean("button1_enabled", button1_enabled);
                editor.apply();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button2_enabled = handleButton(button2, button2text, button2_enabled, mp2);
                editor.putBoolean("button2_enabled", button2_enabled);
                editor.apply();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button3_enabled = handleButton(button3, button3text, button3_enabled, mp3);
                editor.putBoolean("button3_enabled", button3_enabled);
                editor.apply();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button4_enabled = handleButton(button4, button4text, button4_enabled, mp4);
                editor.putBoolean("button4_enabled", button4_enabled);
                editor.apply();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button5_enabled = handleButton(button5, button5text, button5_enabled, mp5);
                editor.putBoolean("button5_enabled", button5_enabled);
                editor.apply();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button6_enabled = handleButton(button6, button6text, button6_enabled, mp6);
                editor.putBoolean("button6_enabled", button6_enabled);
                editor.apply();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button7_enabled = handleButton(button7, button7text, button7_enabled, mp7);
                editor.putBoolean("button7_enabled", button7_enabled);
                editor.apply();
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button8_enabled = handleButton(button8, button8text, button8_enabled, mp8);
                editor.putBoolean("button8_enabled", button8_enabled);
                editor.apply();
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button9_enabled = handleButton(button9, button9text, button9_enabled, mp9);
                editor.putBoolean("button9_enabled", button9_enabled);
                editor.apply();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mp1.isPlaying())
            mp1.pause();
        if(mp2.isPlaying())
            mp2.pause();
        if(mp3.isPlaying())
            mp3.pause();
        if(mp4.isPlaying())
            mp4.pause();
        if(mp5.isPlaying())
            mp5.pause();
        if(mp6.isPlaying())
            mp6.pause();
        if(mp7.isPlaying())
            mp7.pause();
        if(mp8.isPlaying())
            mp8.pause();
        if(mp9.isPlaying())
            mp9.pause();
        mp1.release();
        mp2.release();
        mp3.release();
        mp4.release();
        mp5.release();
        mp6.release();
        mp7.release();
        mp8.release();
        mp9.release();

        final SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putBoolean("button1_enabled", button1_enabled);
        editor.putBoolean("button2_enabled", button2_enabled);
        editor.putBoolean("button3_enabled", button3_enabled);
        editor.putBoolean("button4_enabled", button4_enabled);
        editor.putBoolean("button5_enabled", button5_enabled);
        editor.putBoolean("button6_enabled", button6_enabled);
        editor.putBoolean("button7_enabled", button7_enabled);
        editor.putBoolean("button8_enabled", button8_enabled);
        editor.putBoolean("button9_enabled", button9_enabled);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        button1_enabled = sharedpreferences.getBoolean("button1_enabled", false);
        String color = sharedpreferences.getString("button1_color", "None");
        String img = sharedpreferences.getString("button1_image", "None");
        String filepath = sharedpreferences.getString("mp1_filepath", "None");
        loadbutton(button1, button1_enabled, button1text, color, img, filepath, mp1);
        try {
            mp1 = new MediaPlayer();
            mp1.setDataSource(filepath);
            mp1.prepare();
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare() failed");
        }

        button2_enabled = sharedpreferences.getBoolean("button2_enabled", false);
        color = sharedpreferences.getString("button2_color", "None");
        img = sharedpreferences.getString("button2_image", "None");
        filepath = sharedpreferences.getString("mp2_filepath", "None");
        loadbutton(button2, button2_enabled, button2text, color, img, filepath, mp2);
        try {
            mp2 = new MediaPlayer();
            mp2.setDataSource(filepath);
            mp2.prepare();
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare() failed");
        }

        button3_enabled = sharedpreferences.getBoolean("button3_enabled", false);
        color = sharedpreferences.getString("button3_color", "None");
        img = sharedpreferences.getString("button3_image", "None");
        filepath = sharedpreferences.getString("mp3_filepath", "None");
        loadbutton(button3, button3_enabled, button3text, color, img, filepath, mp3);
        try {
            mp3 = new MediaPlayer();
            mp3.setDataSource(filepath);
            mp3.prepare();
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare() failed");
        }

        button4_enabled = sharedpreferences.getBoolean("button4_enabled", false);
        color = sharedpreferences.getString("button4_color", "None");
        img = sharedpreferences.getString("button4_image", "None");
        filepath = sharedpreferences.getString("mp4_filepath", "None");
        loadbutton(button4, button4_enabled, button4text, color, img, filepath, mp4);
        try {
            mp4 = new MediaPlayer();
            mp4.setDataSource(filepath);
            mp4.prepare();
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare() failed");
        }

        button5_enabled = sharedpreferences.getBoolean("button5_enabled", false);
        color = sharedpreferences.getString("button5_color", "None");
        img = sharedpreferences.getString("button5_image", "None");
        filepath = sharedpreferences.getString("mp5_filepath", "None");
        loadbutton(button5, button5_enabled, button5text, color, img, filepath, mp5);
        try {
            mp5 = new MediaPlayer();
            mp5.setDataSource(filepath);
            mp5.prepare();
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare() failed");
        }

        button6_enabled = sharedpreferences.getBoolean("button6_enabled", false);
        color = sharedpreferences.getString("button6_color", "None");
        img = sharedpreferences.getString("button6_image", "None");
        filepath = sharedpreferences.getString("mp6_filepath", "None");
        loadbutton(button6, button6_enabled, button6text, color, img, filepath, mp6);
        try {
            mp6 = new MediaPlayer();
            mp6.setDataSource(filepath);
            mp6.prepare();
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare() failed");
        }

        button7_enabled = sharedpreferences.getBoolean("button7_enabled", false);
        color = sharedpreferences.getString("button7_color", "None");
        img = sharedpreferences.getString("button7_image", "None");
        filepath = sharedpreferences.getString("mp7_filepath", "None");
        loadbutton(button7, button7_enabled, button7text, color, img, filepath, mp7);
        try {
            mp7 = new MediaPlayer();
            mp7.setDataSource(filepath);
            mp7.prepare();
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare() failed");
        }

        button8_enabled = sharedpreferences.getBoolean("button8_enabled", false);
        color = sharedpreferences.getString("button8_color", "None");
        img = sharedpreferences.getString("button8_image", "None");
        filepath = sharedpreferences.getString("mp8_filepath", "None");
        loadbutton(button8, button8_enabled, button8text, color, img, filepath, mp8);
        try {
            mp8 = new MediaPlayer();
            mp8.setDataSource(filepath);
            mp8.prepare();
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare() failed");
        }

        button9_enabled = sharedpreferences.getBoolean("button9_enabled", false);
        color = sharedpreferences.getString("button9_color", "None");
        img = sharedpreferences.getString("button9_image", "None");
        filepath = sharedpreferences.getString("mp9_filepath", "None");
        loadbutton(button9, button9_enabled, button9text, color, img, filepath, mp9);
        try {
            mp9 = new MediaPlayer();
            mp9.setDataSource(filepath);
            mp9.prepare();
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare() failed");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /*switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/
        Intent intent = new Intent(BoardActivity.this, MainActivity.class);
        startActivity(intent);
        return true;
    }

    protected boolean handleButton(final ImageButton button, TextView buttontext, boolean button_enabled, MediaPlayer mp) {
        final SharedPreferences.Editor editor = sharedpreferences.edit();

        if (removemode) {
            button.setVisibility(View.GONE);
            button.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            buttontext.setText("");
            button.setImageResource(android.R.color.transparent);
            if (button.getId() == R.id.imageButton1) {
                editor.putString("button1_color", "None");
                editor.putString("button1_image", "None");
                editor.apply();
            }
            else if (button.getId() == R.id.imageButton2) {
                editor.putString("button2_color", "None");
                editor.putString("button2_image", "None");
                editor.apply();
            }
            else if (button.getId() == R.id.imageButton3) {
                editor.putString("button3_color", "None");
                editor.putString("button3_image", "None");
                editor.apply();
            }
            else if (button.getId() == R.id.imageButton4) {
                editor.putString("button4_color", "None");
                editor.putString("button4_image", "None");
                editor.apply();
            }
            else if (button.getId() == R.id.imageButton5) {
                editor.putString("button5_color", "None");
                editor.putString("button5_image", "None");
                editor.apply();
            }
            else if (button.getId() == R.id.imageButton6) {
                editor.putString("button6_color", "None");
                editor.putString("button6_image", "None");
                editor.apply();
            }
            else if (button.getId() == R.id.imageButton7) {
                editor.putString("button7_color", "None");
                editor.putString("button7_image", "None");
                editor.apply();
            }
            else if (button.getId() == R.id.imageButton8) {
                editor.putString("button8_color", "None");
                editor.putString("button8_image", "None");
                editor.apply();
            }
            else if (button.getId() == R.id.imageButton9) {
                editor.putString("button9_color", "None");
                editor.putString("button9_image", "None");
                editor.apply();
            }
            return false;
        }
        else if (editmode) {
            final String[] colors = {"Red", "Green", "Blue", "Yellow", "White"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Button color:");
            builder.setItems(colors, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String color = colors[which];
                    if (color.equals("Red")) {
                        button.setBackgroundColor(Color.RED);
                    }
                    else if (color.equals("Green")) {
                        button.setBackgroundColor(Color.GREEN);
                    }
                    else if (color.equals("Blue")) {
                        button.setBackgroundColor(Color.BLUE);
                    }
                    else if (color.equals("Yellow")) {
                        button.setBackgroundColor(Color.YELLOW);
                    }
                    else {
                        button.setBackgroundColor(Color.WHITE);
                    }
                    if (button.getId() == R.id.imageButton1) {
                        editor.putString("button1_color", color);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton2) {
                        editor.putString("button2_color", color);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton3) {
                        editor.putString("button3_color", color);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton4) {
                        editor.putString("button4_color", color);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton5) {
                        editor.putString("button5_color", color);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton6) {
                        editor.putString("button6_color", color);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton7) {
                        editor.putString("button7_color", color);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton8) {
                        editor.putString("button8_color", color);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton9) {
                        editor.putString("button9_color", color);
                        editor.apply();
                    }
                    // Ask for Permission to access files:
                    getPermissionToAcessFiles();
                    // Start Activity:
                    Intent intent = new Intent(BoardActivity.this, RecordingListSelectActivity.class);
                    String id = "0";
                    if (button.getId() == R.id.imageButton1)
                        id = "1";
                    if (button.getId() == R.id.imageButton2)
                        id = "2";
                    if (button.getId() == R.id.imageButton3)
                        id = "3";
                    if (button.getId() == R.id.imageButton4)
                        id = "4";
                    if (button.getId() == R.id.imageButton5)
                        id = "5";
                    if (button.getId() == R.id.imageButton6)
                        id = "6";
                    if (button.getId() == R.id.imageButton7)
                        id = "7";
                    if (button.getId() == R.id.imageButton8)
                        id = "8";
                    if (button.getId() == R.id.imageButton9)
                        id = "9";

                    intent.putExtra(RecordingListSelectActivity.BUTTON_ID, id);
                    startActivity(intent);
                }
            });

            final String[] imgs = {"Flame", "Leaf", "Water Droplet", "Music Note", "None"};

            AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
            builder2.setTitle("Button image:");
            builder2.setItems(imgs, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String img = imgs[which];
                    if (img.equals("Music Note"))
                        button.setImageResource(R.drawable.note);
                    else if (img.equals("Leaf"))
                        button.setImageResource(R.drawable.leaf);
                    else if (img.equals("Water Droplet"))
                        button.setImageResource(R.drawable.water);
                    else if (img.equals("Flame"))
                        button.setImageResource(R.drawable.flame);
                    else
                        button.setImageResource(android.R.color.transparent);
                    if (button.getId() == R.id.imageButton1) {
                        editor.putString("button1_image", img);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton2) {
                        editor.putString("button2_image", img);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton3) {
                        editor.putString("button3_image", img);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton4) {
                        editor.putString("button4_image", img);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton5) {
                        editor.putString("button5_image", img);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton6) {
                        editor.putString("button6_image", img);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton7) {
                        editor.putString("button7_image", img);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton8) {
                        editor.putString("button8_image", img);
                        editor.apply();
                    }
                    else if (button.getId() == R.id.imageButton9) {
                        editor.putString("button9_image", img);
                        editor.apply();
                    }
                }
            });
            builder.show();
            builder2.show();

            button.setVisibility(View.VISIBLE);
            buttontext.setText("\nEDIT");
            buttontext.setVisibility(View.VISIBLE);
            return true;
        }
        else if (button_enabled) {
            if(mp1.isPlaying())
                mp1.pause();
            if(mp2.isPlaying())
                mp2.pause();
            if(mp3.isPlaying())
                mp3.pause();
            if(mp4.isPlaying())
                mp4.pause();
            if(mp5.isPlaying())
                mp5.pause();
            if(mp6.isPlaying())
                mp6.pause();
            if(mp7.isPlaying())
                mp7.pause();
            if(mp8.isPlaying())
                mp8.pause();
            if(mp9.isPlaying())
                mp9.pause();

            mp.start();
            return true;
        }
        return false;
    }

    protected void removemodebutton(ImageButton button, boolean button_enabled, TextView buttontext) {
        if (button_enabled)
            buttontext.setText("\nDELETE");
    }

    protected void normalmodebutton(ImageButton button, boolean button_enabled, TextView buttontext) {
        if (button_enabled)
            if (editmode) {
                buttontext.setText("\nEDIT");
                buttontext.setVisibility(View.VISIBLE);
            }
            else
                buttontext.setText("");
        else {
            button.setVisibility(View.GONE);
            buttontext.setText("");
        }
    }

    protected void editmodebutton(ImageButton button, boolean button_enabled, TextView buttontext) {
        if (button_enabled) {
            buttontext.setVisibility(View.VISIBLE);
            buttontext.setText("\nEDIT");
        }
    }

    protected void loadbutton(ImageButton button, boolean button_enabled, TextView buttontext,
                              String color, String img, String filepath, MediaPlayer mp) {
        if (button_enabled) {
            buttontext.setText("\nEDIT");
            buttontext.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
        }

        if (color.equals("Red")) {
            button.setBackgroundColor(Color.RED);
        }
        else if (color.equals("Green")) {
            button.setBackgroundColor(Color.GREEN);
        }
        else if (color.equals("Blue")) {
            button.setBackgroundColor(Color.BLUE);
        }
        else if (color.equals("Yellow")) {
            button.setBackgroundColor(Color.YELLOW);
        }
        else if (color.equals("White")){
            button.setBackgroundColor(Color.WHITE);
        }
        else
            button.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

        if (img.equals("Music Note"))
            button.setImageResource(R.drawable.note);
        else if (img.equals("Leaf"))
            button.setImageResource(R.drawable.leaf);
        else if (img.equals("Water Droplet"))
            button.setImageResource(R.drawable.water);
        else if (img.equals("Flame"))
            button.setImageResource(R.drawable.flame);
        else
            button.setImageResource(android.R.color.transparent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPermissionToAcessFiles() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, R.string.file_access_request_code);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        if (requestCode == R.string.file_access_request_code) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED){

            } else {
                Toast.makeText(this, "You must give permissions to use this app. " +
                        "App is exiting.", Toast.LENGTH_SHORT).show();
                finishAffinity();
            }
        }

    }
}
