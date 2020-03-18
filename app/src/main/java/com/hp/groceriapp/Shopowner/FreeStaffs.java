package com.hp.groceriapp.Shopowner;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Shopowner.Adapters.FreeStaffsAdapter;
import com.hp.groceriapp.Shopowner.Model.FreeStaffModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreeStaffs extends AppCompatActivity {

    private RecyclerView freeStaffsRV;
    String adminID;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_staffs);
        initView();
        appPreferences = AppPreferences.getInstance(getApplicationContext(), getResources().getString(R.string.app_name));
        adminID=appPreferences.getData("adminid");
        new Retro().getApi().freestaffs(adminID).enqueue(new Callback<FreeStaffModel>() {
            @Override
            public void onResponse(Call<FreeStaffModel> call, Response<FreeStaffModel> response) {
                FreeStaffModel freeStaffModel=response.body();
                if(freeStaffModel.getStatus().equalsIgnoreCase("success"))
                {

                    freeStaffsRV.setLayoutManager(new GridLayoutManager(FreeStaffs.this,2));
                    freeStaffsRV.setAdapter(new FreeStaffsAdapter(FreeStaffs.this,freeStaffModel,adminID));
                }else
                {
                    Toast.makeText(FreeStaffs.this, " No staffs are free .", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FreeStaffModel> call, Throwable t) {
                Toast.makeText(FreeStaffs.this, "Free staff api fail "+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        freeStaffsRV = findViewById(R.id.freeStaffsRV);
    }
}
