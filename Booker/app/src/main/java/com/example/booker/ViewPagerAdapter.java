package com.example.booker;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Ralph_Chao on 2016/11/30.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{
    final int PAGE_CONT = 3;
    private String tabTitles [] = new String[] {"MyBook", "NewBook", "ContactInfo"};
    private Context context;


    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        Log.d("Adapter:" ,"Call Adapter");
    }

    @Override
    public int getCount() {
        return PAGE_CONT;
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case 0:
                return MyBookRoot.newInstance();
            case 1:
                return NewBookRoot.newInstance();
            default:
                return ContactRoot.newInstance();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
                return tabTitles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}


