package com.example.student.soundboard.models;

public class AudioFile {

    String data, title, artist, album;
    long id;
    //Boolean isMusic, isNotification, isAlarm;

    public AudioFile(String data, String title, String artist, String album, long id) {
        this.data = data;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public long getid() {
        return id;
    }

    public void setData(String data){
        this.data= data;
    }

    public void setTitle(String title){
        this.title= title;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setAlbum(long id) {
        this.id = id;
    }
}