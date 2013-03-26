package com.taogongbao.common.entity;

import java.io.Serializable;

/**
 * 
 *
 * @createTime: Mar 8, 2011 12:51:43 PM
 * @author: <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
 * @changesSum: 
 * 
 */
public class DataBean implements Serializable{
	private static final long serialVersionUID = 3698329059507180751L;
	
	private String id;
	private String name;
	
	public DataBean(String id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}