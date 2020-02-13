package com.hp.groceriapp.Adapters;

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
import com.hp.groceriapp.Shopowner.Model.StaffsListModel;

import de.hdodenhof.circleimageview.CircleImageView;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffVH> {

    private AppPreferences appPreferences;
    StaffsListModel staffsListModel;
    Context context;

    public StaffAdapter(StaffsListModel staffsListModel, Context context) {
        this.staffsListModel = staffsListModel;
        this.context = context;
    }




    @NonNull
    @Override
    public StaffVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_staff_list_item, parent, false);
        appPreferences = AppPreferences.getInstance(context,context. getResources().getString(R.string.app_name));

        return new StaffVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffVH holder, int position) {
        Glide.with(context).load(staffsListModel.getStaff_Details().get(position).getPhoto()).placeholder(R.drawable.noitem).into(holder.staffdp);
        holder.staffname.setText(staffsListModel.getStaff_Details().get(position).getName());

        holder.itemView.setOnClickListener(view -> {
            appPreferences.saveData("selectedStaffid",staffsListModel.getStaff_Details().get(position).getStaff_id());
        });
    }

    @Override
    public int getItemCount() {
        return staffsListModel.getStaff_Details().size();
    }

    class StaffVH extends RecyclerView.ViewHolder{

        CircleImageView staffdp;
        TextView staffname;
        public StaffVH(@NonNull View itemView) {
            super(itemView);
            staffdp=itemView.findViewById(R.id.staffSingleImg);
            staffname=itemView.findViewById(R.id.singlestaffname);
        }
    }
}
