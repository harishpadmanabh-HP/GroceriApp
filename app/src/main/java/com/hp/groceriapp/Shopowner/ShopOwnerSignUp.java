package com.hp.groceriapp.Shopowner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Shopowner.Fragments.SigninFragment;
import com.hp.groceriapp.Shopowner.Fragments.SignupFragment;
import com.hp.groceriapp.Adapters.TabAdapter;

public class ShopOwnerSignUp extends AppCompatActivity {
    private ViewPager mViewPager;


    private TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupspare);
        //Tabs
        mViewPager = (ViewPager) findViewById(R.id.main_tabPager);
        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);

//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//
//        mViewPager.setAdapter(mSectionsPagerAdapter);
//
//        mTabLayout.setupWithViewPager(mViewPager);
    TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new SigninFragment(), "Sign in");
        adapter.addFragment(new SignupFragment(), "Sign Up");
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
