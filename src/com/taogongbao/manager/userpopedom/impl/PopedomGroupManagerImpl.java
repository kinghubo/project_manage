package com.taogongbao.manager.userpopedom.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.feinno.baigong.common.tools.StringUtil;
import com.taogongbao.common.constant.Constants;
import com.taogongbao.common.entity.OrderByBean;
import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.entity.QueryKeyValue;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.dao.cms.ICmsGroupUserDao;
import com.taogongbao.dao.cms.ICmsMenuDao;
import com.taogongbao.dao.cms.ICmsPopedomGroupDao;
import com.taogongbao.entity.cms.CmsGroupUser;
import com.taogongbao.entity.cms.CmsMenu;
import com.taogongbao.entity.cms.CmsPopedomGroup;
import com.taogongbao.entity.cms.CmsUser;
import com.taogongbao.manager.userpopedom.IPopedomGroupManager;

public class PopedomGroupManagerImpl implements IPopedomGroupManager {

	private ICmsMenuDao cmsMenuDao;
	private ICmsGroupUserDao cmsGroupUserDao;
	private ICmsPopedomGroupDao cmsPopedomGroupDao;
	
	@Override
	public Response<String> addGroupUser(CmsGroupUser cmsGroupUser,CmsUser cmsUser) {

		Response<String> res = new Response<String>();
		if(null == cmsUser){
			res.setCode(-1);
			res.setMessage("SESSION失效，请重新登陆。");
			return res;
		}
		if(null != cmsGroupUser && null != cmsGroupUser.getGroupId() && !StringUtil.isEmpty(cmsGroupUser.getUserId()) && !StringUtil.isEmpty(cmsGroupUser.getUserName())){
			List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
			queryList.add(new QueryKeyValue("groupId",cmsGroupUser.getGroupId()));
			queryList.add(new QueryKeyValue("userId",cmsGroupUser.getUserId()));
			int count = cmsGroupUserDao.getGenCount(queryList);
			if(count == 0){
				cmsGroupUser.setCreater(cmsUser.getUsername());
				cmsGroupUser.setCreateTime(new Date());
				cmsGroupUserDao.save(cmsGroupUser);
				res.setCode(1);
				res.setMessage("成功增加：" + cmsGroupUser.getUserName() + " 到ID为"+cmsGroupUser.getGroupId()+"的权限组。");
			}else{
				res.setCode(-2);
				res.setMessage("ID为"+cmsGroupUser.getGroupId()+"的权限组已存在"+cmsGroupUser.getUserName()+"，添加失败。");
			}
		}else{
			res.setCode(-1);
			res.setMessage("参数不完整，新增失败。");
		}
		return res;
		
	}

	@Override
	public Response<String> addPopedomGroup(CmsPopedomGroup cmsPopedomGroup,CmsUser cmsUser) {

		Response<String> res = new Response<String>();
		if(null == cmsUser){
			res.setCode(-1);
			res.setMessage("SESSION失效，请重新登陆。");
			return res;
		}
		if(null != cmsPopedomGroup && !StringUtil.isEmpty(cmsPopedomGroup.getGroupName())){
			cmsPopedomGroup.setCreater(cmsUser.getUsername());
			cmsPopedomGroup.setCreateTime(new Date());
			cmsPopedomGroupDao.save(cmsPopedomGroup);
			res.setCode(1);
			res.setMessage("成功增加权限组：" + cmsPopedomGroup.getGroupName() + "。");
		}else{
			res.setCode(-1);
			res.setMessage("权限组名称不能为空。");
		}
		return res;
		
	}

