package com.taogongbao.common.utils.parse.json.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import com.taogongbao.common.utils.parse.IParse;
import com.taogongbao.common.utils.parse.json.DateJsonValueProcessor;

/**
 * <pre>
 * 
 * @createTime: 2010-11-23 上午01:54:13
 * @author: <a href="mailto:liyouliang@feinno.com">李友良</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:liyouliang@feinno.com">李友良</a>
 * @changesSum:
 * </pre>
 */
public class JsonLibImpl<T> implements IParse<T> {
	private static final long serialVersionUID = -5036407174034244357L;

	
	public String getString(T t) {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
//		config.setExcludes(exc);// 只要设置这个数组，指定过滤哪些字段。
		JSONArray fromObject = JSONArray.fromObject(t, config);
		return fromObject.toString();
	}

	
	public Object str2Array(String json, String paramType) {
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(json);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		return JSONSerializer.toJava(jsonArray, jsonConfig);
	}

	
	public List<?> str2List(String json) {
		return (List<?>) JSONSerializer.toJava((JSONArray) JSONSerializer.toJSON(json));
	}

	
	public Map<?, ?> str2Map(String json) {
		return  (Map<?, ?>) JSONSerializer.toJava((JSONArray) JSONSerializer.toJSON(json));
	}

	@SuppressWarnings("unchecked")
	
	public T str2Obj(String json, Class<T> clazz) {
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(json);
		return (T) JSONSerializer.toJava(jsonObject);   
	}
	
	
	public String getResponseString(T t){
		return null;
	}

	
	public String getResponsePage(T t) {
		// TODO Auto-generated method stub
		return null;
	}
}
