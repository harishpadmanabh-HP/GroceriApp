package com.hp.groceriapp.Shopowner.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Shopowner.Fragments.StaffDetailsFragment;
import com.hp.groceriapp.Shopowner.Model.StaffsListModel;
import com.hp.groceriapp.Utils.FragmentSwitcher;

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
           //store data
            appPreferences.saveData("selectedStaffid",staffsListModel.getStaff_Details().get(position).getStaff_id());
            appPreferences.saveData("selectedStaffname",staffsListModel.getStaff_Details().get(position).getName());
            appPreferences.saveData("selectedStaffpin",staffsListModel.getStaff_Details().get(position).getPin());
            appPreferences.saveData("selectedStaffempid",staffsListModel.getStaff_Details().get(position).getEmp_id());
            appPreferences.saveData("selectedStaffphoto",staffsListModel.getStaff_Details().get(position).getPhoto());
            appPreferences.saveData("selectedStaffphone",staffsListModel.getStaff_Details().get(position).getPhone());

            //navigate to staff details fragment
            new FragmentSwitcher().replaceFragment(new StaffDetailsFragment(), (FragmentActivity) context);



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