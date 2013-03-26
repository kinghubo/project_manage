package com.taogongbao.action.userpopedom;

import java.util.List;

import com.taogongbao.action.BaseAction;
import com.taogongbao.common.utils.parse.ParseEngine;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.entity.cms.CmsUser;
import com.taogongbao.manager.userpopedom.ICmsUserManager;

/**
 * @since: 2012-2-8 下午02:45:30
 * @author: <a href="mailto:zhangfei@feinno.com">zhangfei</a>
 * @updateAuthor: <a href="mailto:zhangfei@feinno.com">zhangfei</a>
 *
 */
public class CmsUserAction extends BaseAction{

	private String ids;
	private CmsUser cmsUser;
	private String nowPassword;
	private String newPassword;
	private String confirmPassword;
	private ICmsUserManager cmsUserManager;
	private static final long serialVersionUID = -4604244021807287282L;
	
	/**
	 * 添加系统管理员
	 */
	public void addCmsUser() {
		
		Response<String> res = cmsUserManager.addCmsUser(cmsUser);
		addCmsUserLog("添加新用户", res.getMessage(),res);
		print(new ParseEngine<Response<String>>().getResponseString(res));
		
	}
	
	/**
	 * 删除管理员，支持多帐号同时删除，ID中间用，隔开。
	 */
	public void deleteCmsUsers(){
		
		Response<String> res = cmsUserManager.deleteCmsUsers(ids);
		addCmsUserLog("删除用户", "删除用户ID为：" + ids + "，结果：" + res.getMessage(),res);
		print(new ParseEngine<Response<String>>().getResponseString(res));
		
	}
	
	
	/**
	 * 获取管理员列表
	 */
	public void queryCmsUsers(){
		
		Response<List<CmsUser>> res = cmsUserManager.queryCmsUsers();
		print(new ParseEngine<Response<List<CmsUser>>>().getResponseString(res));
		
	}
	
	/**
	 * 修改自己的密码
	 */
	public void updatePassword(){
		
		Response<String> res = cmsUserManager.updatePassword(getSessionCmsUser(), nowPassword, newPassword, confirmPassword);
		addCmsUserLog("修改密码", res.getMessage(),res);
		print(new ParseEngine<Response<String>>().getResponseString(res));
		
	}
	
	/**
	 * 管理员修改别人的密码
	 */
	public void updateCmsUserPassword() {
		
		Response<String> res = cmsUserManager.updateCmsUserPassword(cmsUser);
		addCmsUserLog("修改用户密码", res.getMessage(),res);
		print(new ParseEngine<Response<String>>().getResponseString(res));
		
	}
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public CmsUser getCmsUser() {
		return cmsUser;
	}
	public void setCmsUser(CmsUser cmsUser) {
		this.cmsUser = cmsUser;
	}
	public ICmsUserManager getCmsUserManager() {
		return cmsUserManager;
	}
	public void setCmsUserManager(ICmsUserManager cmsUserManager) {
		this.cmsUserManager = cmsUserManager;
	}
	public String getNowPassword() {
		return nowPassword;
	}
	public void setNowPassword(String nowPassword) {
		this.nowPassword = nowPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
