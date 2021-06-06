package com.example.touragency.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.sql.Date;

public class Tools {

    public static Calendar getCalendarFromDate(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static boolean decimalCompare(BigDecimal val1, BigDecimal val2){
        return val1.compareTo(val2) >= 0;
    }

}
