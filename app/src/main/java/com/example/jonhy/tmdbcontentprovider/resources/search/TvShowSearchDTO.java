package com.example.jonhy.tmdbcontentprovider.resources.search;

import java.util.List;

import com.example.jonhy.tmdbcontentprovider.resources.series.TvShowDTO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by jonhy on 28/10/2017.
 */

public class TvShowSearchDTO extends AbstractSearchDTO{

    @SerializedName("results")
    @Expose
    private List<TvShowDTO> results = null;

    public List<TvShowDTO> getResults() {
        return results;
    }

    public void setResults(List<TvShowDTO> results) {
        this.results = results;
    }
}
