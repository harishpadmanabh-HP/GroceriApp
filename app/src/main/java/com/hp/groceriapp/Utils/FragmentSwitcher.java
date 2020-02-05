package com.hp.groceriapp.Utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.hp.groceriapp.R;
import com.hp.groceriapp.Shopowner.Fragments.AddProductFragment;

public class FragmentSwitcher extends Fragment {


    public  void replaceFragment(Fragment fragment) {
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.frameLayout,fragment);
        t.commit();    }
}
