package com.myd.sensorapps.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by MYD on 11/21/17.
 *
 */

public class DateTimeUtil {

    public static String formatDate(long epochTime) {
        Date date = new Date(epochTime);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.GERMANY);
        return format.format(date);
    }
}
