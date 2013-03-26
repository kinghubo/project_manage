package com.taogongbao.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateCoverdUtil {
    public final static String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public final static String FORMAT_YYYYMMDD = "yyyyMMdd";
    public final static String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public final static String SQL_FORMAT_YYYY_MM_DD_HH_MM_SS = "%Y-%m-%d %T"; // '%Y-%m-%d
    public final static String SQL_FORMAT_YYYY_MM_DD = "%Y-%m-%d";

    public static SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
    public static SimpleDateFormat sdfMin = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
    public static SimpleDateFormat dt = new SimpleDateFormat(FORMAT_YYYYMMDD);

    /**
     * 方法概要:将日期转换为String串
     *
     * @param dt
     * @return
     */
    public static String toString(Date dt) {
        return toString(dt, FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 方法概要:将日期转换为String串
     *
     * @param dt
     * @param sFmt
     * @return
     */
    public static String toString(Date dt, String sFmt) {
        if (dt == null)
            return "";
        else {
            SimpleDateFormat formatter = new SimpleDateFormat(sFmt);
            return formatter.format(dt).toString();
        }
    }

    /**
     * 方法概要:将String转Date
     * 
     * @param date
     * @param format
     * @return
     */
    public static Date toDate(String date) {
        if (CommonUtil.isEmpty(date)) {
            return new Date();
        }
        DateFormat df = getDateFormat();
        Date da = null;
        try {
            da = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return da;
    }

    /**
     * 方法概要:将String转Date
     * 
     * @param date
     * @param format
     * @return
     */
    public static Date toDate(String date, String format) {
        DateFormat df = getDateFormat(format);
        Date da = null;
        try {
            da = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return da;
    }

    /**
     * 函数名称: toDifferYear 函数功能描述: 判断相差多少年
     * 
     * @param startyear
     * @param lastyear
     * @return //TODO <描述该参数的含义>
     */
    public static int toDifferYear(Date startyear, Date lastyear) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startyear);
        int startyears = cal.get(Calendar.YEAR);

        Calendar endca = Calendar.getInstance();
        endca.setTime(lastyear);
        int lastyears = endca.get(Calendar.YEAR);

        int result = lastyears - startyears;
        return result;
    }

    /**
     * 函数名称: toDifferYear 函数功能描述: 判断相差多少天
     * 
     * @param startyear
     * @param lastyear
     * @return //TODO <描述该参数的含义>
     */
    public static int toDifferDay(Date startyear, Date lastyear) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startyear);

        Calendar endca = Calendar.getInstance();
        endca.setTime(lastyear);

        long daterange = endca.getTimeInMillis() - cal.getTimeInMillis();
        long time = 1000 * 3600 * 24; // A day in milliseconds
        int resutl = (int) (daterange / time);
        // int result = lastyears - startyears;
        return resutl;
    }

    /**
     * 函数名称: toDifferNow 函数功能描述: 同现在比较的天数
     * 
     * @param startyear
     * @return //TODO <描述该参数的含义>
     */
    public static int toDifferNowYear(Date startyear) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startyear);
        int startyears = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_YEAR);

        Calendar endca = Calendar.getInstance();
        endca.setTime(new Date());
        int lastyears = endca.get(Calendar.YEAR);
        int lastday = endca.get(Calendar.DAY_OF_YEAR);
        int result = lastyears - startyears;
        if (lastday < day) {
            result = result - 1;
        }

        return result;
    }

    /**
     * 函数名称: toDifferNow 函数功能描述: 同现在比较的天数
     * 
     * @param startyear
     * @return //TODO <描述该参数的含义>
     */
    public static int toDifferNowDay(Date startyear) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startyear);
        // int startyears = cal.get(Calendar.YEAR);

        Calendar endca = Calendar.getInstance();
        endca.setTime(new Date());
        // int lastyears = endca.get(Calendar.YEAR);

        long daterange = endca.getTimeInMillis() - cal.getTimeInMillis();
        long time = 1000 * 3600 * 24; // A day in milliseconds
        int resutl = (int) (daterange / time);
        // int result = lastyears - startyears;
        return resutl;
    }

    /**
     * 方法概要:获取格式信息
     * 
     * @param format
     * @return
     */
    public static DateFormat getDateFormat(String format) {
        DateFormat df = null;
        if (format == null || format.trim().equals("")) {
            df = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
        } else {
            df = new SimpleDateFormat(format);
        }
        return df;
    }

    public static DateFormat getDateFormat() {
        return getDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 方法概要:增加年
     * 
     * @param date
     * @param years
     * @return
     */
    public static String addYearsToString(Date date, int years, String format) {
        date = addYears(date, years);
        String strdate = toString(date, format);
        return strdate;
    }

    /**
     * 方法概要:增加年
     * 
     * @param date
     * @param years
     * @return
     */
    public static Date addYears(Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + years);// 让日期加1
        calendar.add(Calendar.YEAR, years);
        date = calendar.getTime();
        return date;
    }

    /**
     * 方法概要:增加月
     * 
     * @param date
     * @param years
     * @return
     */
    public static Date addMonths(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        date = calendar.getTime();
        return date;
    }

    /**
     * 方法概要:增加日期
     * 
     * @param date
     * @param years
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);// 让日期加1
        date = calendar.getTime();
        return date;
    }

    /**
     * 方法概要:增加小时
     * 
     * @param date
     * @param years
     * @return
     */
    public static Date addHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hours);// 让日期加1
        date = calendar.getTime();
        return date;
    }

    /**
     * 方法概要:增加分钟
     * 
     * @param date
     * @param years
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);// 让日期加1
        date = calendar.getTime();
        return date;
    }

    /**
     * 年龄转出生日期
     * 
     * @param age
     * @return
     * @auther <a href="mailto:lihanpei@feinno.com">Hanpei Li</a> 2010-11-30
     *         上午10:23:01
     */
    public static Date getInt2Date(int age) {
        Calendar cal = Calendar.getInstance();
        int currentDate = cal.get(Calendar.YEAR);
        cal.set(Calendar.YEAR, currentDate - age);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        return cal.getTime();
    }

    /**
     * 通过生日计算年龄
     * 
     * @param birthday
     *            生日
     * @return
     * @auther <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a> Apr 23, 2011
     *         3:03:10 PM
     */
    public static int getAge(Date birthday) {
        if (birthday == null) {
            return 0;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(birthday);
        Calendar c2 = Calendar.getInstance();
        int y = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        //		int size = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        //		if (size < 0) {
        //			y--;
        //		} else if (size == 0) {
        //			int dsize = c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
        //			if (dsize < 0) {
        //				y--;
        //			}
        //		}
        return y;
    }

    /**
     * 取得当天的零点时间
     * 
     * @return
     * @auther <a href="mailto:zhouhongfu@feinno.com">Hongfu Zhou</a> 2010-11-30
     *         上午09:15:21
     */
    public static Date getZeroDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);

        return cal.getTime();
    }

    /**
     * <pre>
     * 返回系统时间
     * @return
     * @author <a href="mailto:liyouliang@feinno.com">李友良</a>
     * 2010-11-24 上午03:07:39
     * </pre>
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     * 
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     */
    public static String getNextDay(String nowdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     * 
     * @param dateDate
     * @param k
     * @return
     */
    public static String dateToStr(java.util.Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 两个时间之间的天数
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        java.util.Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 获取当前时间或者具体时间所在周的周几对应的日期字符串,格式为yyyy-MM-dd
     *
     * @param sdate 可为null
     * @param num 可为null或者"",则返回所在周周一，传下面的值，返回对应的周几
     *        "0"：周日
     *        "1"：周一
     *        "2"：周二
     *        "3"：周三
     *        "4"：周四
     *        "5"：周五
     *        "6"：周六
     * @return String  格式为yyyy-MM-dd
     * @auther <a href="mailto:zhangsongnw@feinno.com">Zhang Song</a>
     * 2011-7-19 上午10:02:44
     */
    public static String getWeekOfChina(Date sdate, String num) {
        // 再转换为时间
        Date nowday = null;
        if (sdate != null) {
            nowday = sdate;
        } else {
            nowday = new Date();
        }
        if (CommonUtil.isEmpty(num)) {
            num = "1";
        }
        Calendar c = Calendar.getInstance();
        c.setTime(nowday);
        String monday = null;
        if (!"0".equals(num)) {
            int d = c.get(Calendar.DAY_OF_WEEK);
            if (d == 1) {
                nowday = DateCoverdUtil.strToDate(DateCoverdUtil.getNextDay(DateCoverdUtil.dateToStr(nowday), "-7"));
                c.setTime(nowday);
            }
        }
        if (num.equals("1")) {// 返回星期一所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        } else if (num.equals("2")) {// 返回星期二所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        } else if (num.equals("3")) {// 返回星期三所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        } else if (num.equals("4")) {// 返回星期四所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        } else if (num.equals("5")) {// 返回星期五所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        } else if (num.equals("6")) {// 返回星期六所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        } else if (num.equals("0")) {// 返回星期日所在的日期
            int d = c.get(Calendar.DAY_OF_WEEK);
            if (d == 1) {
                return DateCoverdUtil.dateToStr(nowday);
            } else {
                nowday = DateCoverdUtil.strToDate(DateCoverdUtil.getNextDay(DateCoverdUtil.dateToStr(nowday), "7"));
                c.setTime(nowday);
            }
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        }
        monday = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

        return monday;
    }

    /**
     * 在日期签名加0
     * 
     * @param day
     * @return
     */
    public static String getZeroDay(int day) {
        String rtn = "";
        if (day <= 9) {
            rtn = "0" + day;
        } else {
            rtn = "" + day;
        }
        return rtn;
    }

    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
     * 
     * @param sdate
     * @param num
     * @return
     */
    public static String getWeek(String sdate, String num) {
        // 再转换为时间
        Date dd = DateCoverdUtil.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        if (num.equals("1")) // 返回星期一所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        else if (num.equals("2")) // 返回星期二所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        else if (num.equals("3")) // 返回星期三所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        else if (num.equals("4")) // 返回星期四所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        else if (num.equals("5")) // 返回星期五所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        else if (num.equals("6")) // 返回星期六所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        else if (num.equals("0")) // 返回星期日所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 判断是否润年
     * 
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate) {

        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0) {
            if ((year % 100) == 0)
                return false;
            else
                return true;
        } else
            return false;
    }

    /**
     * 获取一个月的最后一天
     * 
     * @param dat
     * @return
     */
    public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
        String str = dat.substring(0, 8);
        String month = dat.substring(5, 7);
        int mon = Integer.parseInt(month);
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
            str += "31";
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            str += "30";
        } else {
            if (isLeapYear(dat)) {
                str += "29";
            } else {
                str += "28";
            }
        }
        return str;
    }

    /**
     * 获取现在时间
     * 
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getOKDate(String sdate) {
        if (sdate == null || sdate.equals(""))
            return getStringDateShort();

        // if (!VeStr.Isdate(sdate)) {
        // sdate = getStringDateShort();
        // }
        // 将“/”转换为“-”
        // sdate = VeStr.Replace(sdate, "/", "-");
        // 如果只有8位长度，则要进行转换
        if (sdate.length() == 8)
            sdate = sdate.substring(0, 4) + "-" + sdate.substring(4, 6) + "-" + sdate.substring(6, 8);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(sdate, pos);
        String dateString = formatter.format(strtodate);
        return dateString;
    }

    public static String getNextMonthDay(String sdate, int m) {
        sdate = getOKDate(sdate);
        int year = Integer.parseInt(sdate.substring(0, 4));
        int month = Integer.parseInt(sdate.substring(5, 7));
        month = month + m;
        if (month < 0) {
            month = month + 12;
            year = year - 1;
        } else if (month > 12) {
            month = month - 12;
            year = year + 1;
        }
        String smonth = "";
        if (month < 10)
            smonth = "0" + month;
        else
            smonth = "" + month;
        return year + "-" + smonth + "-01";
    }

    /**
     * 返回一个月中有多少个周几
     * 
     * @param yearmonth
     *            yyyy-MM
     * @param 1-6:周一到周6, 0:周日
     * @return
     */
    public static List<String> getWeekDaysOfMonth(String yearmonth, String weekdayin) {
        List<String> list = new ArrayList<String>();
        String nextmonthfirstday = yearmonth + "-01";
        String nextmonthlastday = DateCoverdUtil.getEndDateOfMonth(nextmonthfirstday);
        long days = DateCoverdUtil.getDays(nextmonthlastday, nextmonthfirstday);
        String nowmonthstr = nextmonthfirstday.substring(0, 7);
        String weekday = "";
        for (int i = 1; i <= days; i++) {
            String nowday = nowmonthstr + "-" + DateCoverdUtil.getZeroDay(i);
            weekday = DateCoverdUtil.getWeek(nowday, weekdayin);
            long daysweek = DateCoverdUtil.getDays(weekday, nowday);
            if (daysweek > 1) {
                weekday = DateCoverdUtil.getNextDay(DateCoverdUtil.getWeek(nowday, weekdayin), "-7");
            } else {
                daysweek = DateCoverdUtil.getDays(weekday, nowday);
            }
            if (!list.contains(weekday)) {
                list.add(weekday);
            }

        }
        String firstweek = list.get(0);
        if (!firstweek.startsWith(yearmonth)) {
            list.remove(0);
        }
        return list;
    }

    public static Date convertToDate(XMLGregorianCalendar cal) {
        if (null == cal) {
            return null;
        }
        GregorianCalendar ca = cal.toGregorianCalendar();
        return ca.getTime();
    }

    @SuppressWarnings("static-access")
    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date cal) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cal);
        javax.xml.datatype.DatatypeFactory dtf = null;
        try {
            dtf = DatatypeFactory.newInstance();
            return dtf.newXMLGregorianCalendar(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH) + 1, calendar
                    .get(calendar.DAY_OF_MONTH), calendar.get(calendar.HOUR_OF_DAY), calendar.get(calendar.MINUTE),
                    calendar.get(calendar.SECOND), calendar.get(calendar.MILLISECOND), calendar
                            .get(calendar.ZONE_OFFSET)
                            / (1000 * 60));
        } catch (DatatypeConfigurationException e) {
            return null;
        }
    }

    //	
    // public static int getBirth2Age(Date birth){
    // Calendar cal = Calendar.getInstance();
    // int currentYear=cal.get(Calendar.YEAR);
    //		
    // }

    /*
     * public static String DateToString(Date date,String format){ return
     * toString(date, format); }
     */

    public static void main(String args[]) {
        String date = "2000-01-31";
        Date d = DateCoverdUtil.toDate(date, FORMAT_YYYY_MM_DD);
        date = DateCoverdUtil.toString(DateCoverdUtil.addMonths(d, 1));
        System.out.println(date);
        //		if("2012-07-24".compareTo("2011-07-25")>0){
        //			System.out.println("111");
        //		} else {
        //			System.out.println("222");
        //		}
        //		String nowday = DateCoverdUtil.toString(new Date(),"yyyy-MM-dd");
        //		String nextmonthday = DateCoverdUtil.getNextMonthDay(nowday, 1);
        //		String nextmonth = nextmonthday.substring(0, 7);
        //		List<String> weekdaylist = DateCoverdUtil.getWeekDaysOfMonth(nextmonth,"3");
        //		for(String weekday : weekdaylist){
        //		   String chd = DateCoverdUtil.toString(DateCoverdUtil.strToDate(weekday),"yyyy年MM月dd日");
        //		   System.out.println(chd);
        //		}
        //		System.out.println(new Date());
        //		String plan_date = "1,2,3,4,5,6,0";
        //		String[] plandates = plan_date.split(",");
        //		for(int i=0;i<plandates.length;i++){
        //			String d = plandates[i];
        //			if("0".equals(d)){
        //				String nextweekday = DateCoverdUtil.getNextDay("2011-08-10", "7");
        //				System.out.println(DateCoverdUtil.getWeek(nextweekday, ""+d));
        //			} else {
        //				System.out.println(DateCoverdUtil.getWeek("2011-08-10", ""+d));
        //			}
        //			
        //		}

    }
}
