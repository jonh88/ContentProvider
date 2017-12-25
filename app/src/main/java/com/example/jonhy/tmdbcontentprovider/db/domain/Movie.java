package com.example.jonhy.tmdbcontentprovider.db.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jonhy on 29/10/2017.
 */

public class Movie implements Parcelable {

    private int id;
    private int id_tmdb;
    private String title;
    private String photo;

    public Movie(int id, int id_tmdb, String title, String photo) {
        this.id = id;
        this.id_tmdb = id_tmdb;
        this.title = title;
        this.photo = photo;
    }

    protected Movie(Parcel in){
        this.id = in.readInt();
        this.id_tmdb = in.readInt();
        this.title = in.readString();
        this.photo = in.readString();
    }

    public int getId() {
        return id;
    }

    public int getId_tmdb() {
        return id_tmdb;
    }

    public void setId_tmdb(int id_tmdb) {
        this.id_tmdb = id_tmdb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.id_tmdb);
        dest.writeString(this.title);
        dest.writeString(this.photo);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>(){
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
