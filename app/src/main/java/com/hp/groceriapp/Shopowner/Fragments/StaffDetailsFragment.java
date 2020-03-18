package com.hp.groceriapp.Shopowner.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Utils.FragmentSwitcher;
import com.hp.groceriapp.Utils.IOnBackPressed;

/**
 * A simple {@link Fragment} subclass.
 */
public class StaffDetailsFragment extends Fragment implements IOnBackPressed {


    private ImageView alertimage;
    private TextView alertname;
    private TextView alertphone;
    private TextView alertpin;
    private TextView alertempid;
    private AppPreferences appPreferences;

    public StaffDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_staff_details, container, false);
        appPreferences = AppPreferences.getInstance(getActivity(), getResources().getString(R.string.app_name));

        initView(root);

        //set details fo selected staff
        Glide.with(getContext()).load(appPreferences.getData("selectedStaffphoto")).into(alertimage);
        alertname.setText(appPreferences.getData("selectedStaffname"));
        alertphone.setText(appPreferences.getData("selectedStaffphone"));
        alertempid.setText(appPreferences.getData("selectedStaffempid"));
        alertpin.setText(appPreferences.getData("selectedStaffpin"));

        return root;

    }

    private void initView(View root) {
        alertimage = root.findViewById(R.id.alertimage);
        alertname = root.findViewById(R.id.alertname);
        alertphone = root.findViewById(R.id.alertphone);
        alertpin = root.findViewById(R.id.alertpin);
        alertempid = root.findViewById(R.id.alertempid);
    }

    @Override
    public boolean onBackPressed() {
        //navigate to add staff
        new FragmentSwitcher().replaceFragment(new StaffsFragment(),getActivity());
        return true;
    }
}
