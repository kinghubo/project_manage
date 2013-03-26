package com.taogongbao.action.userpopedom;

import com.taogongbao.action.BaseAction;
import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.utils.parse.ParseEngine;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.entity.cms.CmsUserLog;

public class CmsUserLogAction extends BaseAction {

	private static final long serialVersionUID = 6462019930485710332L;

	public void queryCmsUserLog(){
		Response<PageModel<CmsUserLog>> res = getCmsUserLogManager().queryCmsUserLog(getSessionCmsUser(), getsTime(), geteTime(), getStart(), getLimit());
		print(new ParseEngine<Response<PageModel<CmsUserLog>>>().getResponsePage(res));
	}
	
}
