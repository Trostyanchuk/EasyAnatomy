package com.example.EasyAnatomy.test;

import java.util.Date;

public class Result {

    private String date;
    private int mark;

    public Result(String date, int mark) {
        this.date = date;
        this.mark = mark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
