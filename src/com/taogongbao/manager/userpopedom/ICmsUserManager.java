package com.taogongbao.manager.userpopedom;

import java.util.List;

import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.entity.cms.CmsUser;


public interface ICmsUserManager{
	
	public Response<String> addCmsUser(CmsUser cmsUser);
	
	public Response<String> deleteCmsUsers(String ids);
	
	public Response<List<CmsUser>> queryCmsUsers();
	
	public Response<PageModel<CmsUser>> queryCmsUsers(String userName,int userType,int offset,int length);
	
	public Response<String> updatePassword(CmsUser cmsUser,String nowPassword,String newPassword,String confirmPassword);
	
	public Response<String> updateCmsUserPassword(CmsUser cmsUser);
	
	public Response<String> updateCmsUser(CmsUser cmsUser);
	
	public String getCmsUserByUserName(String userName);

}
