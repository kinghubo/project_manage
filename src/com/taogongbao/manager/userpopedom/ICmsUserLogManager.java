package com.taogongbao.manager.userpopedom;

import java.util.Date;

import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.entity.cms.CmsUser;
import com.taogongbao.entity.cms.CmsUserLog;

public interface ICmsUserLogManager {
	
	public void addCmsUserLog(CmsUserLog cmsUserLog);
	
	public Response<PageModel<CmsUserLog>> queryCmsUserLog(CmsUser cmsUser,Date createTimeBegin,Date createTimeEnd,int start,int limit);

}
