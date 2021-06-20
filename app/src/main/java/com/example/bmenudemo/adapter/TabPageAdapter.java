package com.example.bmenudemo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bmenudemo.activities.pageFragments.ftab1;
import com.example.bmenudemo.activities.pageFragments.ftab2;
import com.example.bmenudemo.activities.pageFragments.ftab3;

public class TabPageAdapter extends FragmentPagerAdapter {

    int tabcount;

    public TabPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 : return new ftab1();
            case 1 : return new ftab2();
            case 2 : return new ftab3();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }

}
