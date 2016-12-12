package com.example.booker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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
    public static final String ARG_ID = "ARG_ID";
    public static final String ARG_SYNOPSIS = "ARG_SYNOPSIS";
    private int mItem;
    private String synopsis;

    public static MyBookContent newInstance(int item, BookContent bookContent) {
        Bundle args = new Bundle();
        args.putInt(ARG_ITEM, item);
        args.putInt(ARG_ID, bookContent.getId());
        args.putString(ARG_SYNOPSIS, bookContent.getSynopsis());
        MyBookContent myBookContent = new MyBookContent();
        myBookContent.setArguments(args);
        return myBookContent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mItem = getArguments().getInt(ARG_ITEM);
        synopsis = getArguments().getString(ARG_SYNOPSIS);
        Log.d("Create", "Content Create " + Integer.toString(mItem));
        super.onCreate(savedInstanceState);
    }

  @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
       super.onSaveInstanceState(savedInstanceState);
       savedInstanceState.putInt("CURRENT_POSITION", mItem);
       Log.d("Set Instance", "Start save instance");
       Log.d("Set Instance", "Set prePosition "+Integer.toString(mItem));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("BookContentView", "ViewCreate");
        View view = inflater.inflate(R.layout.my_book_content, container, false);
        TextView textView = (TextView)view.findViewById(R.id.content_text);
        textView.setText(synopsis);
        textView.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
/*
    @Override
    public void onPause() {
        Toast.makeText(getContext(), "ContentPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    public void onStop() {
        Toast.makeText(getContext(), "ContentStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Toast.makeText(getContext(), "ContentDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroyView();
    }
    @Override
    public void onDetach() {
        Toast.makeText(getContext(), "ContentDetach", Toast.LENGTH_SHORT).show();
        super.onDetach();
    }*/
}
