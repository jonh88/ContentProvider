package com.example.jonhy.tmdbcontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import static com.example.jonhy.tmdbcontentprovider.db.contracts.ActorContract.TablaActores;

import com.example.jonhy.tmdbcontentprovider.db.dao.ActorRepository;

import java.util.HashMap;

/**
 * Created by jonhy on 01/11/2017.
 */

public class Provider extends ContentProvider {

    private static final UriMatcher URI_MATCHER;
    private static final String PROVIDER_NAME = "com.example.jonhy.tmdbcontentprovider.Provider";
    private static final String URL = "content://"+PROVIDER_NAME+ "/actors";
    private static final Uri CONTENT_URI = Uri.parse(URL);

    private static HashMap<String, String> ACTORS_PROJECTION_MAP;

    private static final int ACTORS = 1;
    private static final int ACTORS_ID = 2;

    private SQLiteDatabase db;

    static {

        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(PROVIDER_NAME, "actors",ACTORS);
        URI_MATCHER.addURI(PROVIDER_NAME, "actors/#",ACTORS_ID);

    }

    @Override
    public boolean onCreate() {
        Context  context = getContext();
        ActorRepository actorRepository = new ActorRepository(context);

        db = actorRepository.getWritableDatabase();

        return (db==null)?false:true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TablaActores.TABLE_NAME);

        switch (URI_MATCHER.match(uri)){
            case ACTORS:
                queryBuilder.setProjectionMap(ACTORS_PROJECTION_MAP);
                break;
            case ACTORS_ID:
                queryBuilder.appendWhere(TablaActores.COL_NAME_ID + "=" + uri.getLastPathSegment());
                break;
            default:
        }

        if (TextUtils.isEmpty(sortOrder)){
            sortOrder = TablaActores.COL_NAME_NAME;
        }

        Cursor cursor = queryBuilder.query(db,projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (URI_MATCHER.match(uri)){
            case ACTORS:
                return "vnd.android.cursor.dir/vnd.com.example.provider.actors";
            case ACTORS_ID:
                return "vnd.android.cursor.item/vnd.com.example.provider.actors";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}

