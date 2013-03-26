package com.taogongbao.common.entity;

import java.io.Serializable;

import com.taogongbao.common.constant.Constants;

/**
 ******************************************************************************** 
 * 模块名 : com.taogongbao.common.entity 文件名 : QueryKeyValue.java 文件实现功能 :
 * 查询条件的键值对最对象 作者 : liugang 版本 : JDK 1.6 备注 : <其它说明> 创建时间: Jul 21, 2010 12:48:41
 * PM --------------------------------- 修改记录 :
 * ---------------------------------- 日 期 版本 修改人 修改内容 YYYY/MM/DD X.Y <作者或修改者名>
 * <修改内容>
 * 
 ******************************************************************************** 
 */
public class QueryKeyValue implements Serializable {

	private static final long serialVersionUID = -1281448558350562076L;

	private String key; // 查询的字段名（与数据库字段一致）
	private Object value; // 查询的字段对应的值
	private String sign=Constants.HqlSign.EQUAL; //一般是'>','>=','<','<=','<>'

	public QueryKeyValue(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public QueryKeyValue(String key, Object value,String sign) {
		this.key = key;
		this.value = value;
		this.sign = sign;
	}

	public QueryKeyValue() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "QueryKeyValue [key=" + key + ", sign=" + sign + ", value="
				+ value + "]";
	}

}
