package com.example.booker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.w3c.dom.Text;

/**
 * Created by Ralph_Chao on 2016/11/30.
 */

public class DefaultFragment extends android.support.v4.app.Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static DefaultFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        DefaultFragment fragment = new DefaultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.default_fragment, container, false);
        TextView textView = (TextView)view.findViewById(R.id.default_text);
        textView.setText("#"+mPage);

        return view;
    }

}
