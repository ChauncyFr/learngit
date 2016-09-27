package com.example.fxr.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fxr on 16-9-23.
 */

public class LoginFragment extends Fragment {

    private int layoutID;
    SparseArray<View> views;
    View rootView;


    public LoginFragment(int layoutID, Context context) {
        this.layoutID = layoutID;
        views = new SparseArray<>();
        rootView = LayoutInflater.from(context).inflate(layoutID,null);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return rootView;

    }

    public View getView(int id){
        View view = views.get(id);
        if(view == null){
            view = rootView.findViewById(id);
            views.put(id,view);
        }
        return view;
    }
}