	@Override
	public Response<String> addPopedomToGroup(CmsPopedomGroup cmsPopedomGroup) {

		Response<String> res = new Response<String>();
		if(null != cmsPopedomGroup && null != cmsPopedomGroup.getId() && !StringUtil.isEmpty(cmsPopedomGroup.getGroupPopedom())){
			List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
			queryList.add(new QueryKeyValue("id",cmsPopedomGroup.getId()));
			List<QueryKeyValue> updateList = new ArrayList<QueryKeyValue>();
			updateList.add(new QueryKeyValue("groupPopedom",cmsPopedomGroup.getGroupPopedom()));
			int updateInfo = cmsPopedomGroupDao.updateGen(queryList, updateList);
			if(updateInfo > 0){
				res.setCode(1);
				res.setMessage("成功设置ID为：" + cmsPopedomGroup.getId() + "的权限组的权限。");
			}else{
				res.setCode(-2);
				res.setMessage("设置ID为：" + cmsPopedomGroup.getId() + "的权限组的权限时，返回值为： "+updateInfo+" ，设置失败。");
			}
		}else{
			res.setCode(-1);
			res.setMessage("ID或权限不能为空。");
		}
		return res;
		
	}

	@Override
	public Response<String> deleteGroupUser(CmsGroupUser cmsGroupUser) {

		Response<String> res = new Response<String>();
		if(null != cmsGroupUser && null != cmsGroupUser.getId()){
			int removeInfo = cmsGroupUserDao.remove(cmsGroupUser.getId());
			if(removeInfo == 0){
				res.setCode(-2);
				res.setMessage("没找到ID为 "+cmsGroupUser.getId()+" 的数据。");
			}else if(removeInfo == 1){
				res.setCode(1);
				res.setMessage("成功删除ID为：" + cmsGroupUser.getId() + " 的权限组成员。");
			}else{
				res.setCode(-2);
				res.setMessage("删除ID为：" + cmsGroupUser.getId() + " 的权限组成员时，返回值："+removeInfo+"，删除失败。");
			}
		}else{
			res.setCode(-1);
			res.setMessage("ID不能为空。");
		}
		return res;
		
	}

	@Override
	public Response<String> deletePopedomGroup(CmsPopedomGroup cmsPopedomGroup) {

		Response<String> res = new Response<String>();
		if(null != cmsPopedomGroup && null != cmsPopedomGroup.getId()){
			int removeInfo = cmsPopedomGroupDao.remove(cmsPopedomGroup.getId());
			if(removeInfo == 0){
				res.setCode(-2);
				res.setMessage("没找到ID为 "+cmsPopedomGroup.getId()+" 的权限组数据。");
			}else if(removeInfo == 1){
				List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
				queryList.add(new QueryKeyValue("groupId",cmsPopedomGroup.getId()));
				List<CmsGroupUser> cmsGroupUserList = cmsGroupUserDao.getGenList(queryList, null);
				for(CmsGroupUser cgu : cmsGroupUserList){
					cmsGroupUserDao.remove(cgu.getId());
				}
				res.setCode(1);
				res.setMessage("成功删除ID为:" + cmsPopedomGroup.getId() + "的权限组、及组内成员。");
			}else{
				res.setCode(-2);
				res.setMessage("删除ID为:" + cmsPopedomGroup.getId() + "的权限组时报错。");
			}
		}else{
			res.setCode(-1);
			res.setMessage("ID不能为空。");
		}
		return res;
		
	}

	@Override
	public Response<PageModel<CmsGroupUser>> getGroupUserByGroupId(CmsGroupUser cmsGroupUser, int pageNo, int pageSize) {

		if(null != cmsGroupUser && null != cmsGroupUser.getGroupId()){
			Response<PageModel<CmsGroupUser>> res = new Response<PageModel<CmsGroupUser>>();
			List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
			queryList.add(new QueryKeyValue("groupId",cmsGroupUser.getGroupId()));
			PageModel<CmsGroupUser> allCmsGroupUser = cmsGroupUserDao.getGenPage(queryList, null, pageNo, pageSize);
			res.setData(allCmsGroupUser);
			return res;
		}else{
			return null;
		}
		
	}

	@Override
	public Response<PageModel<CmsPopedomGroup>> queryPopedomGroup(int pageNo, int pageSize) {

		Response<PageModel<CmsPopedomGroup>> res = new Response<PageModel<CmsPopedomGroup>>();
		PageModel<CmsPopedomGroup> cmsPopedomGroupList = cmsPopedomGroupDao.getGenPage(null, null, pageNo, pageSize);
		res.setData(cmsPopedomGroupList);
		return res;
		
	}


