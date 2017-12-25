package com.example.jonhy.tmdbcontentprovider.db.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jonhy on 29/10/2017.
 */

public class TvShow implements Parcelable {

    private int id;
    private int id_tmdb;
    private String name;
    private String photo;

    public TvShow(int id, int id_tmdb, String name, String photo) {
        this.id = id;
        this.id_tmdb = id_tmdb;
        this.name = name;
        this.photo = photo;
    }

    protected TvShow(Parcel in){
        this.id = in.readInt();
        this.id_tmdb = in.readInt();
        this.name = in.readString();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        dest.writeString(this.name);
        dest.writeString(this.photo);
    }

    public static final Parcelable.Creator<TvShow> CREATOR = new Parcelable.Creator<TvShow>(){
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
