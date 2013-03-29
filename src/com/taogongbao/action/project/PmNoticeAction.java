package com.taogongbao.action.project;

import java.sql.Timestamp;

import jodd.typeconverter.Convert;

import com.taogongbao.action.BaseAction;
import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.utils.parse.ParseEngine;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.dao.project.IPmNoticeDao;
import com.taogongbao.entity.cms.CmsUser;
import com.taogongbao.entity.project.PmNotice;

public class PmNoticeAction extends BaseAction{
	private IPmNoticeDao pmNoticeDao;
	private PmNotice pmNotice;
	public IPmNoticeDao getPmNoticeDao() {
		return pmNoticeDao;
	}
	public PmNotice getPmNotice() {
		return pmNotice;
	}
	public void setPmNoticeDao(IPmNoticeDao pmNoticeDao) {
		this.pmNoticeDao = pmNoticeDao;
	}
	public void setPmNotice(PmNotice pmNotice) {
		this.pmNotice = pmNotice;
	}

	public void add() {
		Response<PmNotice> res = new Response<PmNotice>();
		CmsUser user = getSessionCmsUser();
		pmNotice.setCreateUserId(user.getId());
		pmNotice.setCreateUserName(user.getUsername());
		pmNotice.setCreateTime(new Timestamp(System.currentTimeMillis()));
		
		res.setData(pmNoticeDao.save(pmNotice));
		addCmsUserLog("添加项目公告", "添加项目公告:" + res.getData().getName());
		print(new ParseEngine<Response<PmNotice>>().getResponseString(res));
	}
	
	public void delete(){
		Response<Integer> res = new Response<Integer>();
		String[] ids = this.ids.split(",");
		for (String id : ids) {
			pmNoticeDao.remove(Convert.toInteger(id));
		}
		addCmsUserLog("删除项目公告", "删除项目公告" + this.ids);
		print(new ParseEngine<Response<Integer>>().getResponseString(res));
	}
	
	
	public void search(){
		Response<PageModel<PmNotice>> res = new Response<PageModel<PmNotice>>();
		res.setData(pmNoticeDao.getGenPage(null, null, getStart(), getLimit()));
		print(new ParseEngine<Response<PageModel<PmNotice>>>().getResponsePage(res));
	}
	
}
