package com.taogongbao.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.taogongbao.common.constant.ErrorCodes;
import com.taogongbao.common.constant.SessionConstants;
import com.taogongbao.common.utils.parse.ParseEngine;
import com.taogongbao.common.utils.parse.Response;

/**
 * 用户请求，session拦截器
 * @author Administrator
 *
 */
public class SessionIterceptor extends AbstractInterceptor {

	
	private static final long serialVersionUID = -3205060840540633229L;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext ctx = ActionContext.getContext();
		Action action = (Action) actionInvocation.getAction();
		BaseAction baseAction = (BaseAction) action;
		
		Boolean loginFlag = (Boolean)ctx.getSession().get(SessionConstants.USER_SESSION);
		if(loginFlag == null || !loginFlag){
			// 未登录用户
			Response<String> res = new Response<String>();
			res.setCode(ErrorCodes.OUT_OF_SESSION);
			res.setMessage("用户未登录");
			baseAction.print(new ParseEngine<Response<String>>().getResponsePage(res));
			return Action.NONE;
		}else{
			return actionInvocation.invoke();
		}
	}
}
