package com.pascal.pray.android.model;

/**
 * Created by pascal on 2018/1/4.
 */

public class CalendarModel {
    private int year ;
    private int month;
    private int dayOfMonth;
    public CalendarModel(int year , int month , int dayOfMonth){
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }
}
