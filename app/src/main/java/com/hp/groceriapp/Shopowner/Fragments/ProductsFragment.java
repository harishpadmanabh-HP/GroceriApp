package com.hp.groceriapp.Shopowner.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.Shopowner.Adapters.Admin_Productlist_Adapter;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Shopowner.Model.ProductlistModel;
import com.hp.groceriapp.Utils.FragmentSwitcher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductsFragment extends Fragment {

    RecyclerView productsRV;
    ImageView noitemImg;
    private AppPreferences appPreferences;
    ProductlistModel productlistModel;
    ExtendedFloatingActionButton addProductFab;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_products, container, false);
        initView(root);

        appPreferences = AppPreferences.getInstance(getActivity(), getResources().getString(R.string.app_name));

        noitemImg.setVisibility(View.INVISIBLE);
        String adminid=appPreferences.getData("adminid");
        new Retro().getApi().PRODUCTLIST_MODEL_CALL(adminid).enqueue(new Callback<ProductlistModel>() {
            @Override
            public void onResponse(Call<ProductlistModel> call, Response<ProductlistModel> response) {
                productlistModel=response.body();
                if(productlistModel.getStatus().equalsIgnoreCase("success")) {


                    productsRV.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
                    productsRV.setAdapter(new Admin_Productlist_Adapter(productlistModel, getActivity().getApplicationContext()));
                }else
                {
                    noitemImg.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "No products found", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ProductlistModel> call, Throwable t) {

                Toast.makeText(getContext(), "Product api failure"+t, Toast.LENGTH_SHORT).show();

            }
        });

        addProductFab.setOnClickListener(view -> {
            new FragmentSwitcher().replaceFragment(new AddProductFragment(),getActivity());


        } );




        return root;
    }

    private void initView(View root) {

        productsRV=root.findViewById(R.id.productsRV);
        noitemImg=root.findViewById(R.id.noitemsImg);
        addProductFab=root.findViewById(R.id.addstaffFAB);

    }


}
