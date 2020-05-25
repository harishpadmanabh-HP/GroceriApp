package com.hp.groceriapp.Shopowner.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.Shopowner.Adapters.StaffAdapter;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Shopowner.Model.Delete_Staff_Model;
import com.hp.groceriapp.Shopowner.Model.StaffsListModel;
import com.hp.groceriapp.Utils.FragmentSwitcher;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class StaffsFragment extends Fragment {


    private ImageView mNoitemimg;
    private TextView mNoitemtextview;
    private ExtendedFloatingActionButton mAddstaffFAB;
    private RecyclerView mStaffRV;
    private AppPreferences appPreferences;
    private StaffsListModel staffsListModel;
    private View root;
    private AlertDialog pd;


    public StaffsFragment() {
        // Required empty public constructor
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
       if(item.getTitle()== "Edit")
       {

       }
       if(item.getTitle() == "Delete")
       {
           String context_staffid=StaffAdapter.get_staffid_forContextMenuClickListener();
           MaterialAlertDialogBuilder builder =new MaterialAlertDialogBuilder(getActivity())
                   .setTitle("Delete Staff")
                   .setMessage("Are you sure you want to delete this staff ?")
                   .setIcon(R.drawable.ic_delete_forever)
                   .setCancelable(false)
                   .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           //showing dots progress dialog
                           pd.setTitle("Deleting");
                           // pd.setMessage("Please wait");
                           pd.show();
                           //api call to delete

                           new Retro().getApi().deleteStaffCall(context_staffid).enqueue(new Callback<Delete_Staff_Model>() {
                               @Override
                               public void onResponse(Call<Delete_Staff_Model> call, Response<Delete_Staff_Model> response) {
                                   Delete_Staff_Model delete_staff_model =response.body();
                                   if(delete_staff_model.getStatus().equalsIgnoreCase("Deleted Successfully")){
                                       showstaffs();
                                       Toast.makeText(getContext(), "Staff has been deleted.", Toast.LENGTH_SHORT).show();

                                       pd.dismiss();
                                       dialog.dismiss();
                                   }else
                                   { pd.dismiss();
                                       dialog.dismiss();
                                       Toast.makeText(getContext(), "Cant fetch staff details.", Toast.LENGTH_SHORT).show();
                                   }
                               }

                               @Override
                               public void onFailure(Call<Delete_Staff_Model> call, Throwable t) {
                                   pd.dismiss();
                                   dialog.dismiss();
                                   Toast.makeText(getContext(), "Delete Staff api failure : "+t, Toast.LENGTH_SHORT).show();
                               }
                           });

                       }
                   }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                         dialog.dismiss();
                       }
                   });

           builder.show();

       }



        return super.onContextItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         root = inflater.inflate(R.layout.fragment_staffs, container, false);

        initView(root);
        mNoitemimg.setVisibility(View.GONE);
        mNoitemtextview.setVisibility(View.GONE);
        appPreferences = AppPreferences.getInstance(getActivity(), getResources().getString(R.string.app_name));
        pd = new SpotsDialog(getActivity(), R.style.CustomAlert);

        showstaffs();

        mAddstaffFAB.setOnClickListener(view -> {
            //navigate to add staff
            new FragmentSwitcher().replaceFragment(new AddStaffFragment(),getActivity());

        });


        return root;
    }

    private void showstaffs() {
        new Retro().getApi().STAFFS_LIST_MODEL_CALL(appPreferences.getData("adminid")).enqueue(new Callback<StaffsListModel>() {
            @Override
            public void onResponse(Call<StaffsListModel> call, Response<StaffsListModel> response) {
                staffsListModel = response.body();
                if (staffsListModel.getStatus().equalsIgnoreCase("success")) {
                    mStaffRV.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
                    mStaffRV.setAdapter(new StaffAdapter(staffsListModel, getActivity()));


                } else {
                    mNoitemimg.setVisibility(View.VISIBLE);
                    mNoitemtextview.setVisibility(View.VISIBLE);
                    Snackbar.make(root, "No Staffs found.", BaseTransientBottomBar.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<StaffsListModel> call, Throwable t) {
                Toast.makeText(getContext(), "Stafflist API FAILURE : " + t, Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void initView(View root) {
        mNoitemimg = root.findViewById(R.id.noitemimg);
        mNoitemtextview = root.findViewById(R.id.noitemtextview);
        mAddstaffFAB = root.findViewById(R.id.addstaffFAB);
        mStaffRV = root.findViewById(R.id.staffRV);
    }
}
