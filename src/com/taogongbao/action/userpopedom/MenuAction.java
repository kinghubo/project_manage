package com.taogongbao.action.userpopedom;

import java.util.List;

import com.taogongbao.action.BaseAction;
import com.taogongbao.common.constant.SessionConstants;
import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.utils.parse.ParseEngine;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.entity.cms.CmsMenu;
import com.taogongbao.manager.userpopedom.IMenuManager;

public class MenuAction extends BaseAction {

	private int id;
	private String url;
	private int fatherId;
	private CmsMenu cmsMenu;
	private IMenuManager menuManager;
	private static final long serialVersionUID = 403240239874451000L;
	
	
	@SuppressWarnings("unchecked")
	public void nodeTransferStation(){
		
		if(null != url && 0 != id){
			try {
				List<CmsMenu> cmsMenus = menuManager.queryMenu(id);
				StringBuffer sb = new StringBuffer();
				List<String> quanxian = (List<String>) this.getRequest().getSession().getAttribute(SessionConstants.QUANXIAN_LIST);
				for(CmsMenu cm : cmsMenus){
					if(quanxian.contains(cm.getId()+"")){
						sb.append("&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<a style='color:#000;text-decoration: none' href='javascript:"+cm.getMenuUrl()+"()'>"+cm.getMenuName()+"</a>");
					}
				}
				if(sb.length() > 1){
					sb.append("&nbsp;&nbsp;&nbsp;|");
				}else{
					sb.append("&nbsp;");
				}
//				String menuName = menuManager.getMenuName(id);
				//addCmsUserLog(menuName, "访问菜单："+menuName);
				this.getRequest().setAttribute("tbar", sb.toString());
				this.getRequest().getRequestDispatcher(url).forward(getRequest(), getResponse());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(null != url){
			Response<String> res = new Response<String>();
			res.setCode(1);
			print(new ParseEngine<Response<String>>().getResponseString(res));
		}
	}
	
	public void getMenuTree(){
		
		print(menuManager.getMenuTree());
		
	}
	
	public void deleteMenuById(){
		
		String menuName = menuManager.getMenuName(id);
		Response<String> res = menuManager.deleteMenuById(id);
		addCmsUserLog("删除菜单", "删除ID为：" + id + "的" + menuName + "菜单(按钮)。结果："+res.getMessage(),res);
		print(new ParseEngine<Response<String>>().getResponseString(res));
		
	}
	
	public void updateMenu(){
		
		Response<String> res = menuManager.updateMenu(cmsMenu);
		addCmsUserLog("修改菜单",res.getMessage(),res);
		print(new ParseEngine<Response<String>>().getResponseString(res));
		
	}
	
	public void getAllCmsMenu(){
		
		Response<PageModel<CmsMenu>> res = menuManager.queryMenu(fatherId, getStart(), getLimit());
		print(new ParseEngine<Response<PageModel<CmsMenu>>>().getResponsePage(res));
		
	}
	
	public void addMenu(){
		
		Response<String> res = menuManager.addMenu(cmsMenu, getSessionCmsUser());
		addCmsUserLog("新增菜单",res.getMessage(),res);
		print("{success:true,msg:'"+res.getMessage()+"'}");
		
	}
	
	public void setCmsMenu(CmsMenu cmsMenu) {
		this.cmsMenu = cmsMenu;
	}
	public CmsMenu getCmsMenu() {
		return cmsMenu;
	}
	public int getFatherId() {
		return fatherId;
	}
	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public IMenuManager getMenuManager() {
		return menuManager;
	}
	public void setMenuManager(IMenuManager menuManager) {
		this.menuManager = menuManager;
	}
}
