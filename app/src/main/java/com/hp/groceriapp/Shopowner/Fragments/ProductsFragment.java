package com.hp.groceriapp.Shopowner.Fragments;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.Shopowner.Adapters.Admin_Productlist_Adapter;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Shopowner.Model.Delete_Pdt_Model;
import com.hp.groceriapp.Shopowner.Model.ProductlistModel;
import com.hp.groceriapp.Utils.FragmentSwitcher;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductsFragment extends Fragment {

    RecyclerView productsRV;
    ImageView noitemImg;
    private AppPreferences appPreferences;
    ProductlistModel productlistModel;
    ExtendedFloatingActionButton addProductFab;
    private AlertDialog pd;


    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //context menu item click  listener for recycler view
    //context menu created inside adapter
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Log.e("MENU ", item.getTitle().toString());
        if (item.getTitle() == "Edit") {
            String context_menu_pid = Admin_Productlist_Adapter.get_pid_forContextMenuClickListener();
            Toast.makeText(getContext(), "Edit", Toast.LENGTH_SHORT).show();
        }
        if (item.getTitle() == "Delete") {



            String context_menu_pid = Admin_Productlist_Adapter.get_pid_forContextMenuClickListener();

            Toast.makeText(getContext(), "delete", Toast.LENGTH_SHORT).show();

            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity())
                    .setTitle("Delete Product")
                    .setCancelable(false)
                    .setMessage("Are you sure to delete this product ? ")
                    .setIcon(R.drawable.ic_delete_forever)
                    .setPositiveButton("DELETE", (dialog, which) -> {

                        //showing dots progress dialog
                        pd.setTitle("Deleting");
                       // pd.setMessage("Please wait");
                        pd.show();
                        //api call to delete

                        Log.e("pdt id for delete",context_menu_pid);
                        new Retro().getApi().deleteProduct(context_menu_pid).enqueue(new Callback<Delete_Pdt_Model>() {
                            @Override
                            public void onResponse(Call<Delete_Pdt_Model> call, Response<Delete_Pdt_Model> response) {
                                      Delete_Pdt_Model delete_pdt_model =response.body();
                                      if(delete_pdt_model.getStatus().equalsIgnoreCase("Deleted Successfully")){
                                          Toast.makeText(getContext(), "The product has been deleted successfully.", Toast.LENGTH_SHORT).show();
                                            pd.dismiss();
                                             dialog.dismiss();
                                      }else
                                      {
                                          pd.dismiss();
                                          dialog.dismiss();
                                          Toast.makeText(getContext(), "Oops! Something went wrong. Try again later", Toast.LENGTH_SHORT).show();
                                      }



                            }

                            @Override
                            public void onFailure(Call<Delete_Pdt_Model> call, Throwable t) {
                                pd.dismiss();
                                Toast.makeText(getContext(), "DELETE API FAILURE  : "+t, Toast.LENGTH_SHORT).show();

                            }
                        });

                    })
                    .setNegativeButton("CANCEL", (dialog, which) -> {
                        dialog.dismiss();
                    });


            builder.show();


        }
        return super.onContextItemSelected(item);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_products, container, false);
        initView(root);

        appPreferences = AppPreferences.getInstance(getActivity(), getResources().getString(R.string.app_name));
        pd = new SpotsDialog(getActivity(), R.style.CustomAlert);

        noitemImg.setVisibility(View.INVISIBLE);
        String adminid = appPreferences.getData("adminid");
        new Retro().getApi().PRODUCTLIST_MODEL_CALL(adminid).enqueue(new Callback<ProductlistModel>() {
            @Override
            public void onResponse(Call<ProductlistModel> call, Response<ProductlistModel> response) {
                productlistModel = response.body();
                if (productlistModel.getStatus().equalsIgnoreCase("success")) {


                    productsRV.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
                    productsRV.setAdapter(new Admin_Productlist_Adapter(productlistModel, getActivity().getApplicationContext()));
                } else {
                    noitemImg.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "No products found", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ProductlistModel> call, Throwable t) {

                Toast.makeText(getContext(), "Product api failure" + t, Toast.LENGTH_SHORT).show();

            }
        });

        addProductFab.setOnClickListener(view -> {
            new FragmentSwitcher().replaceFragment(new AddProductFragment(), getActivity());


        });


        return root;
    }

    private void initView(View root) {

        productsRV = root.findViewById(R.id.productsRV);
        noitemImg = root.findViewById(R.id.noitemsImg);
        addProductFab = root.findViewById(R.id.addstaffFAB);

    }


}
