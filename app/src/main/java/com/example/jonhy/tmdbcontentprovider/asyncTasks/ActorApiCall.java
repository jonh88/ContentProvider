package com.example.jonhy.tmdbcontentprovider.asyncTasks;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.example.jonhy.tmdbcontentprovider.db.dao.ActorRepository;
import com.example.jonhy.tmdbcontentprovider.resources.search.AbstractSearchDTO;
import com.example.jonhy.tmdbcontentprovider.resources.search.ActorSearchDTO;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jonhy on 28/10/2017.
 */

public class ActorApiCall extends AbstractApiCall {

    private static final String ACTOR_URL ="/search/person?query=";

    public ActorApiCall (Context ctx){
        super(ctx);
    }

    @Override
    protected ActorSearchDTO doInBackground(String... strings) {

        query = strings[0];

        StringBuilder stringBuilderUrl = new StringBuilder(AbstractApiCall.URL_TMDB)
                .append(ACTOR_URL)
                .append(query)
                .append(AbstractApiCall.API_KEY)
                .append(NOT_ADULT_CONTENT)
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
            ActorSearchDTO actorSearchDTO = gson.fromJson(stringBuilderResponse.toString(),ActorSearchDTO.class);

            return actorSearchDTO;

        }catch (Exception e){
            Log.e("Server","Error ActorApiCall doInBackground");
            Log.e("Server",e.getMessage());
            return null;
        }

    }

    @Override
    protected void onPostExecute(AbstractSearchDTO actorSearchDtoResponse) {
        Log.d("Server","ActorApiCall onPostExecute");
        ActorRepository actorRepository = new ActorRepository(this.ctx);

        actorRepository.insert(actorSearchDtoResponse);

    }
}
