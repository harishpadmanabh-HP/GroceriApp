package com.hp.groceriapp.Shopowner.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Shopowner.Model.ProductlistModel;

public class Admin_Productlist_Adapter extends RecyclerView.Adapter<Admin_Productlist_Adapter.ProductsVH> {

  static   ProductlistModel productlistModel;
    Context context;
   static int pos_for_context;

    public Admin_Productlist_Adapter(ProductlistModel productlistModel, Context context) {
        this.productlistModel = productlistModel;
        this.context = context;
    }



    @NonNull
    @Override
    public ProductsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.admin_product_listitem, parent, false);

        return new ProductsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsVH holder, int position) {

        Log.e("PRODUCT ID",productlistModel.getProduct_Details().get(position).getProduct_id()+" "+
                productlistModel.getProduct_Details().get(position).getProduct_name()  );
        Glide.with(context).load(productlistModel.getProduct_Details().get(position).getPhoto()).placeholder(R.drawable.noitem).into(holder.dp);
        holder.pName.setText(productlistModel.getProduct_Details().get(position).getProduct_name()   );
        holder.pBrand.setText( "Brand : "+productlistModel.getProduct_Details().get(position).getBrand());
        holder.pPrice.setText(productlistModel.getProduct_Details().get(position).getPrice()+" Rs");
        holder.pQuantuty.setText("Quantity : "+productlistModel.getProduct_Details().get(position).getQuantity());

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                Log.e("Admin_Productlist_Adap","onCreateContextMenu called");
                menu.setHeaderTitle("OPTIONS");
                menu.add("Edit");
                menu.add("Delete");
                set_pid_forContextMenuClickListener(position);


            }

        });






    }
  public    void set_pid_forContextMenuClickListener(int position)
    {

     pos_for_context=position;
    }
    public static String get_pid_forContextMenuClickListener()
    {

        String pid_forContextMenuClickListener = productlistModel.getProduct_Details().get(pos_for_context).getProduct_id();
        Log.e("Get pid",pid_forContextMenuClickListener);
        return pid_forContextMenuClickListener;
    }

    @Override
    public int getItemCount() {
        return productlistModel.getProduct_Details().size();
    }

  public   class ProductsVH extends RecyclerView.ViewHolder{

        ImageView dp;
        TextView pName,pBrand,pPrice,pQuantuty;
        LinearLayout pdtLayout;

         ProductsVH(@NonNull View itemView) {
            super(itemView);
            dp=itemView.findViewById(R.id.productimageview);
            pName=itemView.findViewById(R.id.product_name);
            pBrand=itemView.findViewById(R.id.product_brand);
            pPrice=itemView.findViewById(R.id.product_price);
            pQuantuty=itemView.findViewById(R.id.product_quantity);
            pdtLayout=itemView.findViewById(R.id.pdtLayout);



        }
    }

}
