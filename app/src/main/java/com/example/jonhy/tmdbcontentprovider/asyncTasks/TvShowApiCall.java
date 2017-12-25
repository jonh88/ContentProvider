package com.example.jonhy.tmdbcontentprovider.asyncTasks;

import android.content.Context;
import android.util.Log;

import com.example.jonhy.tmdbcontentprovider.db.dao.TvShowRepository;
import com.example.jonhy.tmdbcontentprovider.resources.search.AbstractSearchDTO;
import com.example.jonhy.tmdbcontentprovider.resources.search.TvShowSearchDTO;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jonhy on 29/10/2017.
 */

public class TvShowApiCall extends AbstractApiCall {

    private static final String TVSHOW_URL ="/search/tv?query=";

   public TvShowApiCall(Context ctx){
        super(ctx);
    }

    @Override
    protected TvShowSearchDTO doInBackground(String... strings) {

        query = strings[0];

        StringBuilder stringBuilderUrl = new StringBuilder(AbstractApiCall.URL_TMDB)
                .append(TVSHOW_URL)
                .append(query)
                .append(AbstractApiCall.API_KEY)
                .append(LANGUAGE);

        try {
            Log.d("Server","Llamando al servicio: "+stringBuilderUrl.toString());
            this.url = new URL(stringBuilderUrl.toString());
            this.httpURLConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bReader = new BufferedReader(
                    new InputStreamReader(
                            httpURLConnection.getInputStream()
                    ));

            StringBuilder stringBuilderResponse = new StringBuilder();
            String line = bReader.readLine();
            while (line != null){
                stringBuilderResponse
                        .append(line)
                        .append("\n");
                line = bReader.readLine();
            }

            Log.d("Server","Tratando respuesta Gson");
            Gson gson = new Gson();
            TvShowSearchDTO tvShowSearchDTO = gson.fromJson(stringBuilderResponse.toString(),TvShowSearchDTO.class);

            return tvShowSearchDTO;

        }catch (Exception e){
            Log.e("Server","Error TvShowApiCall doInBackground");
            Log.e("Server",e.getMessage());
            return null;
        }

    }

    @Override
    protected void onPostExecute(AbstractSearchDTO tvShowSearchDtoResponse) {
        Log.d("Server","TvShowApiCall onPostExecute");
        TvShowRepository tvShowRepository = new TvShowRepository(this.ctx);

        tvShowRepository.insert(tvShowSearchDtoResponse);
    }
}
