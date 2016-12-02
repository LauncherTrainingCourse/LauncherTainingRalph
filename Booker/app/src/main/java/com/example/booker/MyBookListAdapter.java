package com.example.booker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Ralph_Chao on 2016/11/30.
 */

public class MyBookListAdapter extends BaseAdapter implements Filterable{
    private LayoutInflater inflater;
    private ArrayList<BookContent> myBook;
    private ArrayList<BookContent> originalValues;
    private MyBookFilter filter;

    public MyBookListAdapter(Context context, ArrayList<BookContent> myBook) {
        this.inflater = LayoutInflater.from(context);
        this.myBook = myBook;
    }

    private class ViewHolder {
        TextView textTitle;
        TextView textAuthor;
        public ViewHolder (TextView textTitle, TextView textAuthor) {
            this.textAuthor = textAuthor;
            this.textTitle = textTitle;
        }
    }

    @Override
    public int getCount() {
        return this.myBook.size();
    }

    @Override
    public BookContent getItem(int arg) {
        return this.myBook.get(arg);
    }

    @Override
    public long getItemId(int position) {
        return this.myBook.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.my_book_item, null);
            holder = new ViewHolder ((TextView)convertView.findViewById(R.id.book_title), (TextView)convertView.findViewById(R.id.book_author));
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        BookContent book = getItem(position);
        holder.textTitle.setText(book.getTitle());
        holder.textAuthor.setText(book.getAuthor());

        return convertView;
    }

    @Override
    public android.widget.Filter getFilter() {
        if(filter == null)
            filter = new MyBookFilter();

        return filter;
    }
    private class MyBookFilter extends Filter {

        @Override
        protected void  publishResults(CharSequence constraint, FilterResults results) {
            myBook = (ArrayList)results.values;
            if(results.count > 0)
                notifyDataSetChanged();
            else
                notifyDataSetInvalidated();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //constraint = constraint.toString();
            FilterResults results = new FilterResults();
            if(originalValues == null) {
                synchronized (this) {
                    originalValues = new ArrayList<>(myBook);
                }
            }
            if(constraint != null && constraint.toString().length() > 0) {
                ArrayList<BookContent> filterItem = new ArrayList<>();
                for(int i = 0; i < originalValues.size(); i++) {
                    BookContent target = originalValues.get(i);
                    if(target.getTitle().contains(constraint) || target.getAuthor().contains(constraint))
                        filterItem.add(target);
                }
                results.count = filterItem.size();
                results.values = filterItem;
            }
            else {
                synchronized (this) {
                    results.count = originalValues.size();
                    results.values = originalValues;
                }
            }
            return results;
        }
    }
}


