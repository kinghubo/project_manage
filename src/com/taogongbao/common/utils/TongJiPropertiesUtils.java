package com.taogongbao.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * 属性文件操作类，对/WEB-INF/properties/目录下的属性文件进行读取并缓存到map中
 * map以文件名为索引
 * @createTime: May 24, 2011 8:41:23 PM
 * @author: <a href="mailto:zhaoxinnw@feinno.com">Zhou You Cheng</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:zhaoxinnw@feinno.com">Zhou You Cheng</a>
 * @changesSum: 
 * 
 */
public class TongJiPropertiesUtils {
	
	@SuppressWarnings("unused")
	private static Map<String, Properties> propertyMap = new HashMap<String, Properties>();
	/**
	 * 邮件服务配置文件
	 */
	public static final String EMAIL_PROPERTIES = "/properties/email.properties";
	/**
	 * 报表统计所用数据库
	 */
	public static final String JDBC_REPORT_PROPERTIES = "/properties/jdbcReport.properties";
	//错误代码
	public static final String ERROR_CODE = "/properties/message.properties";
	
}

