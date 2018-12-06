package com.example.student.soundboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.student.soundboard.models.Recording;

import java.util.ArrayList;

public class RecordingSelectionAdaptor extends RecyclerView.Adapter<RecordingSelectionAdaptor.ViewHolder>{

    private Context context;
    private ArrayList<Recording> recordingArrayList;
    private String id;
    private int last_index = -1;

    private int selectedPos = RecyclerView.NO_POSITION;

    public interface AdapterCallback{
        void onItemClicked(int position);
    }
    AdapterCallback callback;

    public RecordingSelectionAdaptor(Context context, ArrayList<Recording> recordingArrayList, String id, AdapterCallback callback){
        this.context = context;
        this.recordingArrayList = recordingArrayList;
        this.id = id;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recording_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setSelected(selectedPos == position);
        setUpData(holder,position);
    }

    private void setUpData(ViewHolder holder, int position) {

        Recording recording = recordingArrayList.get(position);
        holder.textViewName.setText(recording.getFileName());

        holder.imageViewPlay.setImageResource(R.drawable.green);
        TransitionManager.beginDelayedTransition((ViewGroup) holder.itemView);

    }

    @Override
    public int getItemCount() {
        return recordingArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewPlay;
        TextView textViewName;
        private String recordingUri;

        public ViewHolder(View itemView) {
            super(itemView);

            imageViewPlay = itemView.findViewById(R.id.imageViewPlay);
            textViewName = itemView.findViewById(R.id.textViewRecordingName);

            imageViewPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Recording recording = recordingArrayList.get(position);

                    recordingUri = recording.getUri();

                    notifyItemChanged(selectedPos);
                    selectedPos = getLayoutPosition();
                    notifyItemChanged(selectedPos);

                    notifyItemChanged(position);
                    last_index = position;


                    Intent intent = new Intent(context, BoardActivity.class);
                    if (id.equals("1")) {
                        intent.putExtra("MP_1", recordingUri);
                    }
                    else if (id.equals("2")) {
                        intent.putExtra("MP_2", recordingUri);
                    }
                    else if (id.equals("3")) {
                        intent.putExtra("MP_3", recordingUri);
                    }
                    else if (id.equals("4")) {
                        intent.putExtra("MP_4", recordingUri);
                    }
                    else if (id.equals("5")) {
                        intent.putExtra("MP_5", recordingUri);
                    }
                    else if (id.equals("6")) {
                        intent.putExtra("MP_6", recordingUri);
                    }
                    else if (id.equals("7")) {
                        intent.putExtra("MP_7", recordingUri);
                    }
                    else if (id.equals("8")) {
                        intent.putExtra("MP_8", recordingUri);
                    }
                    else if (id.equals("9")) {
                        intent.putExtra("MP_9", recordingUri);
                    }
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                    if (callback != null) {
                        callback.onItemClicked(position);
                    }
                }

            });
        }

    }
}