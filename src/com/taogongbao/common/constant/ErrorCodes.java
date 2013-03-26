package com.taogongbao.common.constant;

/**
 * 错误定义 10位长度的int类型 d,ddd,ddd,ddd
 * 第一位：大类
 * 	1:系统错误
 *  2:
 * 
 * @createTime: Mar 31, 2011 1:59:14 PM
 * @author: <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
 * @changesSum: 
 * 
 */
public class ErrorCodes {
	/**
	 * 服务层错误
	 */
	public static final Integer SERVICE_EXCEP=-1000000000;
	
	/**
	 * 用户session过期
	 */
	public static final Integer OUT_OF_SESSION = -1010000000;
}
