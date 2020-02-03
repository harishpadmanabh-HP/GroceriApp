package com.hp.groceriapp.Shopowner.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Shopowner.HomeDrawer;

/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment {


    private TextInputLayout email;
    private TextInputLayout password;
    private TextInputEditText passwordEditText;
    private MaterialButton nextButton;

    public SigninFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_signin, container, false);
  initView(root);
  nextButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          startActivity(new Intent(getContext(), HomeDrawer.class));
      }
  });

        return root;

    }

    private void initView( View root) {
        email = root. findViewById(R.id.email);
        password = root. findViewById(R.id.password);
        passwordEditText = root. findViewById(R.id.password_edit_text);
        nextButton = root.findViewById(R.id.next_button);
    }
}
