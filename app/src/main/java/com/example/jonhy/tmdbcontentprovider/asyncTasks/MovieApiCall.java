package com.example.jonhy.tmdbcontentprovider.asyncTasks;

import android.content.Context;
import android.util.Log;

import com.example.jonhy.tmdbcontentprovider.db.dao.MovieRepository;
import com.example.jonhy.tmdbcontentprovider.resources.search.AbstractSearchDTO;
import com.example.jonhy.tmdbcontentprovider.resources.search.MovieSearchDTO;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jonhy on 29/10/2017.
 */

public class MovieApiCall extends AbstractApiCall {

    private static final String MOVIE_URL ="/search/movie?query=";
    private String year;

    public MovieApiCall(Context ctx){
        super(ctx);
    }

    @Override
    protected MovieSearchDTO doInBackground(String... strings){
        query = strings[0];
        year = strings[1];


        StringBuilder stringBuilderUrl = new StringBuilder(AbstractApiCall.URL_TMDB)
                .append(MOVIE_URL)
                .append(query)
                .append(AbstractApiCall.API_KEY)
                .append(NOT_ADULT_CONTENT)
                .append("&year="+year)
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
            MovieSearchDTO movieSearchDTO = gson.fromJson(stringBuilderResponse.toString(),MovieSearchDTO.class);

            return movieSearchDTO;

        }catch (Exception e){
            Log.e("Server","Error MovieApiCall doInBackground");
            Log.e("Server",e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(AbstractSearchDTO actorSearchDtoResponse) {
        Log.d("Server","ActorApiCall onPostExecute");
        MovieRepository movieRepository = new MovieRepository(this.ctx);

        movieRepository.insert(actorSearchDtoResponse);
    }
}
