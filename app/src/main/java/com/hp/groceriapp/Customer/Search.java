package com.hp.groceriapp.Customer;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.Customer.Adapters.BuyProduct_Adapter;
import com.hp.groceriapp.Customer.Adapters.SearchAdapter;
import com.hp.groceriapp.Customer.CustomerModels.Cust_SearchModel;
import com.hp.groceriapp.Customer.CustomerModels.OrderReq_Model;
import com.hp.groceriapp.Customer.CustomerModels.OrderResponse_Model;
import com.hp.groceriapp.Customer.CustomerModels.Push_To_Admin_Model;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends AppCompatActivity {

    private SearchView searchview;
    private RecyclerView searchRV;
    private AppPreferences appPreferences;
    private String shopId;
    ArrayList<String> pdtid;
    ArrayList<String> pdtQunatity;
    Cust_SearchModel cust_searchModel;
    private ExtendedFloatingActionButton proceedFAB;
    String  json,jsonview;
    RequestBody AccessTokenValue = null,AccessTokenValueView=null;
    private SearchAdapter searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));
        shopId = appPreferences.getData("shopid");
        pdtid = new ArrayList<>();
        pdtQunatity = new ArrayList<>();


        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new Retro().getApi().searchProductsCall(query, shopId).enqueue(new Callback<Cust_SearchModel>() {
                    @Override
                    public void onResponse(Call<Cust_SearchModel> call, Response<Cust_SearchModel> response) {
                        cust_searchModel = response.body();
                        if (cust_searchModel.getStatus().equalsIgnoreCase("success")) {

                            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

                            searchRV.setLayoutManager(staggeredGridLayoutManager);
                             searchAdapter = new SearchAdapter(getApplicationContext(), cust_searchModel, pdtid, pdtQunatity);
                            searchRV.setAdapter(searchAdapter);


                        } else {
                            Toast.makeText(Search.this, "No items found.", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<Cust_SearchModel> call, Throwable t) {
                        Toast.makeText(Search.this, "API FAILURE " + t, Toast.LENGTH_SHORT).show();
                    }
                });


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        proceedFAB.setOnClickListener(v -> {

            pdtid=searchAdapter.getPdtid();
            pdtQunatity=searchAdapter.getPdtQunatity();

            Log.e("PDT SIZE", String.valueOf(pdtid.size()));
            Log.e("PDT Qun Size", String.valueOf(pdtQunatity.size()));
            Log.e("CUSTOMER ID",appPreferences.getData("customer_id"));
            Log.e("SHOP ID",appPreferences.getData("shopid"));
            for(String pdt : pdtid)
            {
                Log.e("PDT ID",pdt);
            }
            for(String pdtq : pdtQunatity)
            {
                Log.e("PDT quantity",pdtq);
            }
            OrderReq_Model orderReq_model=new OrderReq_Model();
            OrderReq_Model.DataBean dataBean = new OrderReq_Model.DataBean();

            ArrayList<OrderReq_Model.DataBean> beans =new ArrayList<>();
            for(int i=0;i<pdtQunatity.size();i++){

                dataBean.setProduct_id(pdtid.get(i));
                dataBean.setCustomer_id(appPreferences.getData("customer_id"));
                dataBean.setQuantity(pdtQunatity.get(i));
                dataBean.setId(appPreferences.getData("shopid"));
                beans.add(dataBean);
            }
            orderReq_model.setData(beans);
            try {
                Gson gson = new Gson();
                json = gson.toJson(orderReq_model).trim();
                System.out.println("FinalData................\n" + json.toString());

                Log.e("REQUEST BODY",json.toString());


            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                AccessTokenValue = RequestBody.create(MediaType.parse("application/json"), json.getBytes("UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Retro().getApi().orderProducts(AccessTokenValue).enqueue(new Callback<OrderResponse_Model>() {
                @Override
                public void onResponse(Call<OrderResponse_Model> call, Response<OrderResponse_Model> response) {
                    OrderResponse_Model orderResponse_model=response.body();
                    Toast.makeText(Search.this, "Status : "+orderResponse_model.getStatus()+"price "+orderResponse_model.getPrice(), Toast.LENGTH_SHORT).show();

                    pdtid.clear();
                    pdtQunatity.clear();
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

                    searchRV.setLayoutManager(staggeredGridLayoutManager);
                    searchAdapter=new SearchAdapter(getApplicationContext(),cust_searchModel,pdtid,pdtQunatity);
                    searchRV.setAdapter(searchAdapter);


                    new Retro().getApi().pushtoAdmin(appPreferences.getData("shopid"),
                            appPreferences.getData("customer_id") ).enqueue(new Callback<Push_To_Admin_Model>() {
                        @Override
                        public void onResponse(Call<Push_To_Admin_Model> call, Response<Push_To_Admin_Model> response) {
                            Push_To_Admin_Model push_to_admin_model=response.body();
                            if(push_to_admin_model.getSuccess()== 1)
                            {
                                Toast.makeText(Search.this, "Notified Successfully", Toast.LENGTH_SHORT).show();
                            }else
                            {
                                Toast.makeText(Search.this, ""+push_to_admin_model.getResults().get(0).getMessage_id(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Push_To_Admin_Model> call, Throwable t) {
                            Toast.makeText(Search.this, "Push fail"+t, Toast.LENGTH_SHORT).show();
                        }
                    });


                }

                @Override
                public void onFailure(Call<OrderResponse_Model> call, Throwable t) {
                    Toast.makeText(Search.this, "View products api Fail "+t, Toast.LENGTH_SHORT).show();
                }
            });






        });

    }

    private void initView() {
        searchview = findViewById(R.id.searchview);
        searchRV = findViewById(R.id.searchRV);
        proceedFAB = findViewById(R.id.proceedFAB);
    }
}
