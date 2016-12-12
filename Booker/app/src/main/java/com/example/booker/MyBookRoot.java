package com.example.booker;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ralph_Chao on 2016/12/6.
 */
public class MyBookRoot extends Fragment {
    private int currentOrientation;

    public static MyBookRoot newInstance() {
        MyBookRoot fragment = new MyBookRoot();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentOrientation = getResources().getConfiguration().orientation;

        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack("bookList", FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.my_book_root, MyBook.newInstance());
        transaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Orientation Bookroot ", Integer.toString(currentOrientation));
        View view = inflater.inflate(R.layout.my_book_root, container, false);
        return view;
    }
}
