package com.hp.groceriapp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.hp.groceriapp.Customer.CustomerModels.Cust_SignupModel;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerSignup extends AppCompatActivity {

    private EditText custName;
    private EditText custEmail;
    private EditText custPhone;
    private EditText custPass;
    private MaterialButton custSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_signup2);
        initView();
        custSignup.setOnClickListener(view -> {
            if (    custName.getText().toString().isEmpty() ||
                    custEmail.getText().toString().isEmpty() ||
                    custPhone.getText().toString().isEmpty() ||
                    custPass.getText().toString().isEmpty()) {
                Snackbar.make(custSignup, "Fill All Fields", BaseTransientBottomBar.LENGTH_LONG).show();
            } else {
                new Retro().getApi().CUST_SIGNUP_MODEL_CALL(custName.getText().toString(),
                        custEmail.getText().toString(),
                        custPhone.getText().toString(),
                        custPass.getText().toString()).enqueue(new Callback<Cust_SignupModel>() {
                    @Override
                    public void onResponse(Call<Cust_SignupModel> call, Response<Cust_SignupModel> response) {
                        Cust_SignupModel cust_signupModel=response.body();
                        if(cust_signupModel.getStatus().equalsIgnoreCase("success"))
                        {
                            Snackbar.make(custSignup, "Account Created !", BaseTransientBottomBar.LENGTH_LONG).show();
                            startActivity(new Intent(CustomerSignup.this,CustomerLogin.class));


                        }else
                        {
                            Snackbar.make(custSignup, cust_signupModel.getStatus(), BaseTransientBottomBar.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Cust_SignupModel> call, Throwable t) {
                        Snackbar.make(custSignup, "Cust_SignupModel API FAILURE :"+t, BaseTransientBottomBar.LENGTH_LONG).show();

                    }
                });

            }
        });
    }

    private void initView() {
        custName = findViewById(R.id.cust_name);
        custEmail = findViewById(R.id.cust_email);
        custPhone = findViewById(R.id.cust_phone);
        custPass = findViewById(R.id.cust_pass);
        custSignup = findViewById(R.id.cust_signup);
    }
}
