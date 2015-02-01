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
    public final static String POINTS = "points";
    public final static SimpleDateFormat FORMAT = new SimpleDateFormat(
            "HH:mm", Locale.US);

    private String mTitle = new String();
    private boolean mStatus;
    private String mDate = new String();
    private String mPoints;

    Task(String title, Boolean status, String date, String points) {
        this.mTitle = title;
        this.mStatus = status;
        this.mDate = date;
        this.mPoints = points;
    }
    Task(String title, Boolean status, String date, String points, int pos) {
        this.mTitle = title;
        this.mStatus = status;
        this.mDate = date;
        this.mPoints = points;
    }
    // Create a new ToDoItem from data packaged in an Intent

    Task(Intent intent) {

        mTitle = intent.getStringExtra(Task.TITLE);

       // mStatus = Status.valueOf(intent.getStringExtra(Task.STATUS));
        mStatus = false;
        mDate = intent.getStringExtra(Task.DATE);
        mPoints = intent.getStringExtra(Task.POINTS);
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

    public String getPoints(){
        return mPoints;
    }
    public void setPoints(String points){
        mPoints = points;
    }

    // Take a set of String data values and
    // package them for transport in an Intent

    public static void packageIntent(Intent intent, String title,
                                String date, String points) {

        intent.putExtra(Task.TITLE, title);
        intent.putExtra(Task.DATE, date);
        intent.putExtra(Task.POINTS, points);
    }
    public static void packageIntent(Intent intent, String title,
                                     String date,String points, int pos) {

        intent.putExtra(Task.TITLE, title);
        intent.putExtra(Task.DATE, date);
        intent.putExtra(Task.POINTS, points);
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
