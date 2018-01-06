package com.pascal.pray.android.utils.common;


import java.util.Calendar;
import java.util.Date;

public class AgeUtils {

    /**
     * @param month 一月=0
     * @return 是否大於18歲
     */
    public static boolean isOver18(int year , int month , int dayOfMonth){
        return isOverSomeAge(18,year,month,dayOfMonth);
    }

    public static boolean isOver125(int year , int month , int dayOfMonth){
       return isOverSomeAge(125,year,month,dayOfMonth);
    }

    /**
     * @param compareAge 是否超過幾歲
     * @param year , month , dayOfMonth = now age
     * @param month 一月 = 0
     * millis since 1970
     */
    private static boolean isOverSomeAge(int compareAge , int year , int month , int dayOfMonth){
        long millisForCompare = getMillisecByAge(compareAge);

        Calendar calendarBirth = Calendar.getInstance();
        calendarBirth.set(year, month, dayOfMonth, 0, 0, 0);
        long millisForBirth = calendarBirth.getTimeInMillis();

        return millisForBirth < millisForCompare;
    }

    public static long getMillisecByAge(int age){
        Calendar calendarCompareYear = Calendar.getInstance();
        calendarCompareYear.setTime(new Date());
        calendarCompareYear.add(Calendar.YEAR, -age);

        return calendarCompareYear.getTimeInMillis();
    }
}
