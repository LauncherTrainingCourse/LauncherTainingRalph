package com.example.booker;

import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Ralph_Chao on 2016/12/6.
 */
public class MyBookRoot extends Fragment {
    private int currentOrientation;
    ArrayList<BookContent> bookList = new ArrayList<>();
    BookerData dbHelper;
    SQLiteDatabase db;

    public static MyBookRoot newInstance() {
        MyBookRoot fragment = new MyBookRoot();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        currentOrientation = getResources().getConfiguration().orientation;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Orientation Bookroot ", Integer.toString(currentOrientation));
        View view;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack("bookList", FragmentManager.POP_BACK_STACK_INCLUSIVE);

        if(currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            view = inflater.inflate(R.layout.my_book_root, container, false);
            transaction.replace(R.id.my_book_root, MyBook.newInstance());
            transaction.commit();
        }
        else {
            view = inflater.inflate(R.layout.my_book_root_l, container, false);
            transaction.replace(R.id.my_list_container_large, MyBookLarge.newInstance());
            transaction.replace(R.id.my_content_container_large, MyBookContent.newInstance(bookList.get(0))).commit();
        }

        return view;
    }

    public void getData() {
        dbHelper = new BookerData(getActivity());
        db = dbHelper.getReadableDatabase();
        String SQL = "SELECT * FROM myBookTable";
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++) {
            bookList.add(new BookContent(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }
    }
}
