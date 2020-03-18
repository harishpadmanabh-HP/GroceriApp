package com.hp.groceriapp.Staff;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Staff.Adapters.OrderAdpater;
import com.hp.groceriapp.Staff.Models.OrderList_To_Staff_Model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffOrderList extends AppCompatActivity {

    private TextView custName;
    private TextView custPhone;
    private TextView custEmail;
    private RecyclerView orderListRV;
    private ExtendedFloatingActionButton accpetFAB;
    private AppPreferences appPreferences;
    String adminid,customer_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_order_list);
        initView();
        appPreferences = AppPreferences.getInstance(getApplicationContext(), getResources().getString(R.string.app_name));
        customer_id=appPreferences.getData("notify_Customer_id");
        adminid=appPreferences.getData("adminid");
        Log.e("ADMIN ID",adminid);
        Log.e("CUSTOMER ID",customer_id);

        new Retro().getApi().orderListStaff("1","2").enqueue(new Callback<OrderList_To_Staff_Model>() {
            @Override
            public void onResponse(Call<OrderList_To_Staff_Model> call, Response<OrderList_To_Staff_Model> response) {

                OrderList_To_Staff_Model orderList_to_staff_model=response.body();
                Log.e("RESPONSE", String.valueOf(response.body()));
                if(orderList_to_staff_model.getStatus().equalsIgnoreCase("success"))
                {
                    custName.setText(orderList_to_staff_model.getCustomer_details().getName());
                    custEmail.setText(orderList_to_staff_model.getCustomer_details().getEmail());
                    custPhone.setText(orderList_to_staff_model.getCustomer_details().getPhone());
                    orderListRV.setLayoutManager(new LinearLayoutManager(StaffOrderList.this,RecyclerView.VERTICAL,false));
                    orderListRV.setAdapter(new OrderAdpater(StaffOrderList.this,orderList_to_staff_model));

                }else
                {
                    Toast.makeText(StaffOrderList.this, "Order not found\n"+orderList_to_staff_model.getStatus(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<OrderList_To_Staff_Model> call, Throwable t) {

                Toast.makeText(StaffOrderList.this, "OrderList_To_Staff_Model api fail "+t, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initView() {
        custName = findViewById(R.id.cust_name);
        custPhone = findViewById(R.id.cust_phone);
        custEmail = findViewById(R.id.cust_email);
        orderListRV = findViewById(R.id.orderListRV);
        accpetFAB = findViewById(R.id.accpetFAB);
    }
}
