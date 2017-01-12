package com.learn.heddy.biggerbetter.backend;

import java.util.ArrayList;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;

    public ArrayList<String> getMyListData() {
        return myListData;
    }

    public void setMyListData(ArrayList<String> myListData) {
        this.myListData = myListData;
    }

    private ArrayList<String> myListData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }


}