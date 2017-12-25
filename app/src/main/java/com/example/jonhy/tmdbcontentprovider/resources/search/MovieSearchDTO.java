package com.example.jonhy.tmdbcontentprovider.resources.search;

import java.util.List;

import com.example.jonhy.tmdbcontentprovider.resources.movies.MovieDTO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by jonhy on 28/10/2017.
 */

public class MovieSearchDTO  extends AbstractSearchDTO{

    @SerializedName("results")
    @Expose
    private List<MovieDTO> results = null;

    public List<MovieDTO> getResults() {
        return results;
    }

    public void setResults(List<MovieDTO> results) {
        this.results = results;
    }
}
