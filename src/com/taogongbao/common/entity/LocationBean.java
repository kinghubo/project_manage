package com.taogongbao.common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @createTime: Mar 8, 2011 12:52:00 PM
 * @author: <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
 * @changesSum: 
 * 
 */
public class LocationBean implements Serializable{
	private static final long serialVersionUID = 4008113220365107707L;
	
	private DataBean parentLocation;
	private List<DataBean> childList = new ArrayList<DataBean>();
	
	public void addChildList(DataBean position){
		childList.add(position);
	}

	public DataBean getParentLocation() {
		return parentLocation;
	}

	public void setParentLocation(DataBean parentLocation) {
		this.parentLocation = parentLocation;
	}

	public List<DataBean> getChildList() {
		return childList;
	}

	public void setChildList(List<DataBean> childList) {
		this.childList = childList;
	}
}