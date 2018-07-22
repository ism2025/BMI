package com.example.ismailamassi.bmi.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ismailamassi.bmi.Fragments.PregnantFragment;
import com.example.ismailamassi.bmi.Fragments.bmiFragment;


public class TapAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments = {new PregnantFragment(), new bmiFragment()};
    private  String [] title = {"حاسبة الحمل", "حاسبة الوزن"};

    public TapAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