	@Override
	public Response<String> updatePopedomGroup(CmsPopedomGroup cmsPopedomGroup) {

		Response<String> res = new Response<String>();
		if(null != cmsPopedomGroup && null != cmsPopedomGroup.getId() && !StringUtil.isEmpty(cmsPopedomGroup.getGroupName())){
			List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
			queryList.add(new QueryKeyValue("id",cmsPopedomGroup.getId()));
			List<QueryKeyValue> updateList = new ArrayList<QueryKeyValue>();
			updateList.add(new QueryKeyValue("groupName",cmsPopedomGroup.getGroupName()));
			int updateInfo = cmsPopedomGroupDao.updateGen(queryList, updateList);
			if(updateInfo > 0){
				res.setCode(1);
				res.setMessage("成功修改ID为：" + cmsPopedomGroup.getId() + " 的权限组名称为：" + cmsPopedomGroup.getGroupName() + "。");
			}else{
				res.setCode(-2);
				res.setMessage("修改ID为：" + cmsPopedomGroup.getId() + " 的权限组名称为：" + cmsPopedomGroup.getGroupName() + "时，返回值为： "+updateInfo+" ，修改失败。");
			}
		}else{
			res.setCode(-1);
			res.setMessage("ID或组名不能为空。");
		}
		return res;
		
	}
	
	@Override
	public String getPopedomTree(CmsPopedomGroup cmsPopedomGroup) {
		
		if(null == cmsPopedomGroup || null == cmsPopedomGroup.getId()){
			return "{text: '传入ID不能为空。',checked:false,id:'-1',leaf:true}";
		}
		CmsPopedomGroup cpgm = cmsPopedomGroupDao.get(cmsPopedomGroup.getId());
		String quanxianStr = cpgm.getGroupPopedom();
		List<String> quanxianList = new ArrayList<String>();
		if(null != quanxianStr){
			String[] quanxianStrs = quanxianStr.split(",");
			for(int i =0;i<quanxianStrs.length;i++){
				quanxianList.add(quanxianStrs[i]);
			}
		}
		return getJonsTree(quanxianList);
		
	}
	
