package com.taogongbao.action.userpopedom;

import com.taogongbao.action.BaseAction;
import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.utils.parse.ParseEngine;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.entity.cms.CmsGroupUser;
import com.taogongbao.entity.cms.CmsPopedomGroup;
import com.taogongbao.manager.userpopedom.IPopedomGroupManager;

public class PopedomGroupAction extends BaseAction {

	private CmsGroupUser cmsGroupUser;
	private CmsPopedomGroup cmsPopedomGroup;
	private IPopedomGroupManager popedomGroupManager;
	private static final long serialVersionUID = 8631137359697788119L;
	
	public void addPopedomToGroup(){
		
		Response<String> res = popedomGroupManager.addPopedomToGroup(cmsPopedomGroup);
		addCmsUserLog("设置组权限",res.getMessage(),res);
		print(new ParseEngine<Response<String>>().getResponseString(res));
		
	}
	
	public void addPopedomGroup(){
		
		Response<String> res = popedomGroupManager.addPopedomGroup(cmsPopedomGroup, getSessionCmsUser());
		addCmsUserLog("新增权限组",res.getMessage(),res);
		print(new ParseEngine<Response<String>>().getResponseString(res));
		
	}
	
	public void deletePopedomGroup(){
		
		Response<String> res = popedomGroupManager.deletePopedomGroup(cmsPopedomGroup);
		addCmsUserLog("删除权限组",res.getMessage(),res);
		print(new ParseEngine<Response<String>>().getResponseString(res));
		
	}
	
	public void updatePopedomGroup(){
		
		Response<String> res = popedomGroupManager.updatePopedomGroup(cmsPopedomGroup);
		addCmsUserLog("修改权限组",res.getMessage(),res);
		print(new ParseEngine<Response<String>>().getResponseString(res));
		
	}
	
	public void queryPopedomGroup(){
		
		Response<PageModel<CmsPopedomGroup>> res = popedomGroupManager.queryPopedomGroup(getStart(), getLimit());
		print(new ParseEngine<Response<PageModel<CmsPopedomGroup>>>().getResponsePage(res));
		
	}
	
	public void deleteGroupUser(){
		
		Response<String> res = popedomGroupManager.deleteGroupUser(cmsGroupUser);
		addCmsUserLog("删除成员",res.getMessage(),res);
		print(new ParseEngine<Response<String>>().getResponseString(res));
		
	}
	
	public void addGroupUser(){
		
		Response<String> res = popedomGroupManager.addGroupUser(cmsGroupUser, getSessionCmsUser());
		addCmsUserLog("新增成员",res.getMessage(),res);
		print(new ParseEngine<Response<String>>().getResponseString(res));
		
	}
	
	public void getGroupUserByGroupId(){
		
		Response<PageModel<CmsGroupUser>> res = popedomGroupManager.getGroupUserByGroupId(cmsGroupUser, getStart(), getLimit());
		print(new ParseEngine<Response<PageModel<CmsGroupUser>>>().getResponsePage(res));
		
	}
	
	public void getPopedomTree(){
		
		print(popedomGroupManager.getPopedomTree(cmsPopedomGroup));
		
	}
	
	public CmsPopedomGroup getCmsPopedomGroup() {
		return cmsPopedomGroup;
	}
	public void setCmsPopedomGroup(CmsPopedomGroup cmsPopedomGroup) {
		this.cmsPopedomGroup = cmsPopedomGroup;
	}
	public IPopedomGroupManager getPopedomGroupManager() {
		return popedomGroupManager;
	}
	public void setPopedomGroupManager(IPopedomGroupManager popedomGroupManager) {
		this.popedomGroupManager = popedomGroupManager;
	}
	public CmsGroupUser getCmsGroupUser() {
		return cmsGroupUser;
	}
	public void setCmsGroupUser(CmsGroupUser cmsGroupUser) {
		this.cmsGroupUser = cmsGroupUser;
	}
}
