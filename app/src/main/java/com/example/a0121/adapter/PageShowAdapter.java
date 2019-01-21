package com.example.a0121.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.a0121.fragment.HomeFragment;
import com.example.a0121.fragment.MyFragment;

/**
 * はすてすゃの
 * 2019-01-21.
 */
public class PageShowAdapter extends FragmentPagerAdapter {
    public PageShowAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new HomeFragment();
            case 1:
                return new MyFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
