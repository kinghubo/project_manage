package com.taogongbao.common.utils.parse.json.impl;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.taogongbao.common.utils.parse.IParse;


/**
 * 使用Jackson来实现json的序列化和反序列化
 *
 * @createTime: Jan 17, 2011 3:24:25 PM
 * @author: <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
 * @changesSum: 
 * 
 */
public class JacksonImpl<T> implements IParse<T> {

	private static final long serialVersionUID = 6099985658734000113L;
	private static ObjectMapper mapper = new ObjectMapper();
	
//	public JacksonImpl() {
//		mapper = new ObjectMapper();
//	}
	
	public String getString(Object obj) {
		StringWriter writer = new StringWriter();
		String rs = "";
		try {
			mapper.writeValue(writer, obj);
			rs = writer.toString();
			writer.close();
		} catch (Exception e) {
			//TODO 记录日志
		}
		return rs;
	}

	
	public Object str2Array(String json, String paramType) {
		Object obj = null;
		try {
			obj = mapper.readValue(json, Class.forName(paramType));
		} catch (Exception e) {
			//TODO 记录日志
		}
		return obj;
	}

	
	public List<?> str2List(String json) {
		List<?> list = null;
		try {
			list = mapper.readValue(json, List.class);
		} catch (Exception e) {
			//TODO 记录日志
		}
		return list;
	}

	
	public Map<?, ?> str2Map(String json) {

		Map<?, ?> map = null;
		try {
			map = mapper.readValue(json, Map.class);
		} catch (Exception e) {
			//TODO 记录日志
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	
	public Object str2Obj(String json, Class clazz) {
		Object obj = null;
		try {
			obj = mapper.readValue(json, clazz);
		} catch (Exception e) {
			//TODO 记录日志
		}
		return obj;
	
	}
	
	public String getResponseString(T t){
		return null;
	}
	
	
	public String getResponsePage(T t) {
		// TODO Auto-generated method stub
		return null;
	}
}
