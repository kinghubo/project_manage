package com.taogongbao.action;

import com.opensymphony.xwork2.Action;
import com.taogongbao.common.constant.ErrorCodes;
import com.taogongbao.common.constant.SessionConstants;
import com.taogongbao.common.utils.parse.ParseEngine;
import com.taogongbao.common.utils.parse.Response;

public class IntervalAction extends BaseAction{
	private static final long serialVersionUID = -4687719934982715924L;

	public String getInterval(){
		Boolean loginFlag = (Boolean)getRequest().getSession().getAttribute(SessionConstants.USER_SESSION);
		if(loginFlag == null || !loginFlag){
			Response<String> res = new Response<String>();
			res.setCode(ErrorCodes.OUT_OF_SESSION);
			res.setMessage("登陆超时");
			print(new ParseEngine<Response<String>>().getResponsePage(res));
			return Action.NONE;
		}else{
			Response<String> res = new Response<String>();
			print(new ParseEngine<Response<String>>().getResponsePage(res));
			return Action.NONE;
		}
	} 
}
