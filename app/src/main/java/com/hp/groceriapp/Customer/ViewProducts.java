package com.hp.groceriapp.Customer;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.Customer.Adapters.BuyProduct_Adapter;
import com.hp.groceriapp.Customer.CustomerModels.ProductList_Model;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProducts extends AppCompatActivity {

    private SearchView search;
    private RecyclerView pdtRv;
    private FloatingActionButton proceedfab;
    private AppPreferences appPreferences;
    String shop_id, customer_id;
    ArrayList<String> pdtid;
    ArrayList<String> pdtQunatity;
    BottomAppBar bottomAppBar;

    BuyProduct_Adapter buyProduct_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sparebuypdts);
        initView();

        pdtid = new ArrayList<>();
        pdtQunatity = new ArrayList<>();

        appPreferences = AppPreferences.getInstance(this, getResources().getString(R.string.app_name));

        shop_id = appPreferences.getData("shopid");
        customer_id = appPreferences.getData("cutomer_id");

        new Retro().getApi().customerViewProducts(shop_id).enqueue(new Callback<ProductList_Model>() {
            @Override
            public void onResponse(Call<ProductList_Model> call, Response<ProductList_Model> response) {
                ProductList_Model productList_model = response.body();

                if (productList_model.getStatus().equalsIgnoreCase("success")) {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

                    pdtRv.setLayoutManager(staggeredGridLayoutManager);
                    buyProduct_adapter=new BuyProduct_Adapter(productList_model,getApplicationContext(),pdtid,pdtQunatity);
                    pdtRv.setAdapter(buyProduct_adapter);

                } else {
                    Toast.makeText(getApplicationContext(), "No products Found", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ProductList_Model> call, Throwable t) {
                Toast.makeText(ViewProducts.this, "API FAIL"+t, Toast.LENGTH_SHORT).show();
            }
        });

        proceedfab.setOnClickListener(v -> {
            pdtid=buyProduct_adapter.getPdtid();
            pdtQunatity=buyProduct_adapter.getPdtQunatity();

            Log.e("PDT SIZE", String.valueOf(pdtid.size()));
            Log.e("PDT Qun Size", String.valueOf(pdtQunatity.size()));



            for(String pdt : pdtid)
            {
                Log.e("PDT ID",pdt);
            }
            for(String pdtq : pdtQunatity)
            {
                Log.e("PDT quantity",pdtq);
            }



        });


    }

    private void initView() {
      //  search = findViewById(R.id.search);
        pdtRv = findViewById(R.id.pdtRv);
        proceedfab = findViewById(R.id.proceedfab);
        bottomAppBar=findViewById(R.id.bottomAppBar);
    }
}
