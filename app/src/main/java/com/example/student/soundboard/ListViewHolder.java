package com.example.student.soundboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ListViewHolder extends RecyclerView.ViewHolder {

    private TextView fileName;

    public ListViewHolder(View itemView) {
        //super(itemView);
        fileName = itemView.findViewById(R.id.recycler_file_name);
    }

    public void bindView(FilePath soundlist){
        fileName.setText(soundlist.getPath());
    }

}