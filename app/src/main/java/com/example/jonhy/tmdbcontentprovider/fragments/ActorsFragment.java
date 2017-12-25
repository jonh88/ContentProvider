package com.example.jonhy.tmdbcontentprovider.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jonhy.tmdbcontentprovider.R;
import com.example.jonhy.tmdbcontentprovider.asyncTasks.ActorApiCall;

/**
 * Created by jonhy on 28/10/2017.
 */

public class ActorsFragment extends AbstractFragment {

    public ActorsFragment() {

    }

    public static ActorsFragment newInstance(Context ctx) {

        ActorsFragment fragment = new ActorsFragment();

        fragment.setApiCall(new ActorApiCall(ctx));

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_actors, container, false);

        btnSearch = (Button)rootView.findViewById(R.id.btnSearchActor);

        editTextQuery = (EditText) rootView.findViewById(R.id.input_queryActor);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextQuery.getText())){
                    Snackbar.make(v,"Rellena el texto a buscar",Snackbar.LENGTH_SHORT).show();
                }else{
                    executeQuery();
                    Snackbar.make(v,"Actores guardados",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

}
