package com.hp.groceriapp.Staff;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.FireBase.Firebaseidservice;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Staff.Models.Staff_Login_Model;
import com.hp.groceriapp.Utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnboardActivity extends AppCompatActivity {

    private EditText staffId;
    private EditText staffPass;
    private MaterialButton proceed;
    private AppPreferences appPreferences;

    String device_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);
        initView();
        View rootView = findViewById(android.R.id.content);
        appPreferences = AppPreferences.getInstance(getApplicationContext(), getResources().getString(R.string.app_name));

        device_token= FirebaseInstanceId.getInstance().getToken();

        //conecting all TextInputEditText as list
        final List<EditText> EditTexts = Utils.findViewsWithType(
                rootView, EditText.class);

        proceed.setOnClickListener(v -> {
            //checking null values for each edittesxt
            boolean noErrors = true;
            for (EditText editText : EditTexts) {
                //get strings from each edittext
                String editTextString = editText.getText().toString();
                if (editTextString.isEmpty()) {
                    editText.setError(("please fill this field"));
                    noErrors = false;
                } else {
                    editText.setError(null);
                }
            }
            if(noErrors){

                new Retro().getApi().staffLogin(staffId.getText().toString(),
                        staffPass.getText().toString(),device_token).enqueue(new Callback<Staff_Login_Model>() {
                    @Override
                    public void onResponse(Call<Staff_Login_Model> call, Response<Staff_Login_Model> response) {
                        Staff_Login_Model staff_login_model =response.body();
                        if(staff_login_model.getStatus().equalsIgnoreCase("success"))
                        {
                            Toast.makeText(OnboardActivity.this, "Logged in !", Toast.LENGTH_SHORT).show();

                            String staffid=staff_login_model.getUser_data().getStaff_id();
                            appPreferences.saveData("staff_id",staffid);
                        }else
                        {
                            Toast.makeText(OnboardActivity.this, ""+staff_login_model.getStatus(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Staff_Login_Model> call, Throwable t) {
                        Toast.makeText(OnboardActivity.this, "Staff login api failed "+t, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void initView() {
        staffId = findViewById(R.id.staff_id);
        staffPass = findViewById(R.id.staff_pass);
        proceed = findViewById(R.id.proceed);
    }
}
