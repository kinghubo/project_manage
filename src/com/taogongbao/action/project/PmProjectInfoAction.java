package com.taogongbao.action.project;

import java.util.List;

import com.taogongbao.action.BaseAction;
import com.taogongbao.common.utils.parse.ParseEngine;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.dao.project.IPmProjectInfoDao;
import com.taogongbao.entity.project.PmProjectInfo;

public class PmProjectInfoAction extends BaseAction{
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
	
	

	/**
	 * 添加系统管理员
	 */
//	public void addCmsUser() {
//		
//		Response<String> res = cmsUserManager.addCmsUser(cmsUser);
//		addCmsUserLog("添加新用户", res.getMessage(),res);
//		print(new ParseEngine<Response<String>>().getResponseString(res));
//		
//	}
	
	/**
	 * 删除管理员，支持多帐号同时删除，ID中间用，隔开。
	 */
//	public void deleteCmsUsers(){
//		
//		Response<String> res = cmsUserManager.deleteCmsUsers(ids);
//		addCmsUserLog("删除用户", "删除用户ID为：" + ids + "，结果：" + res.getMessage(),res);
//		print(new ParseEngine<Response<String>>().getResponseString(res));
//		
//	}
	
	
	/**
	 * 获取管理员列表
	 */
	public void queryProjectInfos(){
		Response<List<PmProjectInfo>> res = new Response<List<PmProjectInfo>>();
		res.setData(pmProjectInfoDao.getAll());
		print(new ParseEngine<Response<List<PmProjectInfo>>>().getResponseString(res));
	}
	
}
