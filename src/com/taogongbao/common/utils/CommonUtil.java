package com.taogongbao.common.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class CommonUtil {
	/* 列表，数组 */
	public static final int ARRAY = 0;
	/* 普通数据 */
	public static final int NOMAL = 1;

	/**
	 * 方法的概要：根据异常获取到String说明
	 * 
	 * @param e
	 *            异常s
	 * @return
	 */
	public static String getStackTraceStrFromException(Exception e) {
		StringBuilder logStrBuilder = new StringBuilder();
		StackTraceElement[] traces = e.getStackTrace();

		logStrBuilder.append(e.getMessage());
		logStrBuilder.append("\n");
		for (int i = 0; i < traces.length; i++) {
			logStrBuilder.append(traces[i].toString());
			logStrBuilder.append("\n");
		}

		return logStrBuilder.toString();
	}

	/**
	 * 方法的概要：字符为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 方法的概要：字符为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Integer str) {
		if (str == null || "0".equals(str.toString())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 方法的概要：数组为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object[] obj) {
		if (obj == null || obj.length == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 方法的概要：数组为空
	 * 
	 * @param obj
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public static boolean isEmpty(List obj) {
		if (obj == null || obj.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 方法的概要：判断是否为空
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isEmpty(UUID id) {
		if (id == null || id.toString().equals("00000000-0000-0000-0000-000000000000")) {
			return true;
		}
		return false;
	}

	/**
	 * 方法的概要：String转int类型
	 * 
	 * @param str
	 * @return
	 */
	public static int stringToInteger(String str) {
		int iChange = 0;
		if (!CommonUtil.isEmpty(str)) {
			try {
				iChange = Integer.parseInt(str);
			} catch (Exception e) {
				iChange = 0;
			}
		}
		return iChange;
	}

	/**
	 * 方法的概要：字符串转long型
	 * 
	 * @param str
	 * @return
	 */
	public static long stringTolong(String str) {
		long lChanged = 0;
		if (!CommonUtil.isEmpty(str)) {
			try {
				lChanged = new BigDecimal(str).longValue();
			} catch (Exception e) {
				lChanged = 0;
			}
		}
		return lChanged;
	}

	/**
	 * 方法的概要：字符串转bool型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean stringToBoolean(String str) {
		boolean iChange = false;
		if (!CommonUtil.isEmpty(str)) {
			try {
				iChange = Boolean.parseBoolean(str);
			} catch (Exception e) {
			}
		}
		return iChange;
	}

	/**
	 * 方法概要:获取数组对象的STRING串
	 * 
	 * @param args
	 * @return
	 */
	public static String getToString(int type, Object... args) {
		String value = "";
		if (type == ARRAY) {
			if (args != null && args.length > 0) {
				for (Object obj : args) {
					value = value + toString(obj) + ",";
				}
			}
		} else {
			value = toString(args[0]);
		}
		return value;
	}

	/**
	 * 方法概要:将对象转成String
	 * 
	 * @param obj
	 * @return
	 */
	private static String toString(Object obj) {
		String value = String.valueOf(obj);
		return value;
	}

	/**
	 * 方法概要:将列表转换成字组
	 * 
	 * @param str
	 * @param regex
	 * @return
	 */
	public static Integer[] stringToArray(String str, String regex) {
		try {
			String[] s = str.split(regex);
			Integer[] ite = new Integer[s.length];
			for (int i = 0; i < s.length; i++) {
				ite[i] = Integer.parseInt(s[i]);
			}
			return ite;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 方法概要:位运算判断语句
	 * 
	 * @param type
	 *            获取的类型
	 * @param detailtype
	 *            传入的类型
	 * @return
	 */
	public static boolean existtype(int type, int detailtype) {
		boolean exist = (type | detailtype) == detailtype;
		return exist;
	}

	/**
	 * 
	 * 函数名称: getNowDate
	 * 函数功能描述: 获得当前时间的Date对象
	 *  @return //TODO <描述该参数的含义>
	 */
	public static Date getNowDate() {
		return new Date();
	}
	
	/**
	 * 给一个ids串前后添加逗号
	 *
	 * @param str
	 * @return
	 * @auther <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
	 * Dec 20, 2010 7:16:55 PM
	 */
	public static String addComma(String str){
		if(str == null || str.trim().length() == 0){
			
		} else{
			str = str.trim();
			if(!str.startsWith(",")){
				str = "," + str;
			}
			if(!str.endsWith(",")){
				str = str + ",";
			}
		}
		return str;
	}
	
	/**
	 * 把一个ids串前后逗号删除
	 *
	 * @param str
	 * @return
	 * @auther <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
	 * Dec 20, 2010 7:16:55 PM
	 */
	public static String removeComma(String str){
		if(str == null || str.trim().length() == 0){
			
		} else{
			if(str.startsWith(",")){
				str = str.substring(1);
			}
			if(str.endsWith(",")){
				str = str.substring(0, str.length()-1);
			}
		}
		return str;
	}
	
	/**
	 * 根据location id 获取seftid
	 * @param id
	 * @return
	 * @author <a href="mailto:liyouliang@feinno.com">李友良</a>
	 * 2010-12-24 上午09:34:51
	 */
	public static String getSeftId(String id) {
		long tmp = Long.valueOf(id);
		if (tmp % 100 == 0) {
			tmp = tmp / 100;
			return getSeftId(String.valueOf(tmp));
		} else {
			return String.valueOf(tmp);
		}
	}

}