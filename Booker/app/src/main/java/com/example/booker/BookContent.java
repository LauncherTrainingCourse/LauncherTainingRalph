package com.example.booker;

/**
 * Created by Ralph_Chao on 2016/11/30.
 */

public class BookContent {
    private String title;
    private String author;
    private String synopsis;

    public BookContent(String title, String author, String synopsis) {
        this.title = title;
        this.author = author;
        this.synopsis = synopsis;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getSynopsis() {
        return this.synopsis;
    }

    public void setTitle(String data) {
        this.title = data;
        return;
    }

    public void setAuthor(String data) {
        this.author = data;
        return;
    }

    public void setSynopsis(String data) {
        this.synopsis = data;
        return;
    }
}
