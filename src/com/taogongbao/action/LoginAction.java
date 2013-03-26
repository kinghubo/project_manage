package com.taogongbao.action;

import com.feinno.baigong.common.tools.StringUtil;
import com.taogongbao.common.Crypt;
import com.taogongbao.common.constant.SessionConstants;
import com.taogongbao.common.utils.parse.ParseEngine;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.entity.cms.CmsUser;
import com.taogongbao.manager.ILoginManager;

public class LoginAction extends BaseAction {

	private CmsUser cmsUser;
	private ILoginManager loginManager;
	private static final long serialVersionUID = 3287691733721648077L;

	
	/**
	 * 用户登陆
	 */
	public void login(){
		
		Response<String> res = new Response<String>();
		if(null != cmsUser && !StringUtil.isEmpty(cmsUser.getUsername()) && !StringUtil.isEmpty(cmsUser.getPassword())){
			try {
				cmsUser = loginManager.login(cmsUser.getUsername(), Crypt.StrEnCrypt(cmsUser.getPassword()));
				if(cmsUser != null){
					String popedomTree = loginManager.queryPopedomTree(cmsUser.getId(), getRequest().getSession());
					getRequest().getSession().setAttribute(SessionConstants.CHILDREN, popedomTree);
					getRequest().getSession().setAttribute(SessionConstants.USER_SESSION, true);
					getRequest().getSession().setAttribute(SessionConstants.CMS_USER, cmsUser);
					res.setCode(1);
					res.setMessage("登陆成功。");
					addCmsUserLog("系统登录", "成功登录系统。");
				}else{ 
					res.setCode(-1);
					res.setMessage("用户名不存在或密码错误。");
				}
			} catch (Exception e1) {
				res.setCode(-1);
				res.setMessage("密码加密时报错。");
				e1.printStackTrace();
			}
		}else{ 
			res.setCode(-1); 
			res.setMessage("用户名或密码传输错误。");
		}
		print(new ParseEngine<Response<String>>().getResponseString(res));
		
	}
	
	/**
	 * 用户退出
	 * @return
	 */
	public String loginOut(){
		
		addCmsUserLog("退出系统", "成功退出系统。");
		getRequest().getSession().removeAttribute(SessionConstants.USER_SESSION);
		getRequest().getSession().removeAttribute(SessionConstants.CMS_USER);
		getRequest().getSession().removeAttribute(SessionConstants.QUANXIAN_LIST);
		getRequest().getSession().removeAttribute(SessionConstants.CHILDREN);
		return SUCCESS;
		
	}
	
	public CmsUser getCmsUser() {
		return cmsUser;
	}

	public void setCmsUser(CmsUser cmsUser) {
		this.cmsUser = cmsUser;
	}

	public ILoginManager getLoginManager() {
		return loginManager;
	}
	public void setLoginManager(ILoginManager loginManager) {
		this.loginManager = loginManager;
	}
	
}
