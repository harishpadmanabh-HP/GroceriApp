package com.hp.groceriapp.Customer;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.Customer.Adapters.ShopList_Adapter;
import com.hp.groceriapp.Customer.CustomerModels.ShopListModel;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerHome extends AppCompatActivity {

    AppPreferences appPreferences;
    private RecyclerView shoplistRV;
    ShopListModel shopListModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

        initView();
        new Retro().getApi().SHOP_LIST_MODEL_CALL().enqueue(new Callback<ShopListModel>() {
            @Override
            public void onResponse(Call<ShopListModel> call, Response<ShopListModel> response) {

                shopListModel = response.body();
                if (shopListModel.getStatus().equalsIgnoreCase("success")) {

                    shoplistRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                    shoplistRV.setAdapter(new ShopList_Adapter(shopListModel, CustomerHome.this));

                } else {
                    Toast.makeText(CustomerHome.this, "No Shops found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ShopListModel> call, Throwable t) {
                Toast.makeText(CustomerHome.this, "API failure"+t, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initView() {
        shoplistRV = findViewById(R.id.shoplistRV);
    }
}
