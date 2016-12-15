package com.example.booker;

import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ralph_Chao on 2016/12/1.
 */


public class MyBookContent extends Fragment {

    public static final String ARG_ITEM = "ARG_ITEM";
    public static final String ARG_ID = "ARG_ID";
    public static final String ARG_SYNOPSIS = "ARG_SYNOPSIS";
    private String synopsis;
    private int bookID;
    BookerData dbHelper;
    SQLiteDatabase db;
    BookContent bookContent;

    public static MyBookContent newInstance(BookContent bookContent) {
        Bundle args = new Bundle();
        args.putInt(ARG_ID, bookContent.getId());
        args.putString(ARG_SYNOPSIS, bookContent.getSynopsis());
        MyBookContent myBookContent = new MyBookContent();
        myBookContent.setArguments(args);
        return myBookContent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        synopsis = getArguments().getString(ARG_SYNOPSIS);
        bookID = getArguments().getInt(ARG_ID);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("BookContentView", "ViewCreate");
        View view = inflater.inflate(R.layout.my_book_content, container, false);
        TextView textView = (TextView) view.findViewById(R.id.content_text);
        textView.setText(synopsis);
        Button button = (Button) view.findViewById(R.id.delete_my_book);
        button.setText("從我的書架移除");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new BookerData(getActivity());
                db = dbHelper.getWritableDatabase();
                db.delete("myBookTable", "_id=" + bookID, null);
                Toast.makeText(getContext(), "已移除", Toast.LENGTH_SHORT).show();
                db.close();
                ((MainActivity) getActivity()).updateList();
            }
        });
        return view;
    }
}
