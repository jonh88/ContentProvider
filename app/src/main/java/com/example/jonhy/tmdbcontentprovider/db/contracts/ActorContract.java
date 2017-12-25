package com.example.jonhy.tmdbcontentprovider.db.contracts;

import android.provider.BaseColumns;

/**
 * Created by jonhy on 28/10/2017.
 */

public final class ActorContract {

    private ActorContract() {
    }

    public static abstract class TablaActores implements BaseColumns {

        public static final String TABLE_NAME = "actors";
        public static final String DB_NAME = "TheMovieDataBase";

        public static final String COL_NAME_ID = _ID;
        public static final String COL_NAME_ID_TMDB = "id_tmdb";
        public static final String COL_NAME_NAME = "name";
        public static final String COL_NAME_PHOTO = "photo";

    }
}
