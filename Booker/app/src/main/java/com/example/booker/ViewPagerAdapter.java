package com.example.booker;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Ralph_Chao on 2016/11/30.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    final int PAGE_CONT = 3;
    private String tabTitles [] = new String[] {"MyBook", "NewBook", "ContactInfo"};
    private Context context;
    private MyBook  myBookFragment;
    MyBookContent myBookContent;
    private final FragmentManager fragmentManager;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        fragmentManager = fm;
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
                if(myBookFragment == null) {
                    myBookFragment = MyBook.newInstance(position, new PageFragmentListener() {
                        @Override
                        public void onSwitchToNextFragment(int item) {
                            myBookContent = MyBookContent.newInstance(item);
                            //fragmentManager.beginTransaction().remove(myBookFragment).commit();
                            //fragmentManager.beginTransaction().add(myBookContent, "my_book_content");
                            //fragmentManager.beginTransaction().replace(R.id.my_book, myBookContent).commit();
                            notifyDataSetChanged();
                        }
                    });
                }
                return myBookFragment;
            default:
                return DefaultFragment.newInstance(position+1);
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
                return tabTitles[position];
    }


}
