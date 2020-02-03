package com.hp.groceriapp.PreSignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.Customer.CustomerSignup;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Shopowner.ShopOwnerSignUp;

public class SelectUser extends AppCompatActivity {


    private ImageView shopOwnerImg;
    private ImageView customerImg;
    private ImageView staffImg;

    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        initView();

        shopOwnerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                appPreferences.saveData("usercategory","shopowner");
                startActivity(new Intent(SelectUser.this, ShopOwnerSignUp.class));

            }
        });


        customerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appPreferences.saveData("usercategory","customer");
                startActivity(new Intent(SelectUser.this, CustomerSignup.class));

            }
        });


        staffImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appPreferences.saveData("usercategory","staff");
                // TODO intent staff signup


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initView() {
        shopOwnerImg = (ImageView) findViewById(R.id.shopOwnerImg);
        customerImg = (ImageView) findViewById(R.id.customerImg);
        staffImg = (ImageView) findViewById(R.id.staffImg);


    }
}
