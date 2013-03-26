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
import com.taogongbao.dao.cms.ICmsMenuDao;
import com.taogongbao.entity.cms.CmsMenu;
import com.taogongbao.entity.cms.CmsUser;
import com.taogongbao.manager.userpopedom.IMenuManager;

public class MenuManagerImpl implements IMenuManager {
	
	private ICmsMenuDao cmsMenuDao;

	@Override
	public Response<String> addMenu(CmsMenu cmsMenu,CmsUser cmsUser) {
		
		Response<String> res = new Response<String>();
		if(cmsUser == null){
			res.setCode(-1);
			res.setMessage("SESSION失效，请重新登陆。");
			return res;
		}
		if(null == cmsMenu || StringUtil.isEmpty(cmsMenu.getMenuName()) || (cmsMenu.getMenuType() != 1 && cmsMenu.getMenuType() != 2)
				|| null == cmsMenu.getSort()){
			res.setCode(-1);
			res.setMessage("参数没有填写完整，新增失败。");
			return res;
		}
		if(null == cmsMenu.getFatherId()){
			cmsMenu.setFatherId(0);
		}
		cmsMenu.setCreater(cmsUser.getUsername());
		cmsMenu.setCreateTime(new Date());
		cmsMenuDao.save(cmsMenu);
		res.setCode(1);
		res.setMessage("成功增加" + cmsMenu.getMenuName() + "菜单(按钮)。");
		return res;
		
	}

	@Override
	public Response<String> deleteMenuById(int id) {
		
		Response<String> res = new Response<String>();
		if(StringUtil.isEmpty(id)){
			res.setCode(-1);
			res.setMessage("ID不能为空。");
			return res;
		}
		List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
		queryList.add(new QueryKeyValue("fatherId",id));
		int count = cmsMenuDao.getGenCount(queryList);
		if(count == 0){
			cmsMenuDao.remove(id);
			res.setCode(1);
			res.setMessage("删除成功。");
		}else{
			res.setCode(-2);
			res.setMessage("此菜单有  "+count+" 个子菜单，请先删除其子菜单，操作失败。");
		}
		return res;
	}

	@Override
	public String getMenuTree() {
		
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
											sbg.append(",{text: '"+cmcs.getMenuName()+"',id:'"+cmcs.getId()+"',leaf:true}");
											sbcs.append(",{text: '"+cmcs.getMenuName()+"',id:'"+cmcs.getId()+"',children:["+sbg.toString().substring(1)+"]}");
										}else{
											for(CmsMenu cmg : allGrandsonMenu){
												sbg.append(",{text: '"+cmg.getMenuName()+"',id:'"+cmg.getId()+"',leaf:true}");
											}
											sbcs.append(",{text: '"+cmcs.getMenuName()+"',id:'"+cmcs.getId()+"',children:["+sbg.toString().substring(1)+"]}");
										}
									}
								}else{
									sbcs.append(",{text: '"+cmcs.getMenuName()+"',id:'"+cmcs.getId()+"',leaf:true}");
								}
							}
							sbc.append(",{text: '"+cmc.getMenuName()+"',id:'"+cmc.getId()+"',children:["+sbcs.toString().substring(1)+"]}");
						}
						if(allChildrenMenus.size() == 0){
							StringBuffer sbcs = new StringBuffer();
							sbcs.append(",{text: '"+cmc.getMenuName()+"',id:'"+cmc.getId()+"',leaf:true}");
							sbc.append(",{text: '"+cmc.getMenuName()+"',id:'"+cmc.getId()+"',children:["+sbcs.toString().substring(1)+"]}");
						}
					}
				}
				sb.append(",{id:'"+cm.getId()+"',text:'"+cm.getMenuName()+"',children:["+sbc.toString().substring(1)+"]}");
			}else{
				sb.append(",{text: '"+cm.getMenuName()+"',id:'"+cm.getId()+"',leaf:true}");
			}
		}
		return sb.toString().substring(1);
		
	}

	private List<CmsMenu> getCmsMenuListByFatherId(int fatherId,List<OrderByBean> orderList){
		
		List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
		queryList.add(new QueryKeyValue("fatherId",fatherId));
		return cmsMenuDao.getGenList(queryList, orderList);
		
	}
	
	@Override
	public List<CmsMenu> queryMenu(int fatherId) {

		List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
		queryList.add(new QueryKeyValue("fatherId",fatherId));
		List<OrderByBean> orderList = new ArrayList<OrderByBean>();
		orderList.add(new OrderByBean("sort",Constants.HqlSign.ASC));
		return cmsMenuDao.getGenList(queryList, orderList);
		
	}

	@Override
	public Response<String> updateMenu(CmsMenu cmsMenu) {

		Response<String> res = new Response<String>();
		if(null != cmsMenu && null != cmsMenu.getId() && !StringUtil.isEmpty(cmsMenu.getMenuName()) 
				&& null != cmsMenu.getMenuType() && null != cmsMenu.getFatherId() && null != cmsMenu.getSort()){
			List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
			queryList.add(new QueryKeyValue("id",cmsMenu.getId()));
			List<QueryKeyValue> updateList = new ArrayList<QueryKeyValue>();
			updateList.add(new QueryKeyValue("menuName",cmsMenu.getMenuName()));
			updateList.add(new QueryKeyValue("menuUrl",cmsMenu.getMenuUrl()));
			updateList.add(new QueryKeyValue("fatherId",cmsMenu.getFatherId()));
			updateList.add(new QueryKeyValue("menuType",cmsMenu.getMenuType()));
			updateList.add(new QueryKeyValue("sort",cmsMenu.getSort()));
			int updateInfo = cmsMenuDao.updateGen(queryList, updateList);
			if(updateInfo == 1){
				res.setCode(1);
				res.setMessage("成功修改ID为：" + cmsMenu.getId() + "的菜单(按钮)为" + cmsMenu.getMenuName() + "。");
			}else{
				res.setCode(-2);
				res.setMessage("修改ID为：" + cmsMenu.getId() + "的菜单(按钮)时，返回值为： "+updateInfo+" ，修改失败。");
			}
		}else{
			res.setCode(-1);
			res.setMessage("参数传递不完整，修改失败。");
		}
		return res;
	}

	public ICmsMenuDao getCmsMenuDao() {
		return cmsMenuDao;
	}
	public void setCmsMenuDao(ICmsMenuDao cmsMenuDao) {
		this.cmsMenuDao = cmsMenuDao;
	}

	@Override
	public Response<PageModel<CmsMenu>> queryMenu(int fatherId, int pageSize,int pageNo) {
		
		Response<PageModel<CmsMenu>> res = new Response<PageModel<CmsMenu>>();
		List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
		queryList.add(new QueryKeyValue("fatherId",fatherId));
		List<OrderByBean> orderList = new ArrayList<OrderByBean>();
		orderList.add(new OrderByBean("sort",Constants.HqlSign.ASC));
		PageModel<CmsMenu> allCmsMenu = cmsMenuDao.getGenPage(queryList, orderList, pageSize, pageNo);
		res.setData(allCmsMenu);
		return res;
		
	}

	@Override
	public String getMenuName(int id) {
		CmsMenu cmsMenu = cmsMenuDao.get(id);
		return null==cmsMenu?null:cmsMenu.getMenuName();
	}

}
