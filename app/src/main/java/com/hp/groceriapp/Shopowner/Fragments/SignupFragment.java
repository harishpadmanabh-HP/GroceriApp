package com.hp.groceriapp.Shopowner.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.hp.groceriapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {


    private EditText nameEdt;
    private EditText emailEdt;
    private EditText phoneEdt;
    private EditText shopnameEdt;
    private EditText addEdt;
    private EditText passEdt;
    private Button signupBtn;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_signup, container, false);

        return root;
    }

    private void initView(View root) {
        nameEdt = root. findViewById(R.id.nameEdt);
        emailEdt = root.  findViewById(R.id.emailEdt);
        phoneEdt = root. findViewById(R.id.phoneEdt);
        shopnameEdt = root.  findViewById(R.id.shopnameEdt);
        addEdt = root. findViewById(R.id.addEdt);
        passEdt = root.  findViewById(R.id.passEdt);
        signupBtn = root.  findViewById(R.id.signupBtn);

    }
}
