package com.hp.groceriapp.Shopowner.Fragments;


import android.app.AlertDialog;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Shopowner.Model.Edit_product_Model;
import com.hp.groceriapp.Shopowner.Model.Single_Product_model;
import com.hp.groceriapp.Utils.FragmentSwitcher;
import com.hp.groceriapp.Utils.IOnBackPressed;

import dmax.dialog.SpotsDialog;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProductFragment extends Fragment implements IOnBackPressed {


    private ImageView pdtImage;
    private TextInputEditText pdtNameEdt;
    private TextInputEditText pdtBrandEdt;
    private TextInputEditText pdtQuantityEdt;
    private TextInputEditText pdtPriceEdt;
    private TextInputEditText pdtRackNoEdt;
    private AppCompatAutoCompleteTextView categories;
    private MaterialButton addphotoBtn;
    private MaterialButton addProductbtn;
    private AppPreferences appPreferences;
    private AlertDialog pd;
    private String admin_id, product_id;
    private boolean isphotoChanged;

    public EditProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_edit_product, container, false);
        initView(root);
        appPreferences = AppPreferences.getInstance(getActivity(), getResources().getString(R.string.app_name));
        pd = new SpotsDialog(getActivity(), R.style.CustomAlert);
        admin_id = appPreferences.getData("adminid");
        product_id = appPreferences.getData("edit_pdt_id");
        isphotoChanged = false;

        showDetails(admin_id, product_id);


        addProductbtn.setOnClickListener(v -> {
            saveDetails(isphotoChanged);

        });

        return root;
    }

    private void saveDetails(boolean isphotoChanged) {
        pd.show();

        if (isphotoChanged) {

        } else {

            String newName = pdtNameEdt.getText().toString();
            String newBrand = pdtBrandEdt.getText().toString();
            String newQuantity = pdtQuantityEdt.getText().toString();
            String newprice = pdtPriceEdt.getText().toString();
            String newRack = pdtRackNoEdt.getText().toString();
            String newCategory = categories.getText().toString();

            RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), newName);
            RequestBody brandBody = RequestBody.create(MediaType.parse("text/plain"), newBrand);
            RequestBody qtyBody = RequestBody.create(MediaType.parse("text/plain"), newQuantity);
            RequestBody priceBody = RequestBody.create(MediaType.parse("text/plain"), newprice);
            RequestBody rackBody = RequestBody.create(MediaType.parse("text/plain"), newRack);
            RequestBody categoryBody = RequestBody.create(MediaType.parse("text/plain"), newCategory);
            RequestBody pdt_id_Body = RequestBody.create(MediaType.parse("text/plain"), product_id);

            new Retro().getApi().editProductsCall(nameBody, pdt_id_Body, qtyBody, priceBody, rackBody,brandBody).enqueue(new Callback<Edit_product_Model>() {
                @Override
                public void onResponse(Call<Edit_product_Model> call, Response<Edit_product_Model> response) {
                    Edit_product_Model edit_product_model = response.body();
                    if (edit_product_model.getStatus().equalsIgnoreCase("success")) {

                        pd.dismiss();
                        Toast.makeText(getContext(), "Product changes saved .", Toast.LENGTH_SHORT).show();
                        new FragmentSwitcher().replaceFragment(new ProductsFragment(), getActivity());

                    } else {
                        pd.dismiss();
                        new FragmentSwitcher().replaceFragment(new ProductsFragment(), getActivity());

                        Toast.makeText(getContext(), "Something went wrong .", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Edit_product_Model> call, Throwable t) {
                    pd.dismiss();
                    Toast.makeText(getContext(), "edit api failure : " + t, Toast.LENGTH_SHORT).show();

                }
            });


        }
    }

    private void showDetails(String admin_id, String product_id) {
        pd.show();
        new Retro().getApi().singleProdcutCall(admin_id, product_id).enqueue(new Callback<Single_Product_model>() {
            @Override
            public void onResponse(Call<Single_Product_model> call, Response<Single_Product_model> response) {
                Single_Product_model single_product_model = response.body();


                if (single_product_model.getStatus().equalsIgnoreCase("Success")) {
                    //null check for image
                    if (!single_product_model.getProduct_Details().getPhoto().equals("")) {


                        Glide.with(getActivity()).load(single_product_model.getProduct_Details().getPhoto()).into(pdtImage);
                    } else {
                        pdtImage.setImageResource(R.drawable.noitem);
                    }
                    pdtNameEdt.setText(single_product_model.getProduct_Details().getProduct_name());
                    pdtBrandEdt.setText(single_product_model.getProduct_Details().getBrand());
                    pdtQuantityEdt.setText(single_product_model.getProduct_Details().getQuantity());
                    pdtPriceEdt.setText(single_product_model.getProduct_Details().getPrice());
                    pdtRackNoEdt.setText(single_product_model.getProduct_Details().getRack_no());
                    categories.setText(single_product_model.getProduct_Details().getCategory_name());

                    pd.dismiss();

                } else {
                    pd.dismiss();
                    Toast.makeText(getActivity(), "Product not found !", Toast.LENGTH_SHORT).show();
                    //go back to product page
                    new FragmentSwitcher().replaceFragment(new ProductsFragment(), getActivity());
                }


            }

            @Override
            public void onFailure(Call<Single_Product_model> call, Throwable t) {
                Toast.makeText(getActivity(), "Product api failure ! " + t, Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }

    @Override
    public boolean onBackPressed() {
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.frameLayout, new ProductsFragment());
        t.commit();
        return true;
    }

    private void initView(View root) {
        pdtImage = root.findViewById(R.id.pdtImage);
        pdtNameEdt = root.findViewById(R.id.pdtNameEdt);
        pdtBrandEdt = root.findViewById(R.id.pdtBrandEdt);
        pdtQuantityEdt = root.findViewById(R.id.pdtQuantityEdt);
        pdtPriceEdt = root.findViewById(R.id.pdtPriceEdt);
        pdtRackNoEdt = root.findViewById(R.id.pdtRackNoEdt);
        categories = root.findViewById(R.id.categories);
        addphotoBtn = root.findViewById(R.id.addphotoBtn);
        addProductbtn = root.findViewById(R.id.addProductbtn);
    }
}
