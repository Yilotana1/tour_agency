package com.example.touragency;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.Locale;
import java.util.ResourceBundle;

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


    public static boolean decimalCompare(BigDecimal val1, BigDecimal val2) {
        return val1.compareTo(val2) >= 0;
    }


    public static int getGeneratedId(Statement statement) throws SQLException {
        ResultSet keys = statement.getGeneratedKeys();
        keys.next();
        return keys.getInt(1);
    }

}
