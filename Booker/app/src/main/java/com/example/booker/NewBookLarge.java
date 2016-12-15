package com.example.booker;

import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Ralph_Chao on 2016/12/12.
 */

public class NewBookLarge extends Fragment {

    private MyBookListAdapter listAdapter;
    ArrayList<BookContent> bookList = new ArrayList<>();
    ListView listView;
    BookerData dbHelper;
    SQLiteDatabase db;

    public static NewBookLarge newInstance() {
        NewBookLarge newBookLarge = new NewBookLarge();
        return newBookLarge;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getData();
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.new_book, container, false);
        listView = (ListView) view.findViewById(R.id.new_book_list);
        listAdapter = new MyBookListAdapter(getActivity(), bookList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        FragmentTransaction transaction;
                        transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.new_content_container_large, NewBookContent.newInstance(position, bookList.get(position)));
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        transaction.commit();
                    }
                }
        );
        return view;
    }


    final private SearchView.OnQueryTextListener queryListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextChange(String newText) {
            NewBookLarge.this.listAdapter.getFilter().filter(newText);
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
        String SQL = "SELECT * FROM newBookTable";
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            bookList.add(new BookContent(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }
        db.close();
    }
}

