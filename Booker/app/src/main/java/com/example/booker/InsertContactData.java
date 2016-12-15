package com.example.booker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ralph_Chao on 2016/12/13.
 */

public class InsertContactData extends DialogFragment {
    EditText inputName;

    public static InsertContactData newInstance() {
        InsertContactData fragment = new InsertContactData();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(), R.layout.add_contact, null);
        inputName = (EditText)view.findViewById(R.id.add_name);
        builder.setTitle("Enter you contact info").setView(view) .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                            }});
        return builder.create();
    }

}
