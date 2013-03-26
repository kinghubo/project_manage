package com.taogongbao.common.utils.parse;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 解析接口类。
 *
 * @createTime: Jan 17, 2011 5:35:04 PM
 * @author: <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
 * @changesSum: 
 * 
 */
public interface IParse<T> extends Serializable {
	/**
	 * 根据Java对象获取字符串
	 * @param t	需要转换的目标Java对象
	 * @return
	 * @throws Exception
	 * @auther <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
	 * Jan 17, 2011 5:37:44 PM
	 */
	public String getString(T t);
	
	/**
	 * 针对Response的对象转换为String
	 *
	 * @param t 需要转换的目标{@link Response}对象
	 * @return
	 * @auther <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
	 * Apr 1, 2011 10:58:04 AM
	 */
	public String getResponseString(T t);
	
	/**
	 * 根据字符串封装为Java对象，需要指定对象
	 * 
	 * @param str	需要转换的字符串
	 * @param clazz	目标对象
	 * 
	 * @return
	 * @throws Exception
	 * @auther <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
	 * Jan 17, 2011 5:38:05 PM
	 */
	public T str2Obj(String str, Class<T> clazz);
	
	/**
	 * 根据字符串封装为List对象
	 * @param str	需要转换的字符串
	 * @return
	 * @throws Exception
	 * @auther <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
	 * Jan 17, 2011 5:38:29 PM
	 */
	public List<?> str2List(String str);
	/**
	 * 根据字符串封装为Map对象
	 * @param str	需要转换的字符串
	 *
	 * @return
	 * @throws Exception
	 * @auther <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
	 * Jan 17, 2011 5:38:40 PM
	 */
	public Map<?, ?> str2Map(String str);
	
	/**
	 * 根据字符串封装为数组对象
	 *
	 * @param str		需要转换的字符串
	 * @param paramType	数组类型
	 * @return
	 * @throws Exception
	 * @auther <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
	 * Jan 17, 2011 5:38:54 PM
	 */
	public Object str2Array(String str, String paramType);
	
	public String getResponsePage(T t);
}
