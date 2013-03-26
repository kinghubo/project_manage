package com.taogongbao.common.entity;

import com.taogongbao.common.constant.Constants;

/**
 * 存放排序的字段和排序规则
 *
 * @createTime: 2010-12-1 下午02:56:46
 * @author: <a href="mailto:zhouhongfu@feinno.com">Hongfu Zhou</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:zhouhongfu@feinno.com">Hongfu Zhou</a>
 * @changesSum: 
 * 
 */
public class OrderByBean {
	
	private String orderField ; //要排序的字段名
	private String orderValue = Constants.HqlSign.ASC; //升序或者降序:acs/desc
	
	public OrderByBean(){
	}
	
	public OrderByBean(String orderField, String orderValue) {
		this.orderField = orderField;
		this.orderValue = orderValue;
	}
	
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(String orderValue) {
		this.orderValue = orderValue;
	}
	
	@Override
	public String toString() {
		return "OrderByBean [orderField=" + orderField + ", orderValue="
				+ orderValue + "]";
	}
	
	
	
}
