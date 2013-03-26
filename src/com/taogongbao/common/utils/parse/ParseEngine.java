package com.taogongbao.common.utils.parse;

import java.util.List;
import java.util.Map;

import com.taogongbao.common.utils.parse.json.impl.GsonImpl;
import com.taogongbao.common.utils.parse.json.impl.JacksonImpl;
import com.taogongbao.common.utils.parse.json.impl.JsonLibImpl;

/**
 * 解析引擎，负责把传入的数据格式转换为目标数据格式。<br>
 * 本引擎支持对JSON、XML数据转换为Java对象；<br>
 *
 * @createTime: Jan 17, 2011 5:44:20 PM
 * @author: <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
 * @changesSum: 
 * 
 */
public class ParseEngine<T> {
	private IParse<T> json;
	/** 解析JSON数据类型 */
	private static final int JSON_TYPE = 0;
	/** 解析XML数据类型 */
	private static final int XML_TYPE = 1;
	/** 数据类型 */
	public static enum DATA_TYPE {
		XML(XML_TYPE), JSON(JSON_TYPE);
		
		private final int value;
		DATA_TYPE(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	};
	
	/** 使用Gson作为解析器 */
	private static final int GSON_TYPE = 0;
	/** 使用Jackson作为解析器 */
	private static final int JACKSON_TYPE = 1;
	/** 使用JsonLib作为解析器 */
	private static final int JSONLIB_TYPE = 2;
	/** 解析器引擎 */
	public static enum ENGINE_TYPE {
		GSON(GSON_TYPE), JACKSON(JACKSON_TYPE), JSONLIB(JSONLIB_TYPE);

		private final int value;

		ENGINE_TYPE(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	};

	/**
	 * 默认构造函数，默认为使用Gson解析Json
	 */
	public ParseEngine () {
		this.json = new GsonImpl<T>();
	}
	
	/**
	 * 根据类型去初始化解析器
	 * @param dateType	数据类型（暂时支持Json和xml；目前只实现了Json的解析）
	 */
	public ParseEngine(DATA_TYPE dateType) {
		switch (dateType.getValue()) {
		//	Json类型解析
		case JSON_TYPE:
			this.json = new GsonImpl<T>();
			break;
			// XML类型解析
		case XML_TYPE:
			break;
		}
	}
	
	/**
	 * 根据选择的解析器去解析Json格式数据
	 * @param engineType	Json解析器类型，支持Gson、Jackson和Jsonlib
	 */
	public ParseEngine(ENGINE_TYPE engineType) {
		switch (engineType.getValue()) {
			// Gson解析器
			case GSON_TYPE:
				this.json = new GsonImpl<T>();
				break;
			// Jackson解析器
			case JACKSON_TYPE:
				this.json = new JacksonImpl<T>();
				break;
			// JsonLib解析器
			case JSONLIB_TYPE:
				this.json = new JsonLibImpl<T>();
				break;
		}
	}
	
	/**
	 * 根据传入对象获取此对象的Json字符串，没有判断为空的对象
	 * @throws Exception 
	 */
	public String getString(T t){
		return this.json.getString(t);
	}
	
	/**
	 * 针对Response的对象转换为String
	 *
	 * @param t 需要转换的目标{@link Response}对象
	 * @return
	 * @auther <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
	 * Apr 1, 2011 10:58:04 AM
	 */
	public String getResponseString(T t){
		return this.json.getResponseString(t);
	}
	
	public String getResponsePage(T t){
		return this.json.getResponsePage(t);
	}
	
	/**
	 * 把Json字符串转换为Java对象
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public T str2Obj(String json, Class<T> clazz) {
		return this.json.str2Obj(json, clazz);
	}
	public List<?> str2List(String jsonStr) {
		return this.json.str2List(jsonStr);
	}
	
	public Map<?, ?> str2Map(String jsonStr) {
		return this.json.str2Map(jsonStr);
	}
	
	public Object str2Array(String jsonStr, String paramType) {
		return this.json.str2Array(jsonStr, paramType);
	}
	
}
