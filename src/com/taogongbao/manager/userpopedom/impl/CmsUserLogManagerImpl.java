package com.taogongbao.manager.userpopedom.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.feinno.baigong.common.tools.StringUtil;
import com.taogongbao.common.constant.Constants;
import com.taogongbao.common.entity.OrderByBean;
import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.entity.QueryKeyValue;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.dao.cms.ICmsUserLogDao;
import com.taogongbao.entity.cms.CmsUser;
import com.taogongbao.entity.cms.CmsUserLog;
import com.taogongbao.manager.userpopedom.ICmsUserLogManager;

public class CmsUserLogManagerImpl implements ICmsUserLogManager {
	
	private ICmsUserLogDao cmsUserLogDao;

	@Override
	public void addCmsUserLog(CmsUserLog cmsUserLog) {
		
		if(null == cmsUserLog || StringUtil.isEmpty(cmsUserLog.getUserId())
				|| StringUtil.isEmpty(cmsUserLog.getMenuName()) || StringUtil.isEmpty(cmsUserLog.getContent())){
			return;
		}
		cmsUserLog.setCreateTime(new Timestamp(new Date().getTime()));
		cmsUserLogDao.save(cmsUserLog);
		
	}

	@Override
	public Response<PageModel<CmsUserLog>> queryCmsUserLog(CmsUser cmsUser, Date createTimeBegin, Date createTimeEnd,int start, int limit) {
		
		if(null == cmsUser || StringUtil.isEmpty(cmsUser.getId())){
			return null;
		}
		List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
		queryList.add(new QueryKeyValue("userId",cmsUser.getId()));
		if(null != createTimeBegin && null != createTimeEnd){
			if(createTimeBegin.after(createTimeEnd)){
				Date d = createTimeBegin;
				createTimeBegin = createTimeEnd;
				createTimeEnd = d;
			}
		}
		if(null != createTimeBegin){
			queryList.add(new QueryKeyValue("createTime",createTimeBegin,">="));
		}
		if(null != createTimeEnd){
			queryList.add(new QueryKeyValue("createTime",createTimeEnd,"<="));
		}
		List<OrderByBean> orderList = new ArrayList<OrderByBean>();
		orderList.add(new OrderByBean("createTime",Constants.HqlSign.DESC));
		
		Response<PageModel<CmsUserLog>> res = new Response<PageModel<CmsUserLog>>();
		res.setData(cmsUserLogDao.getGenPage(queryList, orderList, start, limit));
		return res;
	}
	
	public ICmsUserLogDao getCmsUserLogDao() {
		return cmsUserLogDao;
	}
	public void setCmsUserLogDao(ICmsUserLogDao cmsUserLogDao) {
		this.cmsUserLogDao = cmsUserLogDao;
	}
}
