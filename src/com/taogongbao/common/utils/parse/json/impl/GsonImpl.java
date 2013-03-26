package com.taogongbao.common.utils.parse.json.impl;

import java.util.List;
import java.util.Map;

import javax.lang.model.element.Modifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.utils.parse.IParse;
import com.taogongbao.common.utils.parse.Response;

/**
 * 用Gson来实现Json的读取和封装。<br>
 * 如果自定义对象里面用到泛型，则需要在Gson里面自定义TypeToken，不然会抛出异常。<br>
 * 在属性前加transient 关键字可以在序列化时，抛弃此属性。
 * <h1>配置说明</h1><br>
 * 	<h1>GsonBuilder配置：</h1><br>
 * 		disableHtmlEscaping()：不对Html代码转义，默认是转义的<br>
 * 		disableInnerClassSerialization()：不对内部类进行序列化或反序列化<br>
 * 		excludeFieldsWithModifiers(int... modifiers)：配置排除制定类型(参数类型详见{@link Modifier})的属性字段。
 * 													 Gson默认是排除transient和static类型的属性字段的。<br>
 * 		excludeFieldsWithoutExposeAnnotation()：序列化或反序列化时排除未指定Expose注解属性的字段<br>
 * 		generateNonExecutableJson()：生成的Json字符串不在js中运行时的配置，会在生成的json字符串中增加前缀<br>
 * 		serializeNulls()：对空值的属性字段进行序列化或反序列化，默认是不进行序列化<br>
 * 		setPrettyPrinting()：生成Json字符串中的空格不去掉，默认是去掉的<br>
 * 		setVersion(ignoreVersionsAfter)：设置版本，主要运用在有注解属性Until和Since时：<br>
 * 		setLongSerializationPolicy(serializationPolicy)：<br>
 * 		serializeSpecialFloatingPointValues()：<br>
 * 		setFieldNamingStrategy(fieldNamingStrategy)：<br>
 * 		setFieldNamingPolicy(namingConvention)：<br>
 * 		setExclusionStrategies(arg0)：<br>
 * 		setDateFormat()：时间格式化，有几种传参方式。<br>
 * 		registerTypeAdapter(type, typeAdapter)：用自己的类型去替换Json默认类型解析。
 * 			默认：java.net.URL，java.net.URI，java.util.Locale，java.util.Date，java.math.BigDecimal，和java.math.BigInteger<br>
 * 		create()：创建一个Json实例<br><br>
 * 
 * 	<h1>注释部分：</h1><br>
 * <h1>@SerializedName("UserName")</h1>
 * 		在对象属性上增加SerializedName注解之后，<br>
 * 		序列化和反序列化的json字符串中会用此注解上标明的名称。<br>
 * 		如：@SerializedName("UserName")<br>
 * 			private String name;<br>
 * 			则生成的json字符串中name属性实际为UserName。<br><br>
 * <h1>@Expose(serialize =true, deserialize=true)</h1>
 * 		配置有此注解的对象属性，会在序列化或非序列化的时候决定是否对此对象属性序列化或反序列化。<br>
 * 		此注解需要Gson在创建的时候以：<br>
 * 			new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()<br>
 * 		创建才能生效。<br><br>
 * <h1>@Since(1.1)</h1>
 * 		此注解是对属性的版本号的配置。<br>
 * 		当配置好版本号之后，可以使用GsonBuilder获取指定版本号的属性。<br>
 * 		如：@Since(1.0)<br>
 * 			@ Since(1.1)<br>
 * 			private String name;<br>
 * 			@ Since(1.1)<br>
 * 			private String pwd;<br>
 * 		则：<br>
 * 			Gson gson = new GsonBuilder().setVersion(1.0).create();<br>
 * 		只会对name属性进行序列化和反序列化。<br>
 * 			Gson gson = new GsonBuilder().setVersion(1.1).create();<br>
 * 		会对name和pwd属性进行序列化和反序列化。<br><br>
 * <h1>@Until(1.1)</h1>
 * 		<font color="red">此注解是对属性的版本号的配置同上述Since。</font><br>
 * 		当配置好版本号之后，可以使用GsonBuilder获取大于指定版本号的属性。<br>
 * 		如：@Until(1.0)<br>
 * 			private String name;<br>
 * 			@ Until(1.11)<br>
 * 			private String pwd;<br>
 * 			@ Until(1.2)<br>
 * 			private String msg;<br>
 * 		则：<br>
 * 			Gson gson = new GsonBuilder().setVersion(1.1).create();<br>
 * 		只会对msg属性进行序列化和反序列化。<br>
 * 			Gson gson = new GsonBuilder().setVersion(1.0).create();<br>
 * 		会对msg和pwd属性进行序列化和反序列化。<br><br>
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
public class GsonImpl<T> implements IParse<T> {

	private static final long serialVersionUID = 2821409621580789528L;

	private Gson gson;

	public GsonImpl() {
		this.gson = new GsonBuilder().create();
	}

	
	public T str2Obj(String json, Class<T> clazz) {
		return (T) this.gson.fromJson(json, clazz);
	}

	
	public String getString(T t) {
		return (null == t) ? "" : this.gson.toJson(t);
	}
	
	
	public String getResponseString(T t){
		return (null == t) ? "" : this.gson.toJson(t, new TypeToken<Response<Object>>() {}.getType());
	}

	public String getResponsePage(T t){
		return (null == t) ? "" : this.gson.toJson(t, new TypeToken<Response<PageModel<Object>>>() {}.getType());
	}
	
	
	public Object str2Array(String json, String paramType) {
		Object obj = null;
		try {
			obj = this.gson.fromJson(json, Class.forName(paramType));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	
	public List<T> str2List(String json) {
		return gson.fromJson(json, new TypeToken<List<T>>() {}.getType());
	}

	
	public Map<?,?> str2Map(String json) {
		return this.gson.fromJson(json, new TypeToken<Map<?, ?>>() {
		}.getType());
	}
}
