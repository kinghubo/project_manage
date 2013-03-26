package com.taogongbao.common.utils.parse.json.impl;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;

/**
 * <pre>
 * 
 * @createTime: 2010-11-23 上午11:31:40
 * @author: <a href="mailto:liyouliang@feinno.com">李友良</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:liyouliang@feinno.com">李友良</a>
 * @changesSum:
 * </pre>
 */
public class HibernateJsonBeanProcessor implements JsonBeanProcessor {
	public JSONObject processBean(Object obj, JsonConfig jsonConfig) {
		return new JSONObject();
	}
}
