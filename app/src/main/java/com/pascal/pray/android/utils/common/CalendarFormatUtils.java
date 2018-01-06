package com.pascal.pray.android.utils.common;

import android.text.TextUtils;

import com.pascal.pray.android.model.CalendarModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarFormatUtils {
    public static DateFormat FORMAT_UPLOAD_STEP = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    /**
     * @param monthOfYear 第一個月是0
     * @return 1990-1-1
     */
    public static String getHyphenFormat(int year,int monthOfYear,int dayOfMonth){
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear + 1) + "-"
                + String.valueOf(dayOfMonth);
    }

    /**
     * @param monthOfYear 第一個月是0
     * @return 30/01/1990
     */
    public static String getSlashFormat(int year,int monthOfYear,int dayOfMonth){
        return String.valueOf(dayOfMonth) + "/"
                + String.valueOf(monthOfYear + 1) + "/"
                + String.valueOf(year);
    }

    /**
     * 請參照上一個method還原
     */
    public static CalendarModel getSlashReductionFormat(String string){
        if (string == null || string.length() == 0)return null;
        String[] stringArr = string.split("/");
        return new CalendarModel(
                Integer.parseInt(stringArr[2]),
                Integer.parseInt(stringArr[1]) -1 ,
                Integer.parseInt(stringArr[0]));
    }

    public static Calendar getEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar;
    }

    public static Calendar getStartTime() {
        return getStartTime(getOffset()) ;
    }

    public static Calendar getStartTime(int offset) {
        Calendar calendar = Calendar.getInstance(); // today
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // To be monday of current work
        // If today is wednesday, offset must be 2
        // 3-2 = 1
        calendar.add(Calendar.DAY_OF_MONTH, -offset);
        return calendar;
    }

    public static int getOffset() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.DAY_OF_WEEK, cal1.getActualMinimum(Calendar.DAY_OF_WEEK));
        Date current = new Date();
        cal1.setTime(current);
        return cal1.get(Calendar.DAY_OF_WEEK);
    }

    public static boolean isSameWeek(long currentTime, long comparedTime) {
        if (0 == currentTime || 0 == comparedTime) return false;
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTimeInMillis(currentTime);
        int year1=c.get(Calendar.YEAR);
        int week1=c.get(Calendar.WEEK_OF_YEAR);

        c.setTimeInMillis(comparedTime);
        int year2=c.get(Calendar.YEAR);
        int week2=c.get(Calendar.WEEK_OF_YEAR);

        return year1 == year2 && week1 == week2;
    }

    public static String getToday(){
        return CalendarFormatUtils.FORMAT_UPLOAD_STEP.format(new Date());
    }
}

