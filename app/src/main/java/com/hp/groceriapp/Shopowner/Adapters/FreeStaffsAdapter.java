package com.hp.groceriapp.Shopowner.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Shopowner.Model.FreeStaffModel;
import com.hp.groceriapp.Shopowner.Model.PushtoStaffModel;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreeStaffsAdapter extends RecyclerView.Adapter<FreeStaffsAdapter.FreeStaffVh> {

    Context context;
    FreeStaffModel freeStaffModel;
    private AppPreferences appPreferences;
    String adminID;

    public FreeStaffsAdapter(Context context, FreeStaffModel freeStaffModel, String adminID) {
        this.context = context;
        this.freeStaffModel = freeStaffModel;
        this.adminID = adminID;
    }

    @NonNull
    @Override
    public FreeStaffVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_staff_list_item, parent, false);
        appPreferences = AppPreferences.getInstance(context, context.getResources().getString(R.string.app_name));

        return new FreeStaffVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FreeStaffVh holder, int position) {

        Glide.with(context).load(freeStaffModel.getStaff_Details().get(position).getPhoto()).into(holder.staffdp);
        holder.staffname.setText(freeStaffModel.getStaff_Details().get(position).getName());

        holder.itemView.setOnClickListener(v -> {
            appPreferences.saveData("freestaffId", freeStaffModel.getStaff_Details().get(position).getStaff_id());

            String customerid = appPreferences.getData("notify_Customer_id");
            String freestaffid = appPreferences.getData("freestaffId");


            new Retro().getApi().pushToStaff(adminID,
                    customerid,
                    freestaffid).enqueue(new Callback<PushtoStaffModel>() {
                @Override
                public void onResponse(Call<PushtoStaffModel> call, Response<PushtoStaffModel> response) {
                    PushtoStaffModel pushtoStaffModel = response.body();
                    if (pushtoStaffModel.getSuccess() == 1) {
                        Toast.makeText(context, "Notified to staff", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Cant push notification as " + pushtoStaffModel.getResults().get(0).getError(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PushtoStaffModel> call, Throwable t) {
                    Toast.makeText(context, "Push to staff failed " + t, Toast.LENGTH_SHORT).show();
                }
            });


        });

    }

    @Override
    public int getItemCount() {
        return freeStaffModel.getStaff_Details().size();
    }

    class FreeStaffVh extends RecyclerView.ViewHolder {
        CircleImageView staffdp;
        TextView staffname;

        public FreeStaffVh(@NonNull View itemView) {
            super(itemView);
            staffdp = itemView.findViewById(R.id.staffSingleImg);
            staffname = itemView.findViewById(R.id.singlestaffname);
        }
    }
}
