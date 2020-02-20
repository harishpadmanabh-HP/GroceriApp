package com.hp.groceriapp.Shopowner.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.Adapters.StaffAdapter;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Shopowner.Model.StaffsListModel;
import com.hp.groceriapp.Utils.FragmentSwitcher;

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

    public StaffsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_staffs, container, false);

        initView(root);
        mNoitemimg.setVisibility(View.GONE);
        mNoitemtextview.setVisibility(View.GONE);
        appPreferences = AppPreferences.getInstance(getActivity(), getResources().getString(R.string.app_name));
        new Retro().getApi().STAFFS_LIST_MODEL_CALL(appPreferences.getData("adminid")).enqueue(new Callback<StaffsListModel>() {
            @Override
            public void onResponse(Call<StaffsListModel> call, Response<StaffsListModel> response) {
                staffsListModel = response.body();
                if (staffsListModel.getStatus().equalsIgnoreCase("success")) {
                    mStaffRV.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
                    mStaffRV.setAdapter(new StaffAdapter(staffsListModel, getActivity().getApplicationContext()));


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

        mAddstaffFAB.setOnClickListener(view -> {
            new FragmentSwitcher().replaceFragment(new AddStaffFragment(),getActivity());

        });


        return root;
    }

    private void initView(View root) {
        mNoitemimg = root.findViewById(R.id.noitemimg);
        mNoitemtextview = root.findViewById(R.id.noitemtextview);
        mAddstaffFAB = root.findViewById(R.id.addstaffFAB);
        mStaffRV = root.findViewById(R.id.staffRV);
    }
}
