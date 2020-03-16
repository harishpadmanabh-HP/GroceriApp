package com.hp.groceriapp.Shopowner.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.iid.FirebaseInstanceId;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.Adapters.TabAdapter;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Shopowner.Model.Login_model;
import com.hp.groceriapp.Shopowner.Model.Reg_model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    Reg_model reg_model;
    private AppPreferences appPreferences;
    String device_token;





    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_signup, container, false);
        initView(root);
        appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));

        device_token= FirebaseInstanceId.getInstance().getToken();
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v=view;
                String inputname = nameEdt.getText().toString();
                String inputemail = emailEdt.getText().toString();
                String inputphone = phoneEdt.getText().toString();
                String inputshopname = shopnameEdt.getText().toString();
                String inputadd = addEdt.getText().toString();
                String inputpass = passEdt.getText().toString();
                if (inputname.equals("")|| inputemail.equals("") || inputphone.equals("") || inputshopname.equals("") || inputadd.equals("") || inputpass.equals("")) {
                    Toast.makeText(getContext(), "Fill necessary details", Toast.LENGTH_SHORT).show();

                } else {

                    Retro retro = new Retro();
                    retro.getApi().REG_MODEL_CALL(nameEdt.getText().toString(),
                            emailEdt.getText().toString(),
                            phoneEdt.getText().toString(),shopnameEdt.getText().toString(),addEdt.getText().toString(),passEdt.getText().toString(),device_token).enqueue(new Callback<Reg_model>() {
                        @Override
                        public void onResponse(Call<Reg_model> call, Response<Reg_model> response) {
                            reg_model=response.body();

                            if(reg_model.getStatus().equalsIgnoreCase("success"))
                            {
                                Snackbar.make(v,"Registered successfully.Sign in to continue", BaseTransientBottomBar.LENGTH_LONG).show();
                            }else
                            {
                                Snackbar.make(v,reg_model.getStatus(), BaseTransientBottomBar.LENGTH_LONG).show();

                            }


                        }


                        @Override
                        public void onFailure(Call<Reg_model> call, Throwable t) {


                        }
                    });






                }
            }
        });


        return root;
    }

    private void initView(View root) {
        nameEdt = root.findViewById(R.id.nameEdt);
        emailEdt = root.findViewById(R.id.emailEdt);
        phoneEdt = root.findViewById(R.id.phoneEdt);
        shopnameEdt = root.findViewById(R.id.shopnameEdt);
        addEdt = root.findViewById(R.id.addEdt);
        passEdt = root.findViewById(R.id.passEdt);
        signupBtn = root.findViewById(R.id.signupBtn);

    }
}
