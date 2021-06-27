package com.example.touragency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

public class Tools {

    public static Calendar getCalendarFromDate(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }


    public static Calendar getCalendarFromString(String date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Calendar.getInstance();
    }


    public static int getGeneratedId(Statement statement) throws SQLException {
        ResultSet keys = statement.getGeneratedKeys();
        keys.next();
        return keys.getInt(1);
    }

}
