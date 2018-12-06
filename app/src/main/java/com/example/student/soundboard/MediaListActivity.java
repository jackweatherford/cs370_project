package com.example.student.soundboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.student.soundboard.models.AudioFile;

import java.util.ArrayList;

public class MediaListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMediaList;
    private MediaListAdapter mediaListAdapter;
    private ArrayList<AudioFile> audioFileArraylist;

    //private ArrayList<Recording> recordingArraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            showFinalAlert(getResources().getText(R.string.sdcard_readonly));
            return;
        }
        if (status.equals(Environment.MEDIA_SHARED)) {
            showFinalAlert(getResources().getText(R.string.sdcard_shared));
            return;
        }
        if (!status.equals(Environment.MEDIA_MOUNTED)) {
            showFinalAlert(getResources().getText(R.string.no_sdcard));
            return;
        }

        setContentView(R.layout.activity_media_list);
        initViews();
        fetchAudioFiles();
    }

    private void initViews() {
        audioFileArraylist = new ArrayList<AudioFile>();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /** setting up recyclerView **/
        recyclerViewMediaList = findViewById(R.id.recycler_view_media);
        recyclerViewMediaList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        recyclerViewMediaList.setHasFixedSize(true);
    }

    private void fetchAudioFiles() {
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
                null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int idIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int dataIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int titleIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int albumIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);

            long currentId = cursor.getLong(idIndex);
            String currentData = cursor.getString(dataIndex);
            String currentTitle = cursor.getString(titleIndex);
            String currentArtist = cursor.getString(artistIndex);
            String currentAlbum = cursor.getString(albumIndex);
            audioFileArraylist.add(new AudioFile(currentData, currentTitle, currentArtist,
                    currentAlbum, currentId));
            cursor.moveToNext();
        }

        Cursor cursorExternal = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);

        cursorExternal.moveToFirst();
        while(!cursorExternal.isAfterLast()) {
            int idIndex = cursorExternal.getColumnIndex(MediaStore.Audio.Media._ID);
            int dataIndex = cursorExternal.getColumnIndex(MediaStore.Audio.Media.DATA);
            int titleIndex = cursorExternal.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistIndex = cursorExternal.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int albumIndex = cursorExternal.getColumnIndex(MediaStore.Audio.Media.ALBUM);

            long currentId = cursorExternal.getLong(idIndex);
            String currentData = cursorExternal.getString(dataIndex);
            String currentTitle = cursorExternal.getString(titleIndex);
            String currentArtist = cursorExternal.getString(artistIndex);
            String currentAlbum = cursorExternal.getString(albumIndex);
            audioFileArraylist.add(new AudioFile(currentData, currentTitle, currentArtist,
                    currentAlbum, currentId));
            cursorExternal.moveToNext();
        }
        setAdapterToRecyclerView();
    }

    private void setAdapterToRecyclerView() {
        mediaListAdapter = new MediaListAdapter(this, audioFileArraylist);
        recyclerViewMediaList.setAdapter(mediaListAdapter);
    }

    private void showFinalAlert(CharSequence message) {
        new AlertDialog.Builder(MediaListActivity.this)
                .setTitle(getResources().getText(R.string.alert_title_failure))
                .setMessage(message)
                .setPositiveButton(
                        R.string.alert_ok_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                finish();
                            }
                        })
                .setCancelable(false)
                .show();
    }
}
