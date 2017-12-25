package com.example.jonhy.tmdbcontentprovider.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jonhy.tmdbcontentprovider.resources.search.AbstractSearchDTO;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jonhy on 28/10/2017.
 */

public abstract class AbstractApiCall extends AsyncTask<String, Void, AbstractSearchDTO> {

    protected static final String URL_TMDB ="https://api.themoviedb.org/3";
    protected static final String API_KEY="&api_key=45b18b8019fbead4b72d270c9bedff07";
    protected static final String LANGUAGE="&language=es";
    protected static final String NOT_ADULT_CONTENT = "&include_adult=false";

    protected String query;
    protected Context ctx;
    protected URL url;
    protected HttpURLConnection httpURLConnection;

    protected AbstractApiCall(Context ctx){
        this.ctx = ctx;
    }

}
