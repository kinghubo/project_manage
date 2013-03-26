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
public class IndustryPostionBean implements Serializable{
	private static final long serialVersionUID = 4008113220365107707L;
	
	private DataBean dictionaryIndustry;
	private List<DataBean> postionList = new ArrayList<DataBean>();
	
	public List<DataBean> getPostionList() {
		return postionList;
	}
	public void setPostionList(List<DataBean> postionList) {
		this.postionList = postionList;
	}
	public DataBean getDictionaryIndustry() {
		return dictionaryIndustry;
	}
	public void setDictionaryIndustry(DataBean dictionaryIndustry) {
		this.dictionaryIndustry = dictionaryIndustry;
	}
	
	public void addPosition(DataBean position){
		postionList.add(position);
	}
}