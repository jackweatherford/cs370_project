package com.example.student.soundboard;

import android.view.View;
import android.widget.TextView;


public class ListViewHolder {

    private TextView fileName;

    public ListViewHolder(View itemView) {
        super(itemView);
        fileName = itemView.findViewById(R.id.recycler_file_name);
    }

}