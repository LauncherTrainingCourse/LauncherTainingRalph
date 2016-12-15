package com.example.booker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ralph_Chao on 2016/12/13.
 */

public class ContactListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<ContactData> contactDatas;

    private class ViewHolder {
        TextView textName;
        TextView textPhone;
        public ViewHolder (TextView textName, TextView textPhone) {
            this.textName = textName;
            this.textPhone = textPhone;
        }
    }

    public ContactListAdapter(Context context, ArrayList<ContactData> input) {
        this.inflater = LayoutInflater.from(context);
        contactDatas = input;
    }

    @Override
    public int getCount() {
        return this.contactDatas.size();
    }

    @Override
    public ContactData getItem(int arg) {
        return this.contactDatas.get(arg);
    }

    @Override
    public long getItemId(int position) {
        return this.contactDatas.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactListAdapter.ViewHolder holder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.my_book_item, null);
            holder = new ContactListAdapter.ViewHolder((TextView)convertView.findViewById(R.id.book_title), (TextView)convertView.findViewById(R.id.book_author));
            convertView.setTag(holder);
        }
        else {
            holder = (ContactListAdapter.ViewHolder)convertView.getTag();
        }
        ContactData contactInfo = getItem(position);
        holder.textName.setText(contactInfo.getName());
        holder.textPhone.setText(contactInfo.getPhone().get(0).toString());
        return convertView;
    }
}
