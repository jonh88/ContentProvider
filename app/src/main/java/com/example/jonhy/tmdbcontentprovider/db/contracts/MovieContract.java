package com.example.jonhy.tmdbcontentprovider.db.contracts;

import android.provider.BaseColumns;

/**
 * Created by jonhy on 29/10/2017.
 */

public class MovieContract {

    private MovieContract(){}

    public static abstract class TablaMovies implements BaseColumns {

        public static final String TABLE_NAME = "movie";
        public static final String DB_NAME = "TheMovieDataBase";

        public static final String COL_NAME_ID = _ID;
        public static final String COL_NAME_ID_TMDB = "id_tmdb";
        public static final String COL_NAME_TITLE = "title";
        public static final String COL_NAME_PHOTO = "photo";
    }
}
