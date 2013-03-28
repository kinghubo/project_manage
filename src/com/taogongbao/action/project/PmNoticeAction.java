package com.taogongbao.action.project;

import java.sql.Timestamp;
import java.util.List;

import jodd.typeconverter.Convert;

import com.taogongbao.action.BaseAction;
import com.taogongbao.common.utils.parse.ParseEngine;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.dao.project.IPmProjectInfoDao;
import com.taogongbao.entity.cms.CmsUser;
import com.taogongbao.entity.project.PmProjectInfo;

public class PmNoticeAction extends BaseAction{
	private IPmProjectInfoDao pmProjectInfoDao;
	private PmProjectInfo pmProjectInfo;
	public void setPmProjectInfoDao(IPmProjectInfoDao pmProjectInfoDao) {
		this.pmProjectInfoDao = pmProjectInfoDao;
	}
	public PmProjectInfo getPmProjectInfo() {
		return pmProjectInfo;
	}
	public void setPmProjectInfo(PmProjectInfo pmProjectInfo) {
		this.pmProjectInfo = pmProjectInfo;
	}
	
	

	public void add() {
		Response<PmProjectInfo> res = new Response<PmProjectInfo>();
		CmsUser user = getSessionCmsUser();
		pmProjectInfo.setCreateUserId(user.getId());
		pmProjectInfo.setCreateUserName(user.getUsername());
		pmProjectInfo.setCreateDate(new Timestamp(System.currentTimeMillis()));
		pmProjectInfo.setLastUpdate(new Timestamp(System.currentTimeMillis()).toLocaleString());
		
		res.setData(pmProjectInfoDao.save(pmProjectInfo));
		addCmsUserLog("添加新项目", "添加新项目:" + res.getData().getName());
		print(new ParseEngine<Response<PmProjectInfo>>().getResponseString(res));
	}
	
	public void delete(){
		Response<Integer> res = new Response<Integer>();
		String[] ids = this.ids.split(",");
		for (String id : ids) {
			pmProjectInfoDao.remove(Convert.toInteger(id));
		}
		addCmsUserLog("删除项目", "删除项目" + this.ids);
		print(new ParseEngine<Response<Integer>>().getResponseString(res));
	}
	
	
	public void queryProjectInfos(){
		Response<List<PmProjectInfo>> res = new Response<List<PmProjectInfo>>();
		res.setData(pmProjectInfoDao.getAll());
		print(new ParseEngine<Response<List<PmProjectInfo>>>().getResponseString(res));
	}
	
}
