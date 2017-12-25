package com.example.jonhy.tmdbcontentprovider.resources.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.jonhy.tmdbcontentprovider.resources.actors.ActorDTO;
import java.util.List;

/**
 * Created by jonhy on 28/10/2017.
 */

public class ActorSearchDTO extends AbstractSearchDTO {
    @SerializedName("results")
    @Expose
    private List<ActorDTO> results = null;

    public List<ActorDTO> getResults() {
        return results;
    }

    public void setResults(List<ActorDTO> results) {
        this.results = results;
    }
}

