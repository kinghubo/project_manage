package com.taogongbao.action.userpopedom;

import java.util.Date;

import com.taogongbao.action.BaseAction;
import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.utils.parse.ParseEngine;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.dao.cms.ICmsGroupUserDao;
import com.taogongbao.entity.cms.CmsGroupUser;
import com.taogongbao.entity.cms.CmsUser;
import com.taogongbao.manager.userpopedom.ICmsUserManager;

/**
 * @since: 2012-2-8 下午02:45:30
 * @author: <a href="mailto:zhangfei@feinno.com">zhangfei</a>
 * @updateAuthor: <a href="mailto:zhangfei@feinno.com">zhangfei</a>
 *
 */
public class CmsUserAction extends BaseAction{

	private ICmsUserManager cmsUserManager;
	private String ids;
	private CmsUser cmsUser;
	private String nowPassword;
	private String newPassword;
	private String confirmPassword;
	private ICmsGroupUserDao cmsGroupUserDao;
	private static final long serialVersionUID = -4604244021807287282L;
	
	/**
	 * 添加系统管理员
	 */
	public void addCmsUser() {

        CmsUser user = (CmsUser)this.getRequest().getSession().getAttribute("cmsuser");
        
	    Response<String> res = cmsUserManager.addCmsUser(cmsUser);
	    if(res.getCode() == 1){
    	    CmsGroupUser cmsGroupUser = new CmsGroupUser();
    	    cmsGroupUser.setCreater(user.getUsername());
    	    cmsGroupUser.setCreateTime(new Date());
    	    
    	    cmsGroupUser.setGroupId(16);
    	    cmsGroupUser.setUserId(cmsUser.getId());
    	    cmsGroupUser.setUserName(cmsUser.getUsername());
    	    cmsGroupUserDao.save(cmsGroupUser);
	    }
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
	    String userName = "";
	    int userType = 0;
	    
	    Response<PageModel<CmsUser>> res =cmsUserManager.queryCmsUsers(userName, userType,getStart(), getLimit());
        print(new ParseEngine<Response<PageModel<CmsUser>>>().getResponsePage(res));
	}
	
	/**
	 *  取得当前管理员的帐号名称
	 */
	public void getCurrentAccountName(){
        CmsUser cmsUser = (CmsUser)this.getRequest().getSession().getAttribute("cmsuser");
        String username = (null == cmsUser)?"":cmsUser.getUsername();
        print(username);
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
	 * 管理员修改别人的资料
	 */
	public void updateCmsUserPassword() {

		Response<String> res = cmsUserManager.updateCmsUser(cmsUser);
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

    public ICmsGroupUserDao getCmsGroupUserDao() {
        return cmsGroupUserDao;
    }

    public void setCmsGroupUserDao(ICmsGroupUserDao cmsGroupUserDao) {
        this.cmsGroupUserDao = cmsGroupUserDao;
    }
	
}
