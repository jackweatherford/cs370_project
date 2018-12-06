package com.example.student.soundboard;

import android.os.Environment;
import android.util.Log;

import java.io.File;


public class FilePath {
    private final String TAG = "Files";

    private File directory;
    private File[] files;
    private String path;

    public FilePath(File file){
        this.directory = file;
    }
    public FilePath(String path) {
        this.path = path;
        this.directory = new File(path);
        this.files = directory.listFiles();
    }

    public File get(int position) {
        return files[position];
    }

    public int size() {
        return files.length;
    }


 //  public void setDirectory(File directory) {
//        this.directory = directory;
//    }

//    public void setFiles(File[] files) {
//        this.files = files;
//    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public File getDirectory() {
        return directory;
    }
    public File[] getFiles() {
        return files;
    }
}