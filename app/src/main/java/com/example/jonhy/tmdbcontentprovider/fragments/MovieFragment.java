package com.example.jonhy.tmdbcontentprovider.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jonhy.tmdbcontentprovider.R;
import com.example.jonhy.tmdbcontentprovider.asyncTasks.MovieApiCall;

/**
 * Created by jonhy on 28/10/2017.
 */

public class MovieFragment extends AbstractFragment {

    EditText editTextyearQuery;

    public MovieFragment() {
    }

    public static MovieFragment newInstance(Context ctx) {
        MovieFragment fragment = new MovieFragment();

        fragment.setApiCall(new MovieApiCall(ctx));

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        btnSearch = (Button)rootView.findViewById(R.id.btnSearchMovie);

        editTextQuery = (EditText) rootView.findViewById(R.id.input_queryMovie);

        editTextyearQuery = (EditText) rootView.findViewById(R.id.input_yearMovie);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] data = {editTextQuery.getText().toString(),
                        editTextyearQuery.getText().toString()};
                executeQuery(data);
            }
        });

        return rootView;
    }

    private void executeQuery(String [] data){
        this.apiCall.execute(data);
    }
}
