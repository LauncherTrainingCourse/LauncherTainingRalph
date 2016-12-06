package com.example.booker;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ralph_Chao on 2016/11/30.
 */

public class MyBook extends Fragment {
    private MyBookListAdapter listAdapter;
    ArrayList<BookContent> bookList = new ArrayList<>();
    ListView listView;

    public static MyBook newInstance() {
        MyBook myBook = new MyBook();
        return myBook;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        insertData();                           //read book data
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_book, container, false);

        Log.i("my book create view", "create view");
        listView = (ListView) view.findViewById(R.id.my_book_list);
        listAdapter = new MyBookListAdapter(getActivity(), bookList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        FragmentTransaction transaction;
                        transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.my_book_root, MyBookContent.newInstance(position));
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                }
        );

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

    public void insertData() {
        bookList.add(new BookContent("AAA", "CCC", "DDD"));
        bookList.add(new BookContent("BBB", "FFF", "OOO"));
        bookList.add(new BookContent("DDD", "XXX", "KKK"));
        bookList.add(new BookContent("GGG", "JJJ", "PPP"));
        bookList.add(new BookContent("AAF", "CCC", "DDD"));
        bookList.add(new BookContent("BBG", "FFF", "OOO"));
        bookList.add(new BookContent("DDV", "XXX", "KKK"));
        bookList.add(new BookContent("VVV", "JJJ", "PPP"));
        bookList.add(new BookContent("HHH", "CCC", "DDD"));
        bookList.add(new BookContent("YYY", "FFF", "OOO"));
        bookList.add(new BookContent("DPP", "XXX", "KKK"));
        bookList.add(new BookContent("GKK", "JJJ", "PPP"));
    }



   /* @Override
    public void onPause() {
        Toast.makeText(getContext(),"Pause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    public void onStop() {
        Toast.makeText(getContext(), "Stop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Toast.makeText(getContext(), "Destroy", Toast.LENGTH_SHORT).show();
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Toast.makeText(getContext(), "Detach", Toast.LENGTH_SHORT).show();
        super.onDetach();
    }
*/
}
