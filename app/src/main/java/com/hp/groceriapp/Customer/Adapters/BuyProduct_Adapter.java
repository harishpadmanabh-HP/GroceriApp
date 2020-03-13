package com.hp.groceriapp.Customer.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.Customer.CustomerModels.ProductList_Model;
import com.hp.groceriapp.R;

import java.util.ArrayList;

public class BuyProduct_Adapter extends RecyclerView.Adapter<BuyProduct_Adapter.PdtVh> {
    ProductList_Model productList_model;
    Context context;
    private AppPreferences appPreferences;
    ArrayList<String> pdtid;
    ArrayList<String> pdtQunatity;




    public BuyProduct_Adapter(ProductList_Model productList_model, Context context) {
        this.productList_model = productList_model;
        this.context = context;
        pdtid=new ArrayList<>();
        pdtQunatity=new ArrayList<>();
    }

    @NonNull
    @Override
    public PdtVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customersinglebuyproduct, parent, false);
        appPreferences = AppPreferences.getInstance(context, context.getResources().getString(R.string.app_name));

        return new PdtVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PdtVh holder, int position) {

        holder.btnLayout.setVisibility(View.GONE);
        holder.quantity.setText("0");


        holder. minus.setOnClickListener(v -> {
            if (Integer.parseInt(holder.quantity.getText().toString()) >= 1) {
                holder. quantity.setText(String.valueOf(Integer.parseInt(holder.quantity.getText().toString()) - 1));
            } else if (Integer.parseInt(holder.quantity.getText().toString()) == 0) {
                holder.btnLayout.setVisibility(View.GONE);


            }
        });
        holder. plus.setOnClickListener(v -> {
            holder. quantity.setText(String.valueOf(Integer.parseInt(holder.quantity.getText().toString()) + 1));

            holder. btnLayout.setVisibility(View.VISIBLE);


        });

        Glide.with(context).load(productList_model.getProduct_Details().get(position).getPhoto()).placeholder(R.drawable.noitem).into(holder.productimageview);
        holder. productName.setText(productList_model.getProduct_Details().get(position).getProduct_name());
        holder. productBrand.setText("Brand : " + productList_model.getProduct_Details().get(position).getBrand());
        holder. productPrice.setText(productList_model.getProduct_Details().get(position).getPrice() + " Rs");


    }

    @Override
    public int getItemCount() {
        return productList_model.getProduct_Details().size();
    }



    class PdtVh extends RecyclerView.ViewHolder {
        private ImageView productimageview,plus,minus;
        private TextView productName;
        private TextView productBrand;
        private TextView productPrice,quantity;
        private LinearLayout btnLayout;
      Button clear;
      Button add;

        public PdtVh(@NonNull View itemView) {
            super(itemView);

            productimageview = itemView.findViewById(R.id.productimageview);
            productName = itemView.findViewById(R.id.product_name);
            productBrand = itemView.findViewById(R.id.product_brand);
            productPrice = itemView.findViewById(R.id.product_price);
            btnLayout = itemView.findViewById(R.id.btnLayout);
            clear = itemView.findViewById(R.id.clear);
            add = itemView.findViewById(R.id.add);
            plus=itemView.findViewById(R.id.plus);
            minus=itemView.findViewById(R.id.minus);
            quantity=itemView.findViewById(R.id.quantity);



        }
    }
}
