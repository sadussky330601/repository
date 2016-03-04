package com.android.demo.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Kevin.young  on 2015/9/11.
 * Lately modify by Kevin.young  on 2015/9/11.
 * Copyright @ 1996-2015 Kevin Corporation, All Rights Reserved
 * <p/>
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils {


    public static SimpleDateFormat FORAMAT_01 = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat FORAMAT_02 = new SimpleDateFormat("yyyy年MM月dd");
    public static SimpleDateFormat FORAMAT_03 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat FORAMAT_04 = new SimpleDateFormat("MM-dd HH:mm");
    public static SimpleDateFormat FORAMAT_05 = new SimpleDateFormat("HH:mm");
    public static SimpleDateFormat FORAMAT_06 = new SimpleDateFormat("yyyyMMdd_HHmmss");
    public static SimpleDateFormat FORAMAT_07 = new SimpleDateFormat("yyyyMMdd_HHmmss");
    public static SimpleDateFormat FORAMAT_08 = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    /**
     * format date string to date with "yyyy-MM-dd"
     *
     * @param dateStr
     * @return
     */
    public static Date parse_01(String dateStr) {
        Date date = null;
        try {
            date = FORAMAT_01.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * format date string to date with "yyyy年MM月dd"
     *
     * @param dateStr
     * @return
     */
    public static Date parse_02(String dateStr) {
        Date date = null;
        try {
            date = FORAMAT_02.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * format date string to date with yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr
     * @return
     */
    public static Date parse_03(String dateStr) {
        Date date = null;
        try {
            date = FORAMAT_03.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * format date string to date with "MM-dd HH:mm"
     *
     * @param dateStr
     * @return
     */
    public static Date parse_04(String dateStr) {
        Date date = null;
        try {
            date = FORAMAT_04.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * format date string to date with "yyyy-MM-dd HH:mm"
     *
     * @param dateStr
     * @return
     */
    public static Date parse_08(String dateStr) {
        Date date = null;
        try {
            date = FORAMAT_08.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @return yyyy-MM-dd
     */
    public static String format_1(int year, int monthOfYear, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DATE, dayOfMonth);
        return FORAMAT_01.format(cal.getTime());
    }

    /**
     * @param date
     * @return yyyy-MM-dd
     */
    public static String format_1(Date date) {
        return date == null ? "" : FORAMAT_01.format(date);
    }

    /**
     * @param date
     * @return yyyy年MM月dd
     */
    public static String format_2(Date date) {
        return date == null ? "" : FORAMAT_02.format(date);
    }

    /**
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String format_3(Date date) {
        return date == null ? "" : FORAMAT_03.format(date);
    }

    public static String format_4(Date date) {
        return date == null ? "" : FORAMAT_04.format(date);
    }

    public static String format_8(Date date) {
        return date == null ? "" : FORAMAT_08.format(date);
    }

    public static String[] countdownTime(Date now, Date date) {
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // java.util.Date now = df.parse("2004-03-26 13:31:40");
        // java.util.Date date=df.parse("2004-01-02 11:30:24");

        // long hour = l / (60 * 60 * 1000);
        // long min = ((l / (60 * 1000)) - hour * 60);
        // long s = (l / 1000 - hour * 60 * 60 - min * 60);
        // long mills = (l - hour * 60 * 60 * 1000 - min * 60 * 1000 - s *
        // 1000);

        // Date now = new Date();
        long l = date.getTime() - now.getTime();
        if (l <= 0)
            return null;
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long mills = (l - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000) / 100;

        String dayStr = day + "";
        dayStr = dayStr.length() >= 2 ? dayStr : "0" + dayStr;
        String hourStr = hour + "";
        hourStr = hourStr.length() >= 2 ? hourStr : "0" + hourStr;
        String minStr = min + "";
        minStr = minStr.length() >= 2 ? minStr : "0" + minStr;
        String sStr = s + "";
        sStr = sStr.length() >= 2 ? sStr : "0" + sStr;
        String millstr = mills + "";
        // millstr = (millstr).length() > 3 ? millstr.substring(0, 3) : millstr;
        return new String[]{dayStr + ":" + hourStr + ":" + minStr, ":" + sStr + "." + millstr};
    }

    public static String[] countdownTime(long now, long date) {
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // java.util.Date now = df.parse("2004-03-26 13:31:40");
        // java.util.Date date=df.parse("2004-01-02 11:30:24");

        // long hour = l / (60 * 60 * 1000);
        // long min = ((l / (60 * 1000)) - hour * 60);
        // long s = (l / 1000 - hour * 60 * 60 - min * 60);
        // long mills = (l - hour * 60 * 60 * 1000 - min * 60 * 1000 - s *
        // 1000);

        // Date now = new Date();
        long l = date - now;
        if (l <= 0)
            return null;
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long mills = (l - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000) / 100;

        String dayStr = day + "";
        // dayStr = dayStr.length() >= 2 ? dayStr : "0" + dayStr;
        String hourStr = hour + "";
        hourStr = hourStr.length() >= 2 ? hourStr : "0" + hourStr;
        String minStr = min + "";
        minStr = minStr.length() >= 2 ? minStr : "0" + minStr;
        String sStr = s + "";
        sStr = sStr.length() >= 2 ? sStr : "0" + sStr;
        String millstr = mills + "";
        // millstr = (millstr).length() > 3 ? millstr.substring(0, 3) : millstr;
        return new String[]{dayStr + "天", hourStr + ":" + minStr + ":" + sStr};
    }

    public static Date getPreDay(Date now, int pre) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_MONTH, pre);
        return calendar.getTime();
    }

    public static Date getPreWeek(Date now, int pre) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.WEEK_OF_MONTH, pre);
        return calendar.getTime();
    }

    public static Date getPreMonth(Date now, int pre) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MONTH, pre);
        return calendar.getTime();
    }

    public static Date getToday(Date now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date[] getYestoday(Date now) {
        Date[] result = new Date[2];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.clear(Calendar.HOUR);
        calendar.clear(Calendar.HOUR_OF_DAY);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        // calendar.set(Calendar.HOUR_OF_DAY, 0);
        // calendar.set(Calendar.MINUTE, 0);
        // calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        result[0] = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        result[1] = calendar.getTime();
        return result;
    }

}
