package com.example.jonhy.tmdbcontentprovider.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jonhy.tmdbcontentprovider.R;
import com.example.jonhy.tmdbcontentprovider.asyncTasks.TvShowApiCall;

/**
 * Created by jonhy on 28/10/2017.
 */

public class TvShowFragment extends AbstractFragment {
    public TvShowFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TvShowFragment newInstance(Context ctx) {
        TvShowFragment fragment = new TvShowFragment();

        fragment.setApiCall(new TvShowApiCall(ctx));

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shows, container, false);

        editTextQuery = (EditText) rootView.findViewById(R.id.input_queryTvShow);

        btnSearch = (Button)rootView.findViewById(R.id.btnSearchTvShow);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeQuery();
            }
        });

        return rootView;
    }
}
