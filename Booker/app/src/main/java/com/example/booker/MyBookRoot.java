package com.example.booker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ralph_Chao on 2016/12/6.
 */

public class MyBookRoot extends Fragment {

    public static MyBookRoot newInstance() {
        MyBookRoot fragment = new MyBookRoot();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_book_root, container, false);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.my_book_root, MyBook.newInstance());
        transaction.commit();
        return view;
    }

}
