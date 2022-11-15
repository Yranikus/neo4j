package com.example.neo4j.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class PresentStudents {

    private Date date;
    private String[] presentStudents;

    public PresentStudents() {
    }

    public PresentStudents(String dateString, String[] presentStudents) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.presentStudents = presentStudents;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String dateString) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String[] getPresentStudents() {
        return presentStudents;
    }

    public void setPresentStudents(String[] presentStudents) {
        this.presentStudents = presentStudents;
    }

    @Override
    public String toString() {
        return "PresentStudents{" +
                "date=" + date +
                ", presentStudents=" + Arrays.toString(presentStudents) +
                '}';
    }
}



