package com.hp.groceriapp.Customer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.Customer.CustomerModels.ShopListModel;
import com.hp.groceriapp.Customer.ViewProducts;
import com.hp.groceriapp.R;

public class ShopList_Adapter extends RecyclerView.Adapter<ShopList_Adapter.ShopVH> {

    ShopListModel shopListModel;
    Context context;
    private AppPreferences appPreferences;

    public ShopList_Adapter(ShopListModel shopListModel, Context context) {
        this.shopListModel = shopListModel;
        this.context = context;
    }

    @NonNull
    @Override
    public ShopVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.singleshopdesign, parent, false);
        appPreferences = AppPreferences.getInstance(context,context. getResources().getString(R.string.app_name));

        return new ShopVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopVH holder, int position) {

        holder.name.setText(shopListModel.getShop_Details().get(position).getShop_name());
        holder.owner.setText("Owner : "+shopListModel.getShop_Details().get(position).getName());
        holder.email.setText("Email : "+shopListModel.getShop_Details().get(position).getEmail());
        holder.phone.setText("Phone : "+shopListModel.getShop_Details().get(position).getPhone());
        holder.add.setText("Address : "+shopListModel.getShop_Details().get(position).getBuilding_address());

        holder.itemView.setOnClickListener(v -> {
            appPreferences.saveData("shopid",shopListModel.getShop_Details().get(position).getId());

            context.startActivity(new Intent(context, ViewProducts.class));
        });
    }

    @Override
    public int getItemCount() {
        return shopListModel.getShop_Details().size();
    }

    class ShopVH extends RecyclerView.ViewHolder{

        TextView name,owner,email,phone,add;

        public ShopVH(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.singleshopname);
            owner=itemView.findViewById(R.id.singleshopowner);
            email=itemView.findViewById(R.id.singleshopemail);
            phone=itemView.findViewById(R.id.singleshopPhone);
            add=itemView.findViewById(R.id.singleshopadd);


        }
    }
}
