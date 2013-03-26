package com.taogongbao.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 * 
 * @createTime: 2010-11-24 上午03:04:34
 * @author: <a href="mailto:liyouliang@feinno.com">李友良</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:liyouliang@feinno.com">李友良</a>
 * @changesSum:
 * </pre>
 */
public class DateUtil {
	public static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdfMin = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");

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
	 * <pre>
	 * 返回系统时间
	 * @return
	 * @author <a href="mailto:liyouliang@feinno.com">李友良</a>
	 * 2010-11-24 上午03:07:22
	 * </pre>
	 */
	public static String sysdate() {
		return sdf.format(now());
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
	 * 字符串格式转化为java.util.Date格式
	 *
	 * @param str yyyy-MM-dd格式的字符串
	 * @return java.util.Date对象
	 */
	public static Date strToDate(String str)
	{
		if(!CommonUtil.isEmpty(str))
		{
			DateFormat time = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				return time.parse(str);
			}
			catch (Exception e)
			{
				return null;
			}
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(sdf.format(DateUtil.getZeroDate()));
	}

}
