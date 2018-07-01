package com.college.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2015/5/4.
 */
public class DateTimeUtil {

    public static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY; //中国周一是一周的第一天
    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMS = "yyyyMMddHHmm";
    public static final String YYYYMMDDHH = "yyyyMMddHH";
    public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String YYYYMMDDS = "yyyyMMdd";
    public static final String YYYYMM = "yyyyMM";
    private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR = 3600;
    private static final long ONE_DAY = 86400;
    private static final long ONE_MONTH = 2592000;
    private static final long ONE_YEAR = 31104000;
    public static Calendar calendar = Calendar.getInstance();

    /**
     * 取得日期所在月包含的天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar c = getCalendar();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取得月第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar c = getCalendar();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
        c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
        return c.getTime();
    }

    /**
     * 取得月最后一天
     *
     * @param date
     * @return
     */
//	public static Date getLastDateOfMonth(Date date) {
//		Calendar c = getCalendar();
//		c.setTime(date);
//		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
//		return c.getTime();
//	}
    public static Date getLastDateOfMonth(Date date) {
        Calendar c = getCalendar();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, c.getActualMaximum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, c.getActualMaximum(Calendar.MINUTE));
        c.set(Calendar.SECOND, c.getActualMaximum(Calendar.SECOND));
        return c.getTime();
    }

    public static Date addMonth(Date date, int nMonth) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, nMonth);
        Date result = cal.getTime();
        return result;
    }

    public static Date addDay(Date date, int nDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, nDay);
        Date result = cal.getTime();
        return result;
    }

    public static Date addHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hours);
        Date result = cal.getTime();
        return result;
    }

    public static String formatDate(Date date, String format) {
        java.text.SimpleDateFormat bartDateFormat = new java.text.SimpleDateFormat(format);
        String dateString = bartDateFormat.format(date);
        return dateString;
    }

    public static boolean isDate(String str) {
        String regex = "^[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}$";
        return str.matches(regex);
    }

    public static boolean isTime(String str) {
        String regex = "^[0-9]{2}:[0-9]{1,2}:[0-9]{1,2}$";
        return str.matches(regex);
    }

    public static boolean isDateTime(String str) {
        String regex = "^[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2} [0-9]{2}:[0-9]{1,2}$";
        return str.matches(regex);
    }

    public static Date parseDateTime(String dateString, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);

        Date date = null;

        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException ignored) {
            ignored.printStackTrace();
        }

        return date;
    }

    public static int getDays(String BeginDate, String EndDate) {

        int days = 0;//2005-02-23
        if (BeginDate.length() < 10 || EndDate.length() < 10) {
            return 0;
        }

        int beginyear = Integer.parseInt(BeginDate.substring(0, 4));
        int beginmonth = Integer.parseInt(BeginDate.substring(5, 7));
        int begindate = Integer.parseInt(BeginDate.substring(8, 10));
        int begindays = 0;
        //System.out.println("beginyear:"+beginyear+" beginmonth:"+beginmonth+" begindate:"+begindate);

        int endyear = Integer.parseInt(EndDate.substring(0, 4));
        int endmonth = Integer.parseInt(EndDate.substring(5, 7));
        int enddate = Integer.parseInt(EndDate.substring(8, 10));
        int enddays = 0;
        //System.out.println("endyear:"+endyear+" endmonth:"+endmonth+" enddate:"+enddate);


        Calendar begincal = new GregorianCalendar();
        begincal.set(beginyear, beginmonth - 1, begindate);
        begindays = begincal.get(Calendar.DAY_OF_YEAR);

        Calendar endcal = new GregorianCalendar();
        endcal.set(endyear, endmonth - 1, enddate);
        enddays = endcal.get(Calendar.DAY_OF_YEAR);
        //System.out.println("enddays-begindays:"+enddays+"-"+begindays);
        days = (endyear - beginyear) * 365 + (enddays - begindays);
        return days;
    }

    public static int getWokingDays(Date beginDate, Date endDate) {
        if (beginDate == null) {
            beginDate = new Date();
        }
        if (endDate == null) {
            endDate = new Date();
        }
        Calendar d1 = Calendar.getInstance();
        d1.setTimeInMillis(beginDate.getTime());
        Calendar d2 = Calendar.getInstance();
        d2.setTimeInMillis(endDate.getTime());

        return getWorkingDay(d1, d2);
    }

    public static int getDaysBetween(java.util.Calendar d1, java.util.Calendar d2) {
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }

        int days = d2.get(java.util.Calendar.DAY_OF_YEAR)
                - d1.get(java.util.Calendar.DAY_OF_YEAR);
        int y2 = d2.get(java.util.Calendar.YEAR);
        if (d1.get(java.util.Calendar.YEAR) != y2) {
            d1 = (java.util.Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
                d1.add(java.util.Calendar.YEAR, 1);
            } while (d1.get(java.util.Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * 计算2个日期之间的相隔天数
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int getWorkingDay(java.util.Calendar d1, java.util.Calendar d2) {
        int result = -1;
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }

        int charge_start_date = 0;//开始日期的日期偏移量
        int charge_end_date = 0;//结束日期的日期偏移量
        // 日期不在同一个日期内
        int stmp;
        int etmp;
        stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
        etmp = 7 - d2.get(Calendar.DAY_OF_WEEK);
        if (stmp != 0 && stmp != 6) {// 开始日期为星期六和星期日时偏移量为0
            charge_start_date = stmp - 1;
        }
        if (etmp != 0 && etmp != 6) {// 结束日期为星期六和星期日时偏移量为0
            charge_end_date = etmp - 1;
        }
        //  }
        result = (getDaysBetween(getNextMonday(d1), getNextMonday(d2)) / 7)
                * 5 + charge_start_date - charge_end_date;
        //System.out.println("charge_start_date>" + charge_start_date);
        //System.out.println("charge_end_date>" + charge_end_date);
        //System.out.println("between day is-->" + betweendays);
        return result;
    }

    public static String getChineseWeek(Calendar date) {
        final String dayNames[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
                "星期六"};

        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);

        // System.out.println(dayNames[dayOfWeek - 1]);
        return dayNames[dayOfWeek - 1];

    }

    /**
     * 获得日期的下一个星期一的日期
     *
     * @param date
     * @return
     */
    public static Calendar getNextMonday(Calendar date) {
        Calendar result = null;
        result = date;
        do {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        } while (result.get(Calendar.DAY_OF_WEEK) != 2);
        return result;
    }

    /**
     * @param d1
     * @param d2
     * @return
     */
    public static int getHolidays(Calendar d1, Calendar d2) {
        return getDaysBetween(d1, d2) - getWorkingDay(d1, d2);

    }

    public static Float getWorkingHours(Date beginDate, Date endDate) {
        int wokingDay = getWokingDays(beginDate, endDate);
        float totalHour = 0;
        float firstDayHour = 0F;
        boolean beginIsWeek = isWeekDay(beginDate);
        boolean endIsWeek = isWeekDay(endDate);
        if (!beginIsWeek) {
            firstDayHour = getParamToEndDayWokingHour(beginDate);
        }

        float lastDateHour = 0F;

        if (!endIsWeek) {
            lastDateHour = getToParamWokingHour(endDate);
        }

        if (wokingDay == 0) {
            if (beginIsWeek && endIsWeek) {

            } else if (!beginIsWeek && endIsWeek) {
                totalHour += firstDayHour;
            } else if (beginIsWeek && !endIsWeek) {
                totalHour += lastDateHour;
            } else if (!beginIsWeek && !endIsWeek) {
                if (firstDayHour > 0) {
                    totalHour += (lastDateHour - getToParamWokingHour(beginDate));
                }
            }
        } else if (wokingDay > 0) {
            totalHour += (wokingDay - 1) * 8;
            totalHour += firstDayHour > 0 ? firstDayHour : 0;
            totalHour += lastDateHour > 0 ? lastDateHour : 0;
        }
        return totalHour < 0 ? 0 : totalHour;
    }

    public static boolean isWeekDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_WEEK);
        //System.out.println(day);
        if (day == 1 || day == 7) {
            return true;
        }
        return false;
    }

    public static float getParamToEndDayWokingHour(Date date) {
        float startHour = getDateHour(date);
        float totalHour = 0;
        if (startHour < 9) {
            totalHour = 8;
        } else if (startHour >= 9 && startHour < 12) {
            totalHour += 18 - startHour - 2;
            totalHour += (60.0F - getDateMinute(date)) / 60F;

        } else if (startHour >= 12 && startHour < 13) {
            totalHour = 18 - 13;
        } else if (startHour >= 13 && startHour < 18) {
            totalHour = 18 - startHour - 1;
            totalHour += (60.0F - getDateMinute(date)) / 60F;
        } else if (startHour >= 18) {
            totalHour = 0;
        }
        return totalHour;
    }

    public static float getToParamWokingHour(Date date) {
        float endHour = getDateHour(date);
        float totalHour = 0;
        if (endHour < 9) {
            totalHour = 0;
        } else if (endHour >= 9 && endHour < 12) {
            totalHour = endHour - 9;
            totalHour += getDateMinute(date) / 60F;
        } else if (endHour >= 12 && endHour < 13) {
            totalHour = 12 - 9;
        } else if (endHour >= 13 && endHour < 18) {
            totalHour = endHour - 9 - 1;
            totalHour += getDateMinute(date) / 60F;
        } else if (endHour >= 18) {
            totalHour = 8;
        }
        return totalHour;
    }

    public static int getDateHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getDateMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 数字转换日期
     *
     * @param lDate long/1000的数据
     * @return Date
     */
    public static Date numberToDate(Long lDate) {
        if (null == lDate) {
            return null;
        }
        Date date = new Date();
        date.setTime(lDate.longValue() * ((long) 1000));
        return date;

    }

    /**
     * 返回某天的开始时间
     *
     * @param date
     * @return
     */
    public static Date begin(Date date) {
        if (null == date) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        return c.getTime();
    }

    /**
     * 返回某天的结束时间
     *
     * @param date
     * @return
     */
    public static Date end(Date date) {
        if (null == date) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MINUTE, 59);
        return c.getTime();
    }

    public static String cha(Date date1, Date date2) {
        StringBuffer str = new StringBuffer();
        if (null != date1 && null != date2 && date2.getTime() > date1.getTime()) {
            long cha = (date2.getTime() - date1.getTime()) / 1000;
            int hor = (int) cha / 3600;
            if (0 != hor) {
                str.append(hor).append("小时");
            }
            int secd = (int) cha % 3600 / 60;
            if (0 != secd) {
                str.append(secd).append("分");
            }
            int miao = (int) cha % 60;
            if (0 != miao) {
                str.append(miao).append("秒");
            }
        }
        return str.toString();
    }

    public static boolean isSameDay(Date d1, Date d2) {
        if (null == d1 || null == d2) {
            return false;
        }
        String d1Str = formatDate(d1, "yyyy-MM-dd");
        return d1Str.equals(formatDate(d2, "yyyy-MM-dd"));
    }

    private static Calendar getCalendar() {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
        return c;
    }

    /**
     * @return yyyy-mm-dd
     * 2012-12-25
     */
    public static String getDate() {
        return getYear() + "-" + getMonth() + "-" + getDay();
    }

    /**
     * @param format
     * @return yyyy年MM月dd HH:mm
     * MM-dd HH:mm 2012-12-25
     */
    public static String getDate(String format) {
        SimpleDateFormat simple = new SimpleDateFormat(format);
        return simple.format(calendar.getTime());
    }

    /**
     * @return yyyy-MM-dd HH:mm
     * 2012-12-29 23:47
     */
    public static String getDateAndMinute() {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simple.format(calendar.getTime());
    }

    /**
     * @return yyyy-MM-dd HH:mm:ss
     * 2012-12-29 23:47:36
     */
    public static String getFullDate() {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simple.format(calendar.getTime());
    }

    /**
     * 距离今天多久
     *
     * @param date
     * @return
     */
    public static String fromToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        long time = date.getTime() / 1000;
        long now =System.currentTimeMillis() / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR) {
            return ago / ONE_MINUTE + "分钟前";
        } else if (ago <= ONE_DAY) {
            return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE)
                    + "分钟前";
        } else if (ago <= ONE_DAY * 2) {
            return "昨天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        } else if (ago <= ONE_DAY * 3) {
            return "前天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        } else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            return month + "个月" + day + "天前"
                    + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        } else {
            long year = ago / ONE_YEAR;
            int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0 so month+1
            return year + "年前" + month + "月" + calendar.get(Calendar.DATE)
                    + "日";
        }

    }

    /**
     * 距离截止日期还有多长时间
     *
     * @param date
     * @return
     */
    public static String fromDeadline(Date date) {
        long deadline = date.getTime() / 1000;
        long now = (System.currentTimeMillis()) / 1000;
        long remain = deadline - now;
        if (remain <= ONE_HOUR) {
            return "只剩下" + remain / ONE_MINUTE + "分钟";
        } else if (remain <= ONE_DAY) {
            return "只剩下" + remain / ONE_HOUR + "小时"
                    + (remain % ONE_HOUR / ONE_MINUTE) + "分钟";
        } else {
            long day = remain / ONE_DAY;
            long hour = remain % ONE_DAY / ONE_HOUR;
            long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return "只剩下" + day + "天" + hour + "小时" + minute + "分钟";
        }

    }

    /**
     * 距离今天的绝对时间
     *
     * @param date
     * @return
     */
    public static String toToday(Date date) {
        long time = date.getTime() / 1000;
        long now = (System.currentTimeMillis()) / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR) {
            return ago / ONE_MINUTE + "分钟";
        } else if (ago <= ONE_DAY) {
            return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE) + "分钟";
        } else if (ago <= ONE_DAY * 2) {
            return "昨天" + (ago - ONE_DAY) / ONE_HOUR + "点" + (ago - ONE_DAY)
                    % ONE_HOUR / ONE_MINUTE + "分";
        } else if (ago <= ONE_DAY * 3) {
            long hour = ago - ONE_DAY * 2;
            return "前天" + hour / ONE_HOUR + "点" + hour % ONE_HOUR / ONE_MINUTE
                    + "分";
        } else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            long hour = ago % ONE_DAY / ONE_HOUR;
            long minute = ago % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return day + "天前" + hour + "点" + minute + "分";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            long hour = ago % ONE_MONTH % ONE_DAY / ONE_HOUR;
            long minute = ago % ONE_MONTH % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return month + "个月" + day + "天" + hour + "点" + minute + "分前";
        } else {
            long year = ago / ONE_YEAR;
            long month = ago % ONE_YEAR / ONE_MONTH;
            long day = ago % ONE_YEAR % ONE_MONTH / ONE_DAY;
            return year + "年前" + month + "月" + day + "天";
        }

    }

    public static String getYear() {
        return calendar.get(Calendar.YEAR) + "";
    }

    public static String getMonth() {
        int month = calendar.get(Calendar.MONTH) + 1;
        return month + "";
    }

    public static String getDay() {
        return calendar.get(Calendar.DATE) + "";
    }

    public static String get24Hour() {
        return calendar.get(Calendar.HOUR_OF_DAY) + "";
    }

    public static String getMinute() {
        return calendar.get(Calendar.MINUTE) + "";
    }

    public static String getSecond() {
        return calendar.get(Calendar.SECOND) + "";
    }




    public static void main(String[] args) throws ParseException {

    }

}
