package com.example.booker;


import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ralph_Chao on 2016/12/6.
 */

public class NewBookRoot extends Fragment{
    public static final String ARG_PAGE = "ARG_PAGE";
    private int currentOrientation;
    ArrayList<BookContent> bookList = new ArrayList<>();
    BookerData dbHelper;
    SQLiteDatabase db;

    public static NewBookRoot newInstance() {
        NewBookRoot fragment= new NewBookRoot();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        currentOrientation = getResources().getConfiguration().orientation;
        getData();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if(currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            view = inflater.inflate(R.layout.new_book_root, container, false);
            transaction.replace(R.id.new_book_root, NewBook.newInstance());
            transaction.commit();
        }
        else {
            view = inflater.inflate(R.layout.new_book_root_l, container, false);
            transaction.replace(R.id.list_container_large, NewBookLarge.newInstance());
            transaction.replace(R.id.new_content_container_large, NewBookContent.newInstance(0, bookList.get(0))).commit();
        }
        return view;
    }
    public void getData() {
        dbHelper = new BookerData(getActivity());
        db = dbHelper.getReadableDatabase();
        String SQL = "SELECT * FROM newBookTable";
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++) {
            bookList.add(new BookContent(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }
    }

}
