package com.example.jonhy.tmdbcontentprovider.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jonhy.tmdbcontentprovider.resources.actors.ActorDTO;
import com.example.jonhy.tmdbcontentprovider.resources.search.AbstractSearchDTO;
import com.example.jonhy.tmdbcontentprovider.resources.search.ActorSearchDTO;

import java.util.Iterator;

import static com.example.jonhy.tmdbcontentprovider.db.contracts.ActorContract.TablaActores;
/**
 * Created by jonhy on 28/10/2017.
 */

public class ActorRepository extends SQLiteOpenHelper {

    private static final String DB_NAME = TablaActores.DB_NAME;
    private static final int DB_VERSION = 1;

    public ActorRepository(Context context) {super(context, DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String querySQL = "CREATE TABLE IF NOT EXISTS "+ TablaActores.TABLE_NAME + " ("
                + TablaActores.COL_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TablaActores.COL_NAME_ID_TMDB + " INTEGER, "
                + TablaActores.COL_NAME_NAME + " TEXT, "
                + TablaActores.COL_NAME_PHOTO + " TEXT)";
        db.execSQL(querySQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String querySQL = "DROP TABLE IF EXISTS "+ TablaActores.TABLE_NAME;
        db.execSQL(querySQL);
        onCreate(db);
    }

    public long insert (int id_tmdb, String name, String photo){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TablaActores.COL_NAME_ID_TMDB,id_tmdb);
        contentValues.put(TablaActores.COL_NAME_NAME, name);
        contentValues.put(TablaActores.COL_NAME_PHOTO, photo);

        return sqLiteDatabase.insert(TablaActores.TABLE_NAME, null, contentValues);
    }

    public void insert(AbstractSearchDTO abstractSearchDTO){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        ActorSearchDTO actorSearchDTO = (ActorSearchDTO) abstractSearchDTO;

        int i = 0;
        Iterator<ActorDTO> iterator = actorSearchDTO.getResults().iterator();
        while (i<10 && iterator.hasNext()){
            ActorDTO actorDTO = iterator.next();
            i++;
            this.insert(actorDTO.getId(),actorDTO.getName(),actorDTO.getProfilePath());
        }

    }
}
