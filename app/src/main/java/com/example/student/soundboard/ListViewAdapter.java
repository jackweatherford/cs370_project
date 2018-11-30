package com.example.student.soundboard;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewHolder> {

    File[] fileCollection;
    public ListViewAdapter(File[] collection) {
        fileCollection = collection;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list_item, parent, false);
        return new ListViewHolder(inflatedView);
    }


    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int position) {
        FilePath soundlist = null;
        soundlist.setDirectory(fileCollection[position]);
        listViewHolder.bindView(soundlist);
    }

    @Override
    public int getItemCount() {
        return fileCollection.length;
    }
}