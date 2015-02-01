package com.example.android.friendship;

import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Locale;



/**
 * Created by Chris on 1/31/2015.
 */
public class Task {
    public String name;
    public static final String ITEM_SEP = System.getProperty("line.separator");


    public final static String TITLE = "title";
    public final static String STATUS = "status";
    public final static String DATE = "date";
    public final static String FILENAME = "filename";
    public final static String POSITION = "position";
    public final static SimpleDateFormat FORMAT = new SimpleDateFormat(
            "HH:mm", Locale.US);

    private String mTitle = new String();
    private boolean mStatus;
    private String mDate = new String();

    Task(String title, Boolean status, String date) {
        this.mTitle = title;
        this.mStatus = status;
        this.mDate = date;
    }
    Task(String title, Boolean status, String date, int pos) {
        this.mTitle = title;
        this.mStatus = status;
        this.mDate = date;
    }
    // Create a new ToDoItem from data packaged in an Intent

    Task(Intent intent) {

        mTitle = intent.getStringExtra(Task.TITLE);

       // mStatus = Status.valueOf(intent.getStringExtra(Task.STATUS));
        mStatus = false;
        mDate = intent.getStringExtra(Task.DATE);
        //        try {
//            mDate = Task.FORMAT.parse(intent.getStringExtra(Task.DATE));
//        } catch (ParseException e) {
//            mDate = new Date();
//        }
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    // Take a set of String data values and
    // package them for transport in an Intent

    public static void packageIntent(Intent intent, String title,
                                String date) {

        intent.putExtra(Task.TITLE, title);
        intent.putExtra(Task.DATE, date);
    }
    public static void packageIntent(Intent intent, String title,
                                     String date, int pos) {

        intent.putExtra(Task.TITLE, title);
        intent.putExtra(Task.DATE, date);
        intent.putExtra(Task.POSITION, pos);
    }

    public String toString() {
        return mTitle + ITEM_SEP +  mStatus + ITEM_SEP
                + mDate;
    }

    public String toLog() {
        return "Title:" + mTitle + ITEM_SEP  + "Status:" + mStatus + ITEM_SEP + "Date:"
                + mDate;
    }
}
