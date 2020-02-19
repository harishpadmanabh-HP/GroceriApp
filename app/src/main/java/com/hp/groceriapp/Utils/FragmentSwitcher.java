package com.hp.groceriapp.Utils;

import android.app.Application;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.hp.groceriapp.R;
import com.hp.groceriapp.Shopowner.Fragments.AddProductFragment;
import com.hp.groceriapp.Shopowner.Fragments.AddStaffFragment;

public class FragmentSwitcher extends Fragment {



    public void replaceFragment(Fragment fragment, FragmentActivity activity) {
        FragmentTransaction t =activity.getSupportFragmentManager().beginTransaction();
        t.replace(R.id.frameLayout,fragment);
        t.commit();
    }
}
