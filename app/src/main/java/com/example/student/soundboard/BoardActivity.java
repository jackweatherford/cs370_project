package com.example.student.soundboard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

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
    private String mp1_filepath;

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

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

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

        /*SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        button1.setBackgroundColor(sharedPref.getInt("button1color", getResources()
                .getColor(android.R.color.darker_gray)));
        button1_enabled = sharedPref.getBoolean("button1enabled", false);
        String img = sharedPref.getString("button1img", "None");
        if (img.equals("Flame"))
            button1.setImageResource(R.drawable.flame);
        else if (img.equals("Leaf"))
            button1.setImageResource(R.drawable.leaf);
        else if (img.equals("Water Droplet"))
            button1.setImageResource(R.drawable.water);
        else if (img.equals("Music Note"))
            button1.setImageResource(R.drawable.note);
        else
            button1.setImageResource(android.R.color.transparent);*/

        mp1 = MediaPlayer.create(this, R.raw.test);
        mp2 = MediaPlayer.create(this, R.raw.test);
        mp3 = MediaPlayer.create(this, R.raw.test);
        mp4 = MediaPlayer.create(this, R.raw.test);
        mp5 = MediaPlayer.create(this, R.raw.test);
        mp6 = MediaPlayer.create(this, R.raw.test);
        mp7 = MediaPlayer.create(this, R.raw.test);
        mp8 = MediaPlayer.create(this, R.raw.test);
        mp9 = MediaPlayer.create(this, R.raw.test);

        settingsButton = findViewById(R.id.settingsButton);
        removeButton = findViewById(R.id.removeButton);

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

                if (!button1_enabled) {
                    button1_enabled = handleButton(button1, button1text, button1_enabled, mp1);
                }
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
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button2_enabled = handleButton(button2, button2text, button2_enabled, mp2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button3_enabled = handleButton(button3, button3text, button3_enabled, mp3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button4_enabled = handleButton(button4, button4text, button4_enabled, mp4);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button5_enabled = handleButton(button5, button5text, button5_enabled, mp5);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button6_enabled = handleButton(button6, button6text, button6_enabled, mp6);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button7_enabled = handleButton(button7, button7text, button7_enabled, mp7);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button8_enabled = handleButton(button8, button8text, button8_enabled, mp8);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button9_enabled = handleButton(button9, button9text, button9_enabled, mp9);
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
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    protected boolean handleButton(final ImageButton button, TextView buttontext, boolean button_enabled, MediaPlayer mp) {
        if (removemode) {
            button.setVisibility(View.GONE);
            button.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            buttontext.setText("");
            button.setImageResource(android.R.color.transparent);
            return false;
        }
        else if (editmode) {
            // TODO: Let user select audio of current button (jump to Sound List Activity)
            // mp.setDataSource(Environment.getExternalStorageDirectory().toString()+"/SoundBoard/PlayList");
            // editor.putString("mp1filepath", mp1_filepath);
            // editor.apply();

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
                }
            });
            builder.show();

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
                }
            });
            builder2.show();

            button.setVisibility(View.VISIBLE);
            buttontext.setText("\nEDIT");
            buttontext.setVisibility(View.VISIBLE);
            return true;
        }
        else if (button_enabled) {
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
}
