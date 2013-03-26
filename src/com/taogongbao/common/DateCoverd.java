package com.taogongbao.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCoverd {
	public final static String FORMART_YYYY_MM_DD = "yyyy-MM-dd";

	public final static String FORMAR_TYYYYMMDD = "yyyyMMdd";

	public final static String FORMART_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public final static String SQL_FORMART_YYYY_MM_DD_HH_MM_SS = "%Y-%m-%d %T"; //'%Y-%m-%d %H:%i:%S'

	public final static String SQL_FORMAT_YYYY_MM_DD = "%Y-%m-%d";

	//    /**
	//     * 方法概要:将数据库时间转为程序时间
	//     * 
	//     * @param sqldate
	//     *            数据库时间
	//     * @return
	//     */
	//    public static Date coverdSQLtoNomal(java.sql.Date sqldate) {
	//        long sqld = sqldate.getTime();
	//        Date date = new Date(sqldate.getTime());
	//        return date;
	//    }
	//
	//    /**
	//     * 方法概要: 将程序时间转为数据库时间
	//     * 
	//     * @param nomaldate
	//     *            程序时间
	//     * @return
	//     */
	//    public static java.sql.Date coverdNomalToSQL(Date nomaldate) {
	//        System.out.println("=----------------------" + nomaldate);
	//
	//        java.sql.Date date = new java.sql.Date(nomaldate.getTime());
	//        date.setTime(nomaldate.getTime());
	//        System.out.println("=----------------------" + date);
	//        return date;
	//    }

	/**
	 * 方法概要:将String转Date
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date toDate(String date) {
		if (Common.isEmpty(date)) {
			return new Date();
		}
		DateFormat df = getDateFormart();
		Date da = null;
		try {
			da = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return da;
	}

	/**
	 * 函数名称: toDifferYear
	 * 函数功能描述: 判断相差多少年
	 *  @param startyear
	 *  @param lastyear
	 *  @return //TODO <描述该参数的含义> 
	 */
	public static int toDifferYear(Date startyear, Date lastyear) {
		//SimpleDateFormat format = new SimpleDateFormat(FORMART_YYYY_MM_DD);
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
	 * 函数名称: toDifferYear
	 * 函数功能描述: 判断相差多少天
	 *  @param startyear
	 *  @param lastyear
	 *  @return //TODO <描述该参数的含义> 
	 */
	public static int toDifferDay(Date startyear, Date lastyear) {
		//SimpleDateFormat format = new SimpleDateFormat(FORMART_YYYY_MM_DD);
		Calendar cal = Calendar.getInstance();
		cal.setTime(startyear);
		//		int startyears = cal.get(Calendar.YEAR);

		Calendar endca = Calendar.getInstance();
		endca.setTime(lastyear);
		//		int lastyears = endca.get(Calendar.YEAR);

		long daterange = endca.getTimeInMillis() - cal.getTimeInMillis();
		long time = 1000 * 3600 * 24; //A day in milliseconds  
		int resutl = (int) (daterange / time);
		//int result = lastyears - startyears;
		return resutl;
	}

	/**
	 * 函数名称: toDifferNow
	 * 函数功能描述: 同现在比较的天数 
	 *  @param startyear
	 *  @return //TODO <描述该参数的含义> 
	 */
	public static int toDifferNowYear(Date startyear) {
		//SimpleDateFormat format = new SimpleDateFormat(FORMART_YYYY_MM_DD);
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
	 * 函数名称: toDifferNow
	 * 函数功能描述: 同现在比较的天数 
	 *  @param startyear
	 *  @return //TODO <描述该参数的含义> 
	 */
	public static int toDifferNowDay(Date startyear) {
		//SimpleDateFormat format = new SimpleDateFormat(FORMART_YYYY_MM_DD);
		Calendar cal = Calendar.getInstance();
		cal.setTime(startyear);
		//		int startyears = cal.get(Calendar.YEAR);

		Calendar endca = Calendar.getInstance();
		endca.setTime(new Date());
		//		int lastyears = endca.get(Calendar.YEAR);

		long daterange = endca.getTimeInMillis() - cal.getTimeInMillis();
		long time = 1000 * 3600 * 24; //A day in milliseconds  
		int resutl = (int) (daterange / time);
		//int result = lastyears - startyears;
		return resutl;
	}

	/**
	 * 方法概要:将String转Date
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date toDate(String date, String format) {
		DateFormat df = getDateFormart(format);
		Date da = null;
		try {
			da = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return da;
	}

	/**
	 * 方法概要:获取格式信息
	 * 
	 * @param format
	 * @return
	 */
	public static DateFormat getDateFormart(String format) {
		DateFormat df = null;
		if (format == null || format.trim().equals("")) {
			df = new SimpleDateFormat(FORMART_YYYY_MM_DD_HH_MM_SS);
		} else {
			df = new SimpleDateFormat(format);
		}
		return df;
	}

	public static DateFormat getDateFormart() {
		return getDateFormart(FORMART_YYYY_MM_DD_HH_MM_SS);
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
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(sFmt);
			String sRet = formatter.format(dt).toString();
			return sRet;
		}
	}

	/**
	 * 方法概要:增加年
	 *
	 * @param date
	 * @param years
	 * @return
	 */
	public static String addYearsToString(Date date, int years, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + years);//让日期加1 
		date = calendar.getTime();
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
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + years);//让日期加1 
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
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + months);//让日期加1 
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
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);//让日期加1 
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
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hours);//让日期加1 
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
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);//让日期加1 
		date = calendar.getTime();
		return date;
	}

	/**
	 * 方法概要:将日期转换为String串
	 *
	 * @param dt
	 * @return
	 */
	public static String toString(Date dt) {
		return toString(dt, FORMART_YYYY_MM_DD_HH_MM_SS);
	}

	/* public static String DateToString(Date date,String format){
	 	return toString(date, format);
	 }*/

	public static void main(String args[]) {
		String date1 = "1976-08-01 21:27:29";
		Date dd = DateCoverd.toDate(date1);
		//		System.out.println(UUID.randomUUID());
		//		Date date = new Date();
		//		date = DateCoverd.addDays(date, -20);
		//		System.out.println(date);
		//		Date newDate = new Date();
		//		System.out.println(newDate);
		System.out.println(toDifferNowYear(dd));
		//		System.out.println(date);
		//		date = DateCoverd.addMonths(date, 3);
		//		System.out.println(date);
		//		date = DateCoverd.addHours(date, -10);
		//		System.out.println(date);
		//		date = DateCoverd.addMinute(date, 30);
		//		System.out.println(date);

		/*
		 * 2009-10-22 00:04:41.039276------------Thu Oct 22 00:04:41 CST 2009
		 * 2009-10-22 12:04:41.039276------------Thu Oct 22 12:04:41 CST 2009
		 * 2009-10-22 24:04:41.039276------------Fri Oct 23 00:04:41 CST 2009
		 */

		// TimeZone timezone = TimeZone.getTimeZone("CCT");
		// System.out.println(timezone);
		// DateFormat df = DateFormat.getInstance();
		// df.setTimeZone(timezone);
		// try {
		// da = df.parse("2009-10-22 00:04:41.039276");
		// System.out.println(da);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//        
		//        
		//        
		// String date = df.format(new Date());
		// System.out.println(date);
		//        
		// timezone = TimeZone.getTimeZone("CET");
		// System.out.println(timezone);
		// df = DateFormat.getInstance();
		// df.setTimeZone(timezone);
		// date = df.format(new Date());
		// System.out.println(date);
	}
}
