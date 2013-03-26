package com.taogongbao.action;

import java.util.List;

import com.taogongbao.common.utils.parse.ParseEngine;
import com.taogongbao.entity.pu.Location;
import com.taogongbao.entity.pu.Options;
import com.taogongbao.manager.ICommonManager;

public class CommonAction extends BaseAction {

	private String parentId;
	private ICommonManager commonManager;
	private static final long serialVersionUID = -6984980960565166791L;
	
	public void queryLocation(){
		List<Location> res = commonManager.queryLocation(parentId);
		print(new ParseEngine<List<Location>>().getString(res));
	}
	
	public void queryOptions(){
		List<Options> res = commonManager.queryOptions();
		print(new ParseEngine<List<Options>>().getString(res));
	}

	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public ICommonManager getCommonManager() {
		return commonManager;
	}
	public void setCommonManager(ICommonManager commonManager) {
		this.commonManager = commonManager;
	}
}
