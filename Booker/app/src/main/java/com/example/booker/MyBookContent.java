package com.example.booker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ralph_Chao on 2016/12/1.
 */

public class MyBookContent extends Fragment {

    public static final String ARG_ITEM = "ARG_ITEM";
    private int mItem;

    public static MyBookContent newInstance(int item) {
        Bundle args = new Bundle();
        args.putInt(ARG_ITEM, item);
        MyBookContent myBookContent = new MyBookContent();
        myBookContent.setArguments(args);
        return myBookContent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItem = getArguments().getInt(ARG_ITEM);
        //Toast.makeText(getContext(), "Content Create", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_book_content, container, false);
        TextView textView = (TextView)view.findViewById(R.id.content_text);
        textView.setText("fragment"+mItem);
        //Toast.makeText(getContext(), "Content Create", Toast.LENGTH_SHORT).show();
        return view;
    }
}