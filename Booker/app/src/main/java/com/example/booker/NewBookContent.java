package com.example.booker;

import android.content.ContentValues;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ralph_Chao on 2016/12/12.
 */

public class NewBookContent extends Fragment {

    public static final String ARG_ITEM = "ARG_ITEM";
    public static final String ARG_ID = "ARG_ID";
    public static final String ARG_SYNOPSIS = "ARG_SYNOPSIS";
    public static final String ARG_TITLE = "ARG_TITLE";
    public static final String ARG_AUTHOR = "ARG_AUTHOR";
    private int mItem;
    private String synopsis;
    private String title;
    private String author;
    BookerData dbHelper;
    SQLiteDatabase db;

    public static NewBookContent newInstance(int item, BookContent bookContent) {
        Bundle args = new Bundle();
        args.putInt(ARG_ITEM, item);
        args.putInt(ARG_ID, bookContent.getId());
        args.putString(ARG_SYNOPSIS, bookContent.getSynopsis());
        args.putString(ARG_TITLE, bookContent.getTitle());
        args.putString(ARG_AUTHOR, bookContent.getAuthor());
        NewBookContent newBookContent = new NewBookContent();
        newBookContent.setArguments(args);
        return newBookContent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mItem = getArguments().getInt(ARG_ITEM);
        synopsis = getArguments().getString(ARG_SYNOPSIS);
        title = getArguments().getString(ARG_TITLE);
        author = getArguments().getString(ARG_AUTHOR);
        Log.d("Create", "NewBook Content Create " + Integer.toString(mItem));
        dbHelper = new BookerData(getActivity());
        db = dbHelper.getWritableDatabase();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("NewBookContentView", "ViewCreate");
        View view = inflater.inflate(R.layout.new_book_content, container, false);
        TextView textView = (TextView)view.findViewById(R.id.new_content_text);
        textView.setText(synopsis);
        Button button = (Button)view.findViewById(R.id.add_to_my_book);
        button.setText("加入我的書架");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SQL = "SELECT * FROM myBookTable WHERE title = '" + title +"'";
                Cursor cursor = db.rawQuery(SQL, null);
                if(cursor.getCount() != 0)
                    Toast.makeText(getContext(),"已存在於書架中", Toast.LENGTH_SHORT).show();
                else {
                    ContentValues values = new ContentValues();
                    values.put("title", title);
                    values.put("author", author);
                    values.put("synopsis", synopsis);
                    db.insert("myBookTable", null, values);

                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                        transaction.replace(R.id.my_list_container_large, MyBookLarge.newInstance());
                    else
                        transaction.replace(R.id.my_book_root, MyBook.newInstance());
                    transaction.commit();

                    Toast.makeText(getContext(),"加入完成", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });
        return view;
    }
}
