package com.hp.groceriapp.Staff.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Staff.Models.OrderList_To_Staff_Model;

public class OrderAdpater extends RecyclerView.Adapter<OrderAdpater.OrderVh> {

    Context  context;
    OrderList_To_Staff_Model  orderList_to_staff_model;
    private AppPreferences appPreferences;

    public OrderAdpater(Context context, OrderList_To_Staff_Model orderList_to_staff_model) {
        this.context = context;
        this.orderList_to_staff_model = orderList_to_staff_model;
    }

    @NonNull
    @Override
    public OrderVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singleorderlistiem, parent, false);
        appPreferences = AppPreferences.getInstance(context, context.getResources().getString(R.string.app_name));

        return new OrderVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderVh holder, int position) {

        holder.name.setText(orderList_to_staff_model.getProduct_details().get(position).getProduct_name());
        holder.qty.setText("Quantity : "+orderList_to_staff_model.getProduct_details().get(position).getQuantity());
        holder.brand.setText("Brand : "+orderList_to_staff_model.getProduct_details().get(position).getBrand());
        holder.price.setText(orderList_to_staff_model.getProduct_details().get(position).getPrice()+" Rs");
        holder.rack.setText("Rack no : "+orderList_to_staff_model.getProduct_details().get(position).getRack_no());

    }

    @Override
    public int getItemCount() {
        return orderList_to_staff_model.getProduct_details().size();
    }

    class OrderVh extends RecyclerView.ViewHolder{

        TextView name,qty,brand,price,rack;

        public OrderVh(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.singlePdtname);
            qty=itemView.findViewById(R.id.singlePdtQuantity);
            brand=itemView.findViewById(R.id.singlepdtBrand);
            price=itemView.findViewById(R.id.singlepdtPrice);
            rack=itemView.findViewById(R.id.singlepdtRack);

        }
    }

}
