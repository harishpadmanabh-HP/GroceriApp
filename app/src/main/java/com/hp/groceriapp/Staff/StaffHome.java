package com.hp.groceriapp.Staff;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Staff.Models.StaffDetailsModel;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffHome extends AppCompatActivity {

    private CircleImageView staffimage;
    private TextView alertname;
    private TextView alertphone;
    private TextView alertpin;
    private TextView alertempid;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);
        initView();
        appPreferences = AppPreferences.getInstance(getApplicationContext(), getResources().getString(R.string.app_name));

        new Retro().getApi().staffDetailsCall(appPreferences.getData("staff_admin"),
                appPreferences.getData("staff_id")).enqueue(new Callback<StaffDetailsModel>() {
            @Override
            public void onResponse(Call<StaffDetailsModel> call, Response<StaffDetailsModel> response) {
                StaffDetailsModel staffDetailsModel=response.body();
                if(staffDetailsModel.getStatus().equalsIgnoreCase("success")){

                    Glide.with(StaffHome.this).load(staffDetailsModel.getStaff_Details().get(0).getPhoto()).into(staffimage);
                    alertname.setText(staffDetailsModel.getStaff_Details().get(0).getName());
                    alertphone.setText(staffDetailsModel.getStaff_Details().get(0).getPhone());
                    alertpin.setText("PIN : "+staffDetailsModel.getStaff_Details().get(0).getPin());
                    alertempid.setText("EMP ID : "+staffDetailsModel.getStaff_Details().get(0).getEmp_id());

                }else
                {
                    Toast.makeText(StaffHome.this, "Details cant be found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StaffDetailsModel> call, Throwable t) {
                Toast.makeText(StaffHome.this, "StaffDetailsModel api fail"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        staffimage = findViewById(R.id.staffimage);
        alertname = findViewById(R.id.alertname);
        alertphone = findViewById(R.id.alertphone);
        alertpin = findViewById(R.id.alertpin);
        alertempid = findViewById(R.id.alertempid);
    }
}
