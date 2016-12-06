package com.example.booker;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;

/**
 * Created by Ralph_Chao on 2016/11/30.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_CONT = 3;
    private String tabTitles [] = new String[] {"MyBook", "NewBook", "ContactInfo"};
    private Context context;
    Fragment myBookFragment;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
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
                return NewBookRoot.newInstance(position+1);
            default:
                return DefaultFragment.newInstance(position+1);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
                return tabTitles[position];
    }

}


