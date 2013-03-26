/**
 * @Copyright 2009, 北京新媒传信科技有限公司 L.P. All rights
 * reserved. The information contained herein is confidential and
 * proprietary to 北京新媒传信科技有限公司, and considered a trade secret
 * as defined under civil and criminal statutes. 北京新媒传信科技有限公司
 * shall pursue its civil and criminal remedies in the event of
 * unauthorized use or misappropriation of its trade secrets. Use of
 * this information by anyone other than authorized employees of 北京
 * 新媒传信科技有限公司 is granted only under a written non-disclosure
 * agreement, expressly prescribing the scope and manner of such use.
 */
package com.taogongbao.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * String类型的相关处理类
 *
 * @createTime: Jan 11, 2010 9:25:19 AM
 * @author: <a href="mailto:gaoxuguo@feinno.com">Gao XuGuo</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime:
 * @updateAuthor: <a href="mailto:gaoxuguo@feinno.com">Gao XuGuo</a>
 * @changesSum:
 * 
 */
public class StringUtils {
	
	/**
	 * 判断字符串是否为空，包括了判断字符串的"0"和"null"，为空则返回True，不为空则返回false
	 *
	 * @param str
	 * @return
	 * @auther <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
	 * Oct 25, 2010 4:29:06 PM
	 */
	public static boolean checkNull(String str) {
		return (null == str || "".equals(str.trim())
				|| str.trim().length() == 0 || "0".equals(str.trim()) || "null"
				.equals(str.trim())) ? true : false;
	}
	
	/**
	 * 判断字符串是否为空（不判断是否为0，“null”）。为空则返回True，不为空则返回false
	 *
	 * @param String str
	 * @return boolean
	 * @auther <a href="mailto:gaoxuguo@feinno.com">Gao XuGuo</a>
	 * Jan 11, 2010 9:35:45 AM
	 */
	public static boolean isNull (String str) {
		return (null == str || "".equals(str.trim()) || str.trim().length() == 0) ? true
				: false;
	}
	
	/**
	 * 读取taogongbao.properties配置文件
	 * @param key
	 * @return
	 * @createTime:2010-11-18
	 * @author: <a href="mailto:liyouliang@feinno.com">李友良</a>
	 * @version:
	 * @lastVersion:
	 * @updateTime:2010-11-18
	 * @updateAuthor: <a href="mailto:liyouliang@feinno.com">李友良</a>
	 * @changesSum:
	 *
	 */
	public static String getProp(String key) {
		try {
			Properties properties = new Properties();
			properties.load(StringUtils.class.getClassLoader()
					.getResourceAsStream("properties/taogongbao.properties"));
			return properties.getProperty(key);
		} catch (FileNotFoundException e) {
			System.out.println("配置文件没有被找到...");// TODO LOG
		} catch (IOException ie) {
			System.out.println("读取配置文件时IO异常");// TODO LOG
		}
		return null;
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
	 * <pre>
	 * 根据location id 获取seftid
	 * @param id
	 * @return
	 * @author <a href="mailto:liyouliang@feinno.com">李友良</a>
	 * 2010-12-24 上午09:34:51
	 * </pre>
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