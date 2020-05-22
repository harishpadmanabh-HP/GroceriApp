package com.hp.groceriapp.Customer.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.Customer.CustomerModels.Cust_SearchModel;
import com.hp.groceriapp.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<BuyProduct_Adapter.PdtVh> {

    Context context;
    Cust_SearchModel cust_searchModel;
    private AppPreferences appPreferences;

    ArrayList<String> pdtid;
    ArrayList<String> pdtQunatity;

    public SearchAdapter(Context context, Cust_SearchModel cust_searchModel, ArrayList<String> pdtid, ArrayList<String> pdtQunatity) {
        this.context = context;
        this.cust_searchModel = cust_searchModel;
        this.pdtid = pdtid;
        this.pdtQunatity = pdtQunatity;
    }

    @NonNull
    @Override
    public BuyProduct_Adapter.PdtVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customersinglebuyproduct, parent, false);
        appPreferences = AppPreferences.getInstance(context, context.getResources().getString(R.string.app_name));

        return new BuyProduct_Adapter.PdtVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyProduct_Adapter.PdtVh holder, int position) {

        holder.btnLayout.setVisibility(View.GONE);
        holder.quantity.setText("0");


        holder.minus.setOnClickListener(v -> {
            if (Integer.parseInt(holder.quantity.getText().toString()) >= 1) {
                holder.quantity.setText(String.valueOf(Integer.parseInt(holder.quantity.getText().toString()) - 1));

                if (Integer.parseInt(holder.quantity.getText().toString()) == 0) {

                    holder.btnLayout.setVisibility(View.GONE);

                }

            } else if (Integer.parseInt(holder.quantity.getText().toString()) == 0) {
                holder.btnLayout.setVisibility(View.GONE);


            }
        });
        holder.plus.setOnClickListener(v -> {
            holder.quantity.setText(String.valueOf(Integer.parseInt(holder.quantity.getText().toString()) + 1));

            holder.btnLayout.setVisibility(View.VISIBLE);


        });

        Glide.with(context).load(cust_searchModel.getProduct_Details().get(position).getPhoto()).placeholder(R.drawable.noitem).into(holder.productimageview);
        holder.productName.setText(cust_searchModel.getProduct_Details().get(position).getProduct_name());
        holder.productBrand.setText("Brand : " + cust_searchModel.getProduct_Details().get(position).getBrand());
        holder.productPrice.setText(cust_searchModel.getProduct_Details().get(position).getPrice() + " Rs");
        holder.clear.setOnClickListener(v -> {
            holder.quantity.setText("0");
            holder.btnLayout.setVisibility(View.GONE);

        });


        holder.add.setOnClickListener(v -> {


            String id=cust_searchModel.getProduct_Details().get(position).getProduct_id();
            String qty=holder.quantity.getText().toString();

            pdtid.add(id);
            pdtQunatity.add(qty);
            holder.productName.setTextColor(Color.GREEN);
            holder.productName.setText(holder.productName.getText().toString()+"\n"+qty+" nos");

            Toast.makeText(context, ""+holder.quantity.getText().toString(), Toast.LENGTH_SHORT).show();
        });


        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, ""+cust_searchModel.getProduct_Details().get(position).getProduct_id(), Toast.LENGTH_SHORT).show();

        });

    }
    public ArrayList<String> getPdtid(){
        return pdtid;
    }
    public ArrayList<String> getPdtQunatity(){
        return pdtQunatity;
    }

    @Override
    public int getItemCount() {
        return cust_searchModel.getProduct_Details().size();
    }
}
