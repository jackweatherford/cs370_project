package com.example.student.soundboard;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.student.soundboard.models.AudioFile;

import java.util.ArrayList;

public class MediaListAdapter  extends RecyclerView.Adapter<MediaListAdapter.MediaListViewHolder>{

    private Context context;
    private String[] media;
    private ArrayList<AudioFile> audioFileArrayList;

    public MediaListAdapter(Context context, ArrayList<AudioFile> audioFileArrayList){
        this.context = context;
        this.audioFileArrayList = audioFileArrayList;
    }

    @Override
    public MediaListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_media_list_support,
                parent, false);
        return new MediaListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MediaListViewHolder holder, int position) {

        setUpData(holder,position);

    }

    private void setUpData(MediaListViewHolder holder, int position) {

        AudioFile audioFile = audioFileArrayList.get(position);
        holder.audioTitle.setText(audioFile.getTitle());
        holder.audioAlbum.setText(audioFile.getAlbum());
        holder.audioArtist.setText(audioFile.getArtist());
        holder.audioData.setText(audioFile.getData());
    }

    @Override
    public int getItemCount() {

        return audioFileArrayList.size();
    }

    public class MediaListViewHolder extends RecyclerView.ViewHolder {

        ImageView audioIcon, audioOptionsTitle;
        TextView audioAlbum, audioArtist, audioTitle, audioData;
        MediaListViewHolder holder;

        public MediaListViewHolder(View itemView) {
            super(itemView);

            audioIcon = itemView.findViewById(R.id.audio_icon);
            audioOptionsTitle = itemView.findViewById(R.id.audio_options_button);
            audioAlbum = itemView.findViewById(R.id.audio_album);
            audioArtist = itemView.findViewById(R.id.audio_artist);
            audioTitle = itemView.findViewById(R.id.audio_title);
            audioData = itemView.findViewById(R.id.audio_data);

            audioOptionsTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //int position = getAdapterPosition();
                    startAudioEditor(view, audioData.getText().toString());
                }
            });
        }

        private void startAudioEditor(View view, String data) {
            try {
                Intent intent = new Intent(Intent.ACTION_EDIT, Uri.parse(data));
                intent.setClassName("com.example.student.soundboard",
                        "com.example.student.soundboard.EditExistingAudio");
                view.getContext().startActivity(intent);
            } catch (Exception e) {
                Log.e("newrecord", "Couldn't start editor");
            }
        }
    }
}