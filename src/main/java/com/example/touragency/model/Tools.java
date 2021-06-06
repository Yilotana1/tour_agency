package com.example.touragency.model;

import java.util.Calendar;
import java.sql.Date;

public class Tools {

    public static Calendar getCalendarFromDate(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

}
