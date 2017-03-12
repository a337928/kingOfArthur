package com.arthur.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * Title: 日期操作
 * </p>
 * <p>
 * Description: 获取当前时间，Date时间与String时间互转(包含各种时间格式)，
 * 日期加减运算，时间比较运算，日期与星期几的换算，月份相关运算
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: 浙江鸿程
 * </p>
 * 詹家平
 *
 * @version 1.0
 *
 */
public class DateUtil {

    // 日期的分隔符位-
    private static final String DATE_SEPARATOR = "-";

    private static final int MONTH_LENGTH[] = { 31, 28, 31, 30, 31, 30, 31, 31,
            30, 31, 30, 31 }; // 0-based

    private static final int LEAP_MONTH_LENGTH[] = { 31, 29, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31 }; // 0-based

    /**
     * 获得当前时间
     *
     * @return java.util.Date的对象
     * @see java.util.Date
     */
    public static java.util.Date getCurDate() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        return cal.getTime();
    }

    /**
     * 获得指定格式的String类型的日期
     *
     * @param date
     *            时间对象
     * @param type
     *            格式类型:0:yyyy-MM-dd,1:yyyy/MM/dd,2:yyyyMMdd,3:MM/dd/yy,4:yyyy-MM
     *            -dd HH:mm:ss
     * @return 获得String类型的日期
     * @see java.util.Date
     */
    public static String formatDate(java.util.Date date, int type) {
        String result = null;
        String format = "yyyy-MM-dd";
        if (type == 0) {
            format = "yyyy-MM-dd";
        }
        if (type == 1) {
            format = "yyyy/MM/dd";
        } else if (type == 2) {
            format = "yyyyMMdd";
        } else if (type == 21) {
            format = "yyyyMM";
        } else if (type == 3) {
            format = "MM/dd/yy";
        } else if (type == 4) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        if (date != null) {
            result = formatter.format(date);
        } else {
            result = "";
        }
        return result;
    }

    /**
     * 将字符串转化成时间对象
     *
     * @param strDate
     *            字符串
     * @return java.util.Date对象
     * @see java.util.Date
     */
    public static java.util.Date StringToDate(String strDate) {
        Date result = new Date();
        String format = "yyyy-MM-dd";

        SimpleDateFormat formatter = new SimpleDateFormat(format);

        if (strDate != null && !strDate.equals("")) {
            try {
                result = formatter.parse(strDate);
            } catch (Exception ex) {
                result = null;
            }
        }

        return result;
    }

    /**
     * 获得指定格式的String类型的日期
     *
     * @param strDate
     * @param type
     *            格式类型:0:yyyy-MM-dd,1:yyyy/MM/dd,2:yyyyMMdd,3:MM/dd/yy,4:yyyy-MM
     *            -dd HH:mm:ss
     * @return
     * @see java.util.Date
     */
    public static java.util.Date StringToDate(String strDate, int type) {
        java.util.Date result = null;
        String format = "yyyy-MM-dd";
        if (type == 0) {
            format = "yyyy-MM-dd";
        }

        if (type == 1) {
            format = "yyyy/MM/dd";
        } else if (type == 2) {
            format = "yyyyMMdd";
        } else if (type == 21) {
            format = "yyyyMM";
        } else if (type == 3) {
            format = "MM/dd/yy";
        } else if (type == 4) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(format);

        if (strDate != null && !strDate.equals("")) {
            try {
                result = formatter.parse(strDate);
            } catch (Exception ex) {
                result = null;
            }
        }

        return result;
    }

    /**
     * 将当前时间进行格式化
     *
     * @param type
     *            格式化类型:yyyy-MM-dd,yyyy/MM/dd,yyyyMMdd,MM/dd/yy,yyyy-MM-dd
     *            HH:mm:ss
     * @return 当前时间
     */
    public static String getCurrentDateString(String type) {
        SimpleDateFormat formatter = new SimpleDateFormat(type);
        String str = "";
        try {
            str = formatter.format(new Date());
        } catch (Exception e) {

        }
        return str;
    }

    /**
     * 得到当前的年月日
     *
     * @return 年月日的字符串
     */
    public static String getDate() {
        Date d_Temp = new Date();
        Calendar calendar = null;
        calendar = Calendar.getInstance();
        calendar.setTime(d_Temp);
        int i_Year = calendar.get(Calendar.YEAR);
        int i_Month = calendar.get(Calendar.MONTH) + 1;
        int i_Day = calendar.get(Calendar.DATE);
        return i_Year + "年" + i_Month + "月" + i_Day + "日";
    }

    /**
     * 得到本月第一天,不足的前面添0
     *
     * @return
     */
    public static String getMonthOneDate() {
        Date d_Temp = new Date();
        Calendar calendar = null;
        calendar = Calendar.getInstance();
        calendar.setTime(d_Temp);
        int i_Year = calendar.get(Calendar.YEAR);
        int i_Month = calendar.get(Calendar.MONTH) + 1;
        int i_Day = 1;
        if (i_Month < 10) {
            return i_Year + "-" + "0" + i_Month + "-" + "0" + i_Day;
        } else {
            return i_Year + "-" + i_Month + "-" + "0" + i_Day;
        }
    }

    /**
     * 取得系统日期
     *
     * @return
     */
    public static String getSystemDate() {
        Date d_Temp = new Date();
        Calendar calendar = null;
        calendar = Calendar.getInstance();
        calendar.setTime(d_Temp);
        int i_Year = calendar.get(Calendar.YEAR);
        int i_Month = calendar.get(Calendar.MONTH) + 1;
        int i_Day = calendar.get(Calendar.DATE);
        String s_Result = Integer.toString(i_Year);
        String s_Temp = Integer.toString(i_Month);
        if (s_Temp.length() == 1) {
            s_Result = s_Result + "0" + s_Temp;
        } else {
            s_Result += s_Temp;
        }
        s_Temp = Integer.toString(i_Day);
        if (s_Temp.length() == 1) {
            s_Result = s_Result + "0" + s_Temp;
        } else {
            s_Result += s_Temp;
        }
        return s_Result;
    }

    /**
     * 取得系统时间
     *
     * @return
     */
    public static String getSystemTime() {
        String s_Result = "";
        String s_Temp = "";
        Date d_Temp = new Date();
        Calendar calendar = null;
        calendar = Calendar.getInstance();
        calendar.setTime(d_Temp);
        int i_Hour = calendar.get(Calendar.HOUR_OF_DAY);
        int i_Minute = calendar.get(Calendar.MINUTE);
        int i_Second = calendar.get(Calendar.SECOND);
        s_Temp = Integer.toString(i_Hour);
        if (s_Temp.length() == 1) {
            s_Result = s_Result + "0" + s_Temp;
        } else {
            s_Result += s_Temp;
        }
        s_Temp = Integer.toString(i_Minute);
        if (s_Temp.length() == 1) {
            s_Result = s_Result + "0" + s_Temp;
        } else {
            s_Result += s_Temp;
        }
        s_Temp = Integer.toString(i_Second);
        if (s_Temp.length() == 1) {
            s_Result = s_Result + "0" + s_Temp;
        } else {
            s_Result += s_Temp;
        }
        return s_Result;
    }

    /**
     * 取得系统时间
     *
     * @return
     */
    public static String getTime() {
        String s_Result = "";
        String s_Temp = "";
        Date d_Temp = new Date();
        Calendar calendar = null;
        calendar = Calendar.getInstance();
        calendar.setTime(d_Temp);
        int i_Hour = calendar.get(Calendar.HOUR_OF_DAY);
        int i_Minute = calendar.get(Calendar.MINUTE);
        int i_Second = calendar.get(Calendar.SECOND);
        s_Temp = Integer.toString(i_Hour);
        if (s_Temp.length() == 1) {
            s_Result = s_Result + "0" + s_Temp;
        } else {
            s_Result += s_Temp;
        }
        s_Result = s_Result + ":";
        s_Temp = Integer.toString(i_Minute);
        if (s_Temp.length() == 1) {
            s_Result = s_Result + "0" + s_Temp;
        } else {
            s_Result += s_Temp;
        }
        s_Result = s_Result + ":";
        s_Temp = Integer.toString(i_Second);
        if (s_Temp.length() == 1) {
            s_Result = s_Result + "0" + s_Temp;
        } else {
            s_Result += s_Temp;
        }
        return s_Result;
    }

    /**
     * 取得系统时间
     *
     * @return
     */
    public static String getRealTime() {
        Date d_Temp = new Date();
        Calendar calendar = null;
        calendar = Calendar.getInstance();
        calendar.setTime(d_Temp);
        int i_Year = calendar.get(Calendar.YEAR);
        int i_Month = calendar.get(Calendar.MONTH) + 1;
        int i_Day = calendar.get(Calendar.DATE);
        String s_Result = Integer.toString(i_Year);
        s_Result = s_Result + "-";
        String s_Temp = Integer.toString(i_Month);
        if (s_Temp.length() == 1) {
            s_Result = s_Result + "0" + s_Temp;
        } else {
            s_Result += s_Temp;
        }
        s_Result = s_Result + "-";
        s_Temp = Integer.toString(i_Day);
        if (s_Temp.length() == 1) {
            s_Result = s_Result + "0" + s_Temp;
        } else {
            s_Result += s_Temp;
        }

        s_Result = s_Result + " ";

        int i_Hour = calendar.get(Calendar.HOUR_OF_DAY);
        int i_Minute = calendar.get(Calendar.MINUTE);
        int i_Second = calendar.get(Calendar.SECOND);
        s_Temp = Integer.toString(i_Hour);
        if (s_Temp.length() == 1) {
            s_Result = s_Result + "0" + s_Temp;
        } else {
            s_Result += s_Temp;
        }
        s_Result = s_Result + ":";
        s_Temp = Integer.toString(i_Minute);
        if (s_Temp.length() == 1) {
            s_Result = s_Result + "0" + s_Temp;
        } else {
            s_Result += s_Temp;
        }
        s_Result = s_Result + ":";
        s_Temp = Integer.toString(i_Second);
        if (s_Temp.length() == 1) {
            s_Result = s_Result + "0" + s_Temp;
        } else {
            s_Result += s_Temp;
        }
        return s_Result;
    }

    /**
     * 日期的增加
     *
     * @param startDate
     *            日期
     * @param days
     *            天数
     * @return
     * @see java.util.Date
     */
    public static Date addDays(Date startDate, int days) {
        GregorianCalendar gCal = new GregorianCalendar();
        gCal.setTime(startDate);
        gCal.add(Calendar.DATE, days);
        return gCal.getTime();
    }

    /**
     * 在现有的日期上增加年份
     *
     * @param startDate
     *            日期
     * @param datetype
     *            类型 1:年数，2
     * @param ls
     *            数量
     * @return
     * @see java.util.Date add by yelj 2010-03-30
     */
    public static Date addDays(Date startDate, int datetype, int ls) {
        GregorianCalendar gCal = new GregorianCalendar();
        gCal.setTime(startDate);
        gCal.add(datetype, ls);
        return gCal.getTime();
    }

    /**
     * 将日期增加天数后，再进行格式化
     *
     * @param startDate
     *            字符串日期
     * @param dateFormat
     *            格式化类型:yyyy-MM-dd,yyyy/MM/dd,yyyyMMdd,MM/dd/yy,yyyy-MM-dd
     *            HH:mm:ss
     * @param days
     *            天数
     * @return 时间
     * @see java.util.Date
     */
    public static Date addDays(String startDate, String dateFormat, int days) {
        return addDays(getDate(startDate, dateFormat), days);
    }

    /**
     * 判断时间d是否在时间d1与时间d2之间
     *
     * @param d
     *            时间
     * @param d1
     *            开始时间
     * @param d2
     *            结束时间
     * @return
     */
    public static boolean isDateBetween(Date d, Date d1, Date d2) {
        return ((d1.after(d) || d1.equals(d)) && (d.before(d2) || d.equals(d2)));
    }

    /**
     * 将时间按yyyyMMddHHmmss形式进行格式化
     *
     * @param timestamp
     *            时间
     * @return
     */
    public static String formatTimestamp(java.util.Date timestamp) {
        String result = null;
        String format = "yyyyMMddHHmmss";
        SimpleDateFormat formatter = new SimpleDateFormat(format);

        if (timestamp != null) {
            result = formatter.format(timestamp);
        } else {
            result = "";
        }

        return result;
    }

    /**
     * 将yyyy-mm-dd类型的日期转换成一个yyyymmdd类型的整数
     *
     * @param date
     *            时间
     * @return
     *
     */
    public static int getDateToInt(String date) {
        int intDate = 0;
        int n = 0;
        Integer integer;
        String tmp = date;
        String tmpdate;
        // 得到年份
        tmpdate = tmp.substring(0, tmp.indexOf("-"));
        integer = new Integer(tmpdate);
        n = integer.intValue();
        tmp = date.substring(tmp.indexOf("-") + 1, tmp.length());
        intDate = n;
        // 得到月份
        tmpdate = tmp.substring(0, tmp.indexOf("-"));
        integer = new Integer(tmpdate);
        n = integer.intValue();
        tmp = date.substring(tmp.indexOf("-") + 1, tmp.length());
        intDate = intDate * 100 + n;

        // 得到日期
        tmpdate = tmp.substring(0, tmp.length());
        integer = new Integer(tmpdate);
        n = integer.intValue();
        intDate = intDate * 100 + n;
        return intDate;
    }

    /**
     * 将yyyy-mm-dd格式转换为yyyymmdd
     *
     * @param date
     * @return
     *
     */
    public static String formateDate(String date) {
        String rdate = "";
        rdate = date.substring(0, 4);
        rdate += date.substring(5, 7);
        rdate += date.substring(8, 10);
        return rdate;
    }

    /**
     * 根据format类型取得当前日期格式
     *
     * @param sdf
     * @return
     *
     *
     */
    public static String getCurrentDateString(SimpleDateFormat sdf) {
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    /**
     * 将字符串转化成指定格式的时间
     *
     * @param sDate
     *            字符串
     * @param dateFormat
     *            时间格式
     * @return
     *
     */
    public static Date getDate(String sDate, String dateFormat) {
        SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
        ParsePosition pos = new ParsePosition(0);

        return fmt.parse(sDate, pos);
    }

    /**
     * String格式的的日期进行比较
     *
     * @param begin
     *            字符串时间
     * @param end
     *            字符串时间
     * @return
     *
     */
    public static int compareDate2(String begin, String end) {
        String t1 = begin;
        String t2 = end;
        char y = 'x';
        char z = 'y';
        int m = 0;
        char sfsre[] = new char[20];
        char sfsre2[] = new char[20];
        char xx[] = new char[20];
        char xx2[] = new char[20];
        sfsre = t1.toCharArray();
        for (int x = 0; x < sfsre.length; x++) {
            y = sfsre[x];
            if ('9' >= y && y >= '0') {
                xx[m] = y;
                m++;
            }
        }
        sfsre2 = t2.toCharArray();
        m = 0;
        for (int x = 0; x < sfsre2.length; x++) {
            y = sfsre2[x];
            if ('9' >= y && y >= '0') {
                xx2[m] = y;
                m++;
            }
        }
        for (int x = 0; x < 10; x++) {
            if (xx[x] > xx2[x])
                return 1;
            if (xx[x] < xx2[x])
                return -1;
        }
        return 0;
    }

    /**
     * 分开年份和月份
     *
     * @param month
     *            年月
     * @return 年份和月份的数组
     *
     */
    public static int[] getYearAndMonth(String month) {
        int result[] = new int[2];
        String s[] = month.split(DATE_SEPARATOR);
        result[0] = Integer.parseInt(s[0]);
        result[1] = Integer.parseInt(s[1]);
        return result;
    }

    /**
     * 获取月份的天数
     *
     * @param month
     *            月份
     * @return 天数
     *
     */
    public static int getNumberOfMonth(String month) {
        int result = 0;
        int yearAndMonth[] = getYearAndMonth(month);
        int yyyy = yearAndMonth[0];
        int mm = yearAndMonth[1];
        GregorianCalendar calendar = new GregorianCalendar();
        if (calendar.isLeapYear(yyyy)) {
            result = MONTH_LENGTH[mm - 1];
        } else {
            result = LEAP_MONTH_LENGTH[mm - 1];
        }
        return result;
    }

    /**
     * 获取传入月份的上一个月份
     *
     * @param month
     *            月份
     * @return
     *
     */
    public static String getPreviewMonth(String month) {
        String result = "";
        int[] yearAndMonth = getYearAndMonth(month);
        int yyyy = yearAndMonth[0];
        int mm = yearAndMonth[1];
        if (mm == 1) {
            mm = 12;
            yyyy = yyyy - 1;
        } else {
            mm = mm - 1;
        }
        if (mm > 10) {
            result = yyyy + DATE_SEPARATOR + "0" + mm;
        } else {
            result = yyyy + DATE_SEPARATOR + +mm;
        }
        return result;
    }

    /**
     * 获取传入月份的下一个月份
     *
     * @param month
     *            月份
     * @return 月份
     *
     */
    public static String getNextMonth(String month) {
        String result = "";
        int[] yearAndMonth = getYearAndMonth(month);
        int yyyy = yearAndMonth[0];
        int mm = yearAndMonth[1];
        if (mm == 12) {
            mm = 1;
            yyyy = yyyy + 1;
        } else {
            mm = mm + 1;
        }
        if (mm > 10) {
            result = yyyy + DATE_SEPARATOR + "0" + mm;
        } else {
            result = yyyy + DATE_SEPARATOR + +mm;
        }
        return result;
    }

    /**
     * 根据某月某天,计算该时间的星期几
     *
     * @param month
     *            月份
     * @param day
     *            天数
     * @return 星期几
     *
     */
    public static int getDayOfWeek(String month, int day) {
        int result = 0;
        int yearAndMonth[] = getYearAndMonth(month);
        int yyyy = yearAndMonth[0];
        int mm = yearAndMonth[1];
        GregorianCalendar calendar = new GregorianCalendar();
        // 这里的set中的月份与时间的月份相差1
        calendar.set(yyyy, mm - 1, day);
        result = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (result == 0) {
            result = 7;
        }
        return result;
    }

    /**
     * 获取某月的所有日期按星期排列的二维数组
     *
     * @param month
     * @return
     *
     */
    public static int[][] getMonthWeekDay(String month) {
        int number = getNumberOfMonth(month);
        int firstDayInWeek = getDayOfWeek(month, 1);
        int laseDayInWeek = getDayOfWeek(month, number);
        int cols = 7;
        int count = (firstDayInWeek - 1) + (cols - laseDayInWeek) + number;
        int rows = count / cols;
        int[][] days = new int[rows][cols];
        int index = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((i == 0 && j >= firstDayInWeek - 1)
                        || (0 < i && i < rows - 1)
                        || (i == rows - 1 && j <= laseDayInWeek - 1)) {

                    days[i][j] = index++;
                }
            }
        }
        return days;
    }

    /**
     * 获得当前时间
     *
     * @param cal
     *            日历
     * @return
     *
     */
    public static String calendarToString(Calendar cal) {

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        String str = String.valueOf(year)
                + "-"
                + (month < 10 ? "0" + String.valueOf(month) : String
                .valueOf(month))
                + "-"
                + (day < 10 ? "0" + String.valueOf(day) : String.valueOf(day))
                + " "
                + (hour < 10 ? "0" + String.valueOf(hour) : String
                .valueOf(hour))
                + ":"
                + (minute < 10 ? "0" + String.valueOf(minute) : String
                .valueOf(minute))
                + ":"
                + (second < 10 ? "0" + String.valueOf(second) : String
                .valueOf(second));

        return str;

    }

    /**
     * 将月份和天数组合成日期
     *
     * @param month
     *            月份
     * @param day
     *            天数
     *
     */
    public static String getMonthDay(String month, int day) {
        String reuslt = "";
        if (day > 0) {
            if (day < 10) {
                reuslt = month + "-" + "0" + day;
            } else {
                reuslt = month + "-" + day;
            }
        }
        return reuslt;
    }

    /**
     *
     * 得到某一天在一年当中是第几天(区分闰年非闰年)
     *
     * @param time
     * @return int 第几天
     * @see
     */
    public static int getDayOfYear(String time) {

        Calendar c = Calendar.getInstance();
        //c.setTime(com.zjhcsoft.zzsoft.util.DateUtil.StringToDate(time));
        return c.get(Calendar.DAY_OF_YEAR);

    }


    /**
     * 得到某一天在一年当中是第几天(一年固定为366天,既2月份固定为29天)
     *
     * @param time
     * @return int 第几天
     * @see
     */
    public static int getDayOfYear2(String time) {
        int count = 0;
        String[] str = time.split("-");
        int year = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
        int day = Integer.parseInt(str[2]);
        switch (month) {
            case 12:
                count += 30;
            case 11:
                count += 31;
            case 10:
                count += 30;
            case 9:
                count += 31;
            case 8:
                count += 31;
            case 7:
                count += 30;
            case 6:
                count += 31;
            case 5:
                count += 30;
            case 4:
                count += 31;
            case 3:
                count += 29;
            case 2:
                count += 31;
            case 1:
                count += 0;
        }
        count += day;

        return count;
    }

    /**
     * 得到上t个月当天的时间
     *
     * @param t
     * @return String
     * @see
     */
    public static String getLastMonthDate(int t) {
        String lastMonthDate = "";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, t);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        lastMonthDate = formatter.format(cal.getTime());
        return lastMonthDate;
    }

    /**
     * 得到与currDate相隔diffDay天的时间
     *
     * @param currDate
     * @param diffDay
     * @return String
     * @throws Exception
     * @see
     */
    public static String getLastday(String currDate, int diffDay)
            throws Exception {
        String resultDate = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dd = formatter.parse(currDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dd);
        cal.add(Calendar.DATE, diffDay);
        resultDate = formatter.format(cal.getTime());
        return resultDate;
    }

    public static void main(String[] args) {
        Date d = new Date();
        String s1 = formatDate(d, 0);
        System.out.println(s1);
        d = addDays(d, Calendar.MONTH, 12);
        String s2 = formatDate(d, 0);
        System.out.println(s2);
    }
}
