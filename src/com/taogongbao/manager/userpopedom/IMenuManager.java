package com.taogongbao.manager.userpopedom;

import java.util.List;

import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.entity.cms.CmsMenu;
import com.taogongbao.entity.cms.CmsUser;


public interface IMenuManager {
	
	public String getMenuTree();
	
	public Response<String> deleteMenuById(int id);
	
	public Response<String> updateMenu(CmsMenu cmsMenu);
	
	public Response<String> addMenu(CmsMenu cmsMenu,CmsUser cmsUser);
	
	public List<CmsMenu> queryMenu(int fatherId);

	public Response<PageModel<CmsMenu>> queryMenu(int fatherId,int pageSize,int pageNo);
	
	public String getMenuName(int id);
	
}