	private String getJonsTree(List<String> quanxian){
		
		List<OrderByBean> orderList = new ArrayList<OrderByBean>();
		orderList.add(new OrderByBean("sort",Constants.HqlSign.ASC));
		List<CmsMenu> allMenu = getCmsMenuListByFatherId(0, orderList);
		StringBuffer sb = new StringBuffer();
		for(CmsMenu cm : allMenu){
			List<CmsMenu> allChildrenMenu = getCmsMenuListByFatherId(cm.getId(), orderList);
			if(null != allChildrenMenu && allChildrenMenu.size() > 0){
				StringBuffer sbc = new StringBuffer();
				for(CmsMenu cmc : allChildrenMenu){
					List<CmsMenu> allChildrenMenus = getCmsMenuListByFatherId(cmc.getId(), orderList);
					if(null != allChildrenMenus){
						if(allChildrenMenus.size() > 0){
							StringBuffer sbcs = new StringBuffer();
							for(CmsMenu cmcs : allChildrenMenus){
								if(cmcs.getMenuType() == 1){
									List<CmsMenu> allGrandsonMenu = getCmsMenuListByFatherId(cmcs.getId(), orderList);
									if(null != allGrandsonMenu){
										StringBuffer sbg = new StringBuffer();
										if(allGrandsonMenu.size() == 0){
											if(quanxian.contains(cmcs.getId()+"")){
												sbcs.append(",{text: '"+cmcs.getMenuName()+"  √ ',checked:true,id:'"+cmcs.getId()+"',leaf:true}");
											}else{
												sbcs.append(",{text: '"+cmcs.getMenuName()+"  √ ',checked:false,id:'"+cmcs.getId()+"',leaf:true}");
											}
										}else{
											for(CmsMenu cmg : allGrandsonMenu){
												if(quanxian.contains(cmg.getId()+"")){
													sbg.append(",{text: '"+cmg.getMenuName()+"  χ ',checked:true,id:'"+cmg.getId()+"',leaf:true}");
												}else{
													sbg.append(",{text: '"+cmg.getMenuName()+"  χ ',checked:false,id:'"+cmg.getId()+"',leaf:true}");
												}
											}
											if(quanxian.contains(cmcs.getId()+"")){
												sbcs.append(",{text: '"+cmcs.getMenuName()+"  √ ',expanded:true,checked:true,id:'"+cmcs.getId()+"',children:["+sbg.toString().substring(1)+"]}");
											}else{
												sbcs.append(",{text: '"+cmcs.getMenuName()+"  √ ',expanded:true,checked:false,id:'"+cmcs.getId()+"',children:["+sbg.toString().substring(1)+"]}");
											}
										}
									}
								}else{
									if(quanxian.contains(cmcs.getId()+"")){
										sbcs.append(",{text: '"+cmcs.getMenuName()+"  χ ',checked:true,id:'"+cmcs.getId()+"',leaf:true}");
									}else{
										sbcs.append(",{text: '"+cmcs.getMenuName()+"  χ ',checked:false,id:'"+cmcs.getId()+"',leaf:true}");
									}
								}
							}
							if(quanxian.contains(cmc.getId()+"")){
								sbc.append(",{text: '"+cmc.getMenuName()+"  √ ',expanded:true,checked:true,id:'"+cmc.getId()+"',children:["+sbcs.toString().substring(1)+"]}");
							}else{
								sbc.append(",{text: '"+cmc.getMenuName()+"  √ ',expanded:true,checked:false,id:'"+cmc.getId()+"',children:["+sbcs.toString().substring(1)+"]}");
							}
						}
						if(allChildrenMenus.size() == 0){
							if(quanxian.contains(cmc.getId()+"")){
								sbc.append(",{text: '"+cmc.getMenuName()+"  √ ',checked:true,id:'"+cmc.getId()+"',leaf:true}");
							}else{
								sbc.append(",{text: '"+cmc.getMenuName()+"  √ ',checked:false,id:'"+cmc.getId()+"',leaf:true}");
							}
						}
					}
				}
				if(quanxian.contains(cm.getId()+"")){
					sb.append(",{id:'"+cm.getId()+"',text:'"+cm.getMenuName()+"  √ ',expanded:true,checked:true,leaf:false,children:["+sbc.toString().substring(1)+"]}");
				}else{
					sb.append(",{id:'"+cm.getId()+"',text:'"+cm.getMenuName()+"  √ ',expanded:true,checked:false,leaf:false,children:["+sbc.toString().substring(1)+"]}");
				}
			}else{
				if(quanxian.contains(cm.getId()+"")){
					sb.append(",{text: '"+cm.getMenuName()+"  √ ',checked:true,id:'"+cm.getId()+"',leaf:true}");
				}else{
					sb.append(",{text: '"+cm.getMenuName()+"  √ ',checked:false,id:'"+cm.getId()+"',leaf:true}");
				}
			}
		}
		return sb.toString().substring(1);
	}
	
	private List<CmsMenu> getCmsMenuListByFatherId(int fatherId,List<OrderByBean> orderList){
		
		List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
		queryList.add(new QueryKeyValue("fatherId",fatherId));
		return cmsMenuDao.getGenList(queryList, orderList);
		
	}

	public ICmsPopedomGroupDao getCmsPopedomGroupDao() {
		return cmsPopedomGroupDao;
	}
	public void setCmsPopedomGroupDao(ICmsPopedomGroupDao cmsPopedomGroupDao) {
		this.cmsPopedomGroupDao = cmsPopedomGroupDao;
	}
	public ICmsGroupUserDao getCmsGroupUserDao() {
		return cmsGroupUserDao;
	}
	public void setCmsGroupUserDao(ICmsGroupUserDao cmsGroupUserDao) {
		this.cmsGroupUserDao = cmsGroupUserDao;
	}
	public ICmsMenuDao getCmsMenuDao() {
		return cmsMenuDao;
	}
	public void setCmsMenuDao(ICmsMenuDao cmsMenuDao) {
		this.cmsMenuDao = cmsMenuDao;
	}
}
