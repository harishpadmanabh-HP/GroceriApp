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

  initView(root);

  nextButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

           dataphone=emailEdt.getText().toString();
           datapass=passwordEditText.getText().toString();

          appPreferences = AppPreferences.getInstance(getContext(), getResources().getString(R.string.app_name));

          if(dataphone.equals("")&&datapass.equals("")){
              Toast.makeText(getContext(), "Fill necessary details", Toast.LENGTH_SHORT).show();

          }
          else{
          Retro retro=new Retro();
          retro.getApi().LOGIN_MODEL_CALL(emailEdt.getText().toString(),passwordEditText.getText().toString()).enqueue(new Callback<Login_model>() {
              @Override
              public void onResponse(Call<Login_model> call, Response<Login_model> response) {
                  login_model=response.body();

                if( login_model.getStatus().equals("Success")) {
                    Toast.makeText(getContext(), login_model.getStatus(), Toast.LENGTH_SHORT).show();
                  dataphone= login_model.getUser_data().getPhone();
                  datapass=login_model.getUser_data().getPassword();
                  String dataid=login_model.getUser_data().getId();

                  appPreferences.saveData("id",dataid);
                  appPreferences.saveData("phone",dataphone);
                  appPreferences.saveData("pass",datapass);


//                    appPreferences.getData("id", null);
//                    appPreferences.getData("ph", null);
//                    appPreferences.getData("password", null);
//

                    startActivity(new Intent(getContext(), HomeDrawer.class));

                }
              //}
                else {
                    Toast.makeText(getContext(), login_model.getStatus(), Toast.LENGTH_SHORT).show();
                }
              }

              @Override
              public void onFailure(Call<Login_model> call, Throwable t) {
                  Toast.makeText(getContext(),""+t, Toast.LENGTH_SHORT).show();
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
