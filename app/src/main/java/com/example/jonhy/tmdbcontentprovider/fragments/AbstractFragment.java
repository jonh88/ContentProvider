package com.example.jonhy.tmdbcontentprovider.fragments;



import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;

import com.example.jonhy.tmdbcontentprovider.asyncTasks.AbstractApiCall;

/**
 * Created by jonhy on 28/10/2017.
 */

public abstract class AbstractFragment extends Fragment {



    protected Button btnSearch;

    protected EditText editTextQuery;

    protected AbstractApiCall apiCall;

    protected void executeQuery(){
        this.apiCall.execute(editTextQuery.getText().toString());
    }

    protected void setApiCall(AbstractApiCall abstractApiCall){
        this.apiCall = abstractApiCall;
    }

}
