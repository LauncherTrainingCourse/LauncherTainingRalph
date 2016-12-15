package com.example.booker;

import java.util.ArrayList;

/**
 * Created by Ralph_Chao on 2016/12/13.
 */

public class ContactData {
    private ArrayList<String> phone = new ArrayList<>();
    private ArrayList<String> email = new ArrayList<>();
    private String name;

    public ContactData (String name) {
        this.name = name;
    }

    public void setPhone(String input) {
        this.phone.add(input);
    }

    public void setEmail(String input) {
        this.email.add(input);
    }

    public void setName(String input) {
        this.name = input;
    }

    public String getName() {
        return name;
    }

    public ArrayList getPhone() {
        return phone;
    }

    public ArrayList getEmail() {
        return email;
    }
}
