package com.hp.groceriapp.Shopowner.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hp.groceriapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Testfragment extends Fragment {


    public Testfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_testfragment, container, false);




        return root;
    }

}
