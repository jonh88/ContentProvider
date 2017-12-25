package com.example.jonhy.tmdbcontentprovider.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.jonhy.tmdbcontentprovider.db.contracts.MovieContract.TablaMovies;
import com.example.jonhy.tmdbcontentprovider.resources.movies.MovieDTO;
import com.example.jonhy.tmdbcontentprovider.resources.search.AbstractSearchDTO;
import com.example.jonhy.tmdbcontentprovider.resources.search.MovieSearchDTO;

import java.util.Iterator;

/**
 * Created by jonhy on 29/10/2017.
 */

public class MovieRepository extends SQLiteOpenHelper {

    private static final String DB_NAME = TablaMovies.DB_NAME;
    private static final int DB_VERSION = 2;
    
    public MovieRepository(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String querySQL = "CREATE TABLE IF NOT EXISTS "+ TablaMovies.TABLE_NAME + " ("
                + TablaMovies.COL_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TablaMovies.COL_NAME_ID_TMDB + " INTEGER, "
                + TablaMovies.COL_NAME_TITLE + " TEXT, "
                + TablaMovies.COL_NAME_PHOTO + " TEXT)";
        db.execSQL(querySQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String querySQL = "DROP TABLE IF EXISTS "+ TablaMovies.TABLE_NAME;
        db.execSQL(querySQL);
        onCreate(db);
    }

    public long insert (int id_tmdb, String name, String photo){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TablaMovies.COL_NAME_ID_TMDB,id_tmdb);
        contentValues.put(TablaMovies.COL_NAME_TITLE, name);
        contentValues.put(TablaMovies.COL_NAME_PHOTO, photo);

        return sqLiteDatabase.insert(TablaMovies.TABLE_NAME, null, contentValues);
    }

    public void insert(AbstractSearchDTO abstractSearchDTO){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        MovieSearchDTO movieSearchDTO = (MovieSearchDTO) abstractSearchDTO;

        int i = 0;
        Iterator<MovieDTO> iterator = movieSearchDTO.getResults().iterator();
        while (i<10 && iterator.hasNext()){
            MovieDTO movieDTO = iterator.next();
            i++;
            this.insert(movieDTO.getId(),movieDTO.getTitle(),movieDTO.getPosterPath());
        }

    }
}
