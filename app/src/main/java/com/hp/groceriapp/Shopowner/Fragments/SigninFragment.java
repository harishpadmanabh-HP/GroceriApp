package com.hp.groceriapp.Shopowner.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Shopowner.HomeDrawer;
import com.hp.groceriapp.Shopowner.Model.Login_model;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment {


    String dataphone;
    String datapass;

//    private TextInputLayout phone;
//    private TextInputLayout password;
    private TextInputEditText passwordEditText;
    private TextInputEditText emailEdt;

    private MaterialButton nextButton;
    Login_model login_model;
    private AppPreferences appPreferences;


    public SigninFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_signin, container, false);
        appPreferences = AppPreferences.getInstance(getActivity(), getResources().getString(R.string.app_name));

  initView(root);


  //......DUMMY CREDENTIALS
        emailEdt.setText("7012069385");
        passwordEditText.setText("qwerty");
  //..............................   DUMMY CREDENTIALS.......................

  nextButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

           dataphone=emailEdt.getText().toString();
           datapass=passwordEditText.getText().toString();


          if(dataphone.equals("")&&datapass.equals("")){
              Toast.makeText(getContext(), "Fill necessary details", Toast.LENGTH_SHORT).show();

          }
          else{
          Retro retro=new Retro();
          retro.getApi().LOGIN_MODEL_CALL(emailEdt.getText().toString(),passwordEditText.getText().toString()).enqueue(new Callback<Login_model>() {
              @Override
              public void onResponse(Call<Login_model> call, Response<Login_model> response) {
                  login_model=response.body();

                if( Objects.requireNonNull(login_model).getStatus().equals("Success")) {
                    Toast.makeText(getContext(), login_model.getStatus(), Toast.LENGTH_SHORT).show();

                  //save data in app preferences
                  appPreferences.saveData("adminid",login_model.getUser_data().getId());
                  appPreferences.saveData("adminphone",login_model.getUser_data().getPhone());
                  appPreferences.saveData("adminpass",login_model.getUser_data().getPassword());
                  appPreferences.saveData("adminemail",login_model.getUser_data().getEmail());
                  appPreferences.saveData("adminshopname",login_model.getUser_data().getShop_name());
                  appPreferences.saveData("adminadd",login_model.getUser_data().getBuilding_address());


//start hopme page
                  startActivity(new Intent(getContext(), HomeDrawer.class));

                }
              //}
                else {//login status false
                    Toast.makeText(getContext(), login_model.getStatus(), Toast.LENGTH_SHORT).show();
                }
              }

              @Override
              public void onFailure(Call<Login_model> call, Throwable t) {
                  Toast.makeText(getContext(),"Login api failure"+t, Toast.LENGTH_SHORT).show();
              }
          });



      }}
  });

        return root;

    }

    private void initView( View root) {
       // phone = root. findViewById(R.id.email);
        //assword = root. findViewById(R.id.password);
        emailEdt=root.findViewById(R.id.phoneEdt);
        passwordEditText = root. findViewById(R.id.password_edit_text);
        nextButton = root.findViewById(R.id.next_button);
    }
}
