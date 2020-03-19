package com.hp.groceriapp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.Customer.CustomerModels.Cust_LoginModel;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerLogin extends AppCompatActivity {


    private EditText customerPhn;
    private EditText customerPass;
    private MaterialButton proceed;
    private MaterialButton customerSignupBTN;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_login);
        initView();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

        //...........dummy cred
      //  customerPhn.setText("7012069385");
        //customerPass.setText("qwerty");

        //............dummy cred ends


        customerSignupBTN.setOnClickListener(view -> {
            startActivity(new Intent(CustomerLogin.this, CustomerSignup.class));
        });

        proceed.setOnClickListener(view -> {
            if (customerPhn.getText().toString().isEmpty() ||
                    customerPass.getText().toString().isEmpty()) {
                Snackbar.make(proceed, "Fill all Fields!", BaseTransientBottomBar.LENGTH_LONG).show();
            } else {
                new Retro().getApi().CUST_LOGIN_MODEL_CALL(customerPhn.getText().toString(),
                        customerPass.getText().toString()).enqueue(new Callback<Cust_LoginModel>() {
                    @Override
                    public void onResponse(Call<Cust_LoginModel> call, Response<Cust_LoginModel> response) {
                        Cust_LoginModel cust_loginModel = response.body();
                        if (cust_loginModel.getStatus().equalsIgnoreCase("success")) {

                            appPreferences.saveData("customer_id",cust_loginModel.getUser_data().getCustomer_id());
                            startActivity(new Intent(CustomerLogin.this,CustomerHome.class));

                        } else {
                            Snackbar.make(proceed, "Wrong Creentials", BaseTransientBottomBar.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Cust_LoginModel> call, Throwable t) {
                        Snackbar.make(proceed, "Cust_LoginModel API FAILURE :" + t, BaseTransientBottomBar.LENGTH_LONG).show();


                    }
                });

            }
        });
    }

    private void initView() {

        customerPhn = findViewById(R.id.customer_phn);
        customerPass = findViewById(R.id.customer_pass);
        proceed = findViewById(R.id.proceed);
        customerSignupBTN = findViewById(R.id.customer_signupBTN);
    }
}
