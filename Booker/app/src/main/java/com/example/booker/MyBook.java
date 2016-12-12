package com.example.booker;

import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by Ralph_Chao on 2016/11/30.
 */

public class MyBook extends Fragment {
    private MyBookListAdapter listAdapter;
    ArrayList<BookContent> bookList = new ArrayList<>();
    ListView listView;
    BookerData dbHelper;
    SQLiteDatabase db;
    int defaultPosition = 0;
    private int currentOrientation;

    public static MyBook newInstance() {
        MyBook myBook = new MyBook();
        return myBook;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        currentOrientation = getResources().getConfiguration().orientation;
        getData();
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        if(currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            view = inflater.inflate(R.layout.my_book, container, false);
            listView = (ListView) view.findViewById(R.id.my_book_list);
            listAdapter = new MyBookListAdapter(getActivity(), bookList);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            FragmentTransaction transaction;
                            transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.my_book_root, MyBookContent.newInstance(position, bookList.get(position)));
                            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                            transaction.addToBackStack("bookList");
                            transaction.commit();
                        }
                    }
            );
        }
        else{
            view = inflater.inflate(R.layout.my_book_l, container, false);
            listView = (ListView) view.findViewById(R.id.my_book_list);
            listAdapter = new MyBookListAdapter(getActivity(), bookList);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            FragmentTransaction transaction;
                            transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.content_container_large, MyBookContent.newInstance(position, bookList.get(position)));
                            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                            transaction.commit();
                        }
                    }
            );
            FragmentTransaction transaction;
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.content_container_large, MyBookContent.newInstance(defaultPosition, bookList.get(defaultPosition)));
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.commit();
        }
        return view;
    }

    final private SearchView.OnQueryTextListener queryListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextChange(String newText) {
            MyBook.this.listAdapter.getFilter().filter(newText);
            return false;
        }

        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setOnQueryTextListener(queryListener);
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

    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
