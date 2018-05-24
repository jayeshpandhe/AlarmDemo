package com.example.alarmdemo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {
    private static final String DATE_FORMAT = "dd/MM/yyyy hh:mm:ss";

    public static String getDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
