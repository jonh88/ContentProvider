package com.example.jonhy.tmdbcontentprovider.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jonhy.tmdbcontentprovider.resources.search.AbstractSearchDTO;
import com.example.jonhy.tmdbcontentprovider.resources.search.TvShowSearchDTO;
import com.example.jonhy.tmdbcontentprovider.resources.series.TvShowDTO;

import java.util.Iterator;

import static com.example.jonhy.tmdbcontentprovider.db.contracts.TvShowContract.TablaTvShows;
/**
 * Created by jonhy on 29/10/2017.
 */

public class TvShowRepository extends SQLiteOpenHelper {

    private static final String DB_NAME = TablaTvShows.DB_NAME;
    private static final int DB_VERSION = 3;
    
    public TvShowRepository (Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String querySQL = "CREATE TABLE IF NOT EXISTS "+ TablaTvShows.TABLE_NAME + " ("
                + TablaTvShows.COL_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TablaTvShows.COL_NAME_ID_TMDB + " INTEGER, "
                + TablaTvShows.COL_NAME_NAME + " TEXT, "
                + TablaTvShows.COL_NAME_PHOTO + " TEXT)";
        db.execSQL(querySQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String querySQL = "DROP TABLE IF EXISTS "+ TablaTvShows.TABLE_NAME;
        db.execSQL(querySQL);
        onCreate(db);
    }

    public long insert (int id_tmdb, String name, String photo){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TablaTvShows.COL_NAME_ID_TMDB,id_tmdb);
        contentValues.put(TablaTvShows.COL_NAME_NAME, name);
        contentValues.put(TablaTvShows.COL_NAME_PHOTO, photo);

        return sqLiteDatabase.insert(TablaTvShows.TABLE_NAME, null, contentValues);
    }

    public void insert(AbstractSearchDTO abstractSearchDTO){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        TvShowSearchDTO movieSearchDTO = (TvShowSearchDTO) abstractSearchDTO;

        int i = 0;
        Iterator<TvShowDTO> iterator = movieSearchDTO.getResults().iterator();
        while (i<10 && iterator.hasNext()){
            TvShowDTO movieDTO = iterator.next();
            i++;
            this.insert(movieDTO.getId(),movieDTO.getName(),movieDTO.getPosterPath());
        }

    }
}
