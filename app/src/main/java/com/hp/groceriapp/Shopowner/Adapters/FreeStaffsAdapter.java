package com.hp.groceriapp.Shopowner.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Shopowner.Model.FreeStaffModel;

import de.hdodenhof.circleimageview.CircleImageView;

public class FreeStaffsAdapter extends RecyclerView.Adapter<FreeStaffsAdapter.FreeStaffVh> {

    Context context;
    FreeStaffModel freeStaffModel;
    private AppPreferences appPreferences;

    public FreeStaffsAdapter(Context context, FreeStaffModel freeStaffModel) {
        this.context = context;
        this.freeStaffModel = freeStaffModel;
    }

    @NonNull
    @Override
    public FreeStaffVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_staff_list_item, parent, false);
        appPreferences = AppPreferences.getInstance(context,context. getResources().getString(R.string.app_name));

        return new FreeStaffVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FreeStaffVh holder, int position) {

        Glide.with(context).load(freeStaffModel.getStaff_Details().get(position).getPhoto()).into(holder.staffdp);
        holder.staffname.setText(freeStaffModel.getStaff_Details().get(position).getName());

        holder.itemView.setOnClickListener(v -> {
            appPreferences.saveData("freestaffId",freeStaffModel.getStaff_Details().get(position).getStaff_id());
        });

    }

    @Override
    public int getItemCount() {
        return freeStaffModel.getStaff_Details().size();
    }

    class FreeStaffVh extends RecyclerView.ViewHolder{
        CircleImageView staffdp;
        TextView staffname;
        public FreeStaffVh(@NonNull View itemView) {
            super(itemView);
            staffdp=itemView.findViewById(R.id.staffSingleImg);
            staffname=itemView.findViewById(R.id.singlestaffname);
        }
    }
}
