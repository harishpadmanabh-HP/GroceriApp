package com.hp.groceriapp.Shopowner.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hp.groceriapp.R;
import com.hp.groceriapp.Utils.IOnBackPressed;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProductFragment extends Fragment implements IOnBackPressed {


    public EditProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_product, container, false);
    }

    @Override
    public boolean onBackPressed() {
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.frameLayout, new ProductsFragment());
        t.commit();
        return true;
    }
}
