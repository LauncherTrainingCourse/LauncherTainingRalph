package com.example.booker;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Ralph_Chao on 2016/11/30.
 */

public class DefaultFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private ContactListAdapter listAdapter;
    ListView listView;
    private ArrayList<ContactData> contactInfo;

    private static final Uri CONTACTS_URI = ContactsContract.Contacts.CONTENT_URI;
    private static final Uri PHONE_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private static final Uri EMAIL_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
    private static final String _ID = ContactsContract.Contacts._ID;
    private static final String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    private static final String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
    private static final String CONTACT_ID = ContactsContract.Data.CONTACT_ID;

    private static final String PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
    private static final String PHONE_TYPE = ContactsContract.CommonDataKinds.Phone.TYPE;
    private static final String EMAIL_DATA = ContactsContract.CommonDataKinds.Email.DATA;
    private static final String EMAIL_TYPE = ContactsContract.CommonDataKinds.Email.TYPE;


    public static DefaultFragment newInstance() {
        DefaultFragment fragment = new DefaultFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContentResolver contentResolver = getActivity().getContentResolver();
        Cursor cursor = contentResolver.query(CONTACTS_URI, null, null, null, null);
        contactInfo = new ArrayList<>();

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(_ID));
            String displayName = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
            ContactData contactData = new ContactData(displayName);

            String selection = CONTACT_ID + "=" + id; //condition

            int hasPhoneNumber = cursor.getInt(cursor.getColumnIndex(HAS_PHONE_NUMBER));
            if(hasPhoneNumber > 0) {
                Cursor c = contentResolver.query(PHONE_URI, null, selection, null, null);
                while(c.moveToNext()) {
                    String phoneNumber = c.getString(c.getColumnIndex(PHONE_NUMBER));
                    contactData.setPhone(phoneNumber);
                }
                c.close();
            }

            Cursor c = contentResolver.query(EMAIL_URI, null, selection, null, null);
            while(c.moveToNext()) {
                String email = c.getString(c.getColumnIndex(EMAIL_DATA));
                contactData.setEmail(email);
            }
            c.close();
            contactInfo.add(contactData);
        }
        cursor.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.default_fragment, container, false);

        for(int i = 0; i < contactInfo.size(); i++) {
            ContactData data = contactInfo.get(i);
            Log.d("Contact name", data.getName());
        }

        listView = (ListView)view.findViewById(R.id.contact_list);
        listAdapter = new ContactListAdapter(getActivity(), contactInfo);
        listView.setAdapter(listAdapter);

        /*Button button = (Button)view.findViewById(R.id.add_contact);
        button.setText("新增聯絡人");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertContactData insertContactData = new InsertContactData();
                insertContactData.show(getFragmentManager(), "InsertData");
            }
        });*/

        return view;
    }
}
