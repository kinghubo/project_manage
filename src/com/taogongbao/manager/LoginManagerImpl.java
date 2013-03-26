package com.taogongbao.manager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.feinno.baigong.common.tools.StringUtil;
import com.taogongbao.common.constant.Constants;
import com.taogongbao.common.constant.SessionConstants;
import com.taogongbao.common.entity.OrderByBean;
import com.taogongbao.common.entity.QueryKeyValue;
import com.taogongbao.dao.cms.ICmsGroupUserDao;
import com.taogongbao.dao.cms.ICmsMenuDao;
import com.taogongbao.dao.cms.ICmsPopedomGroupDao;
import com.taogongbao.dao.cms.ICmsUserDao;
import com.taogongbao.entity.cms.CmsGroupUser;
import com.taogongbao.entity.cms.CmsMenu;
import com.taogongbao.entity.cms.CmsPopedomGroup;
import com.taogongbao.entity.cms.CmsUser;

public class LoginManagerImpl implements ILoginManager {
	
	private ICmsUserDao cmsUserDao;
	private ICmsMenuDao cmsMenuDao;
	private ICmsGroupUserDao cmsGroupUserDao;
	private ICmsPopedomGroupDao cmsPopedomGroupDao;
	
	@Override
	public CmsUser login(String username, String password) {
		
		List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
		queryList.add(new QueryKeyValue("username", username));
		queryList.add(new QueryKeyValue("password", password));
		List<CmsUser> cmsUserList = cmsUserDao.getGenList(queryList, null);
		if(null == cmsUserList || cmsUserList.size() == 0){
			return null;
		}else{
			return cmsUserList.get(0);
		}
		
	}
	
	@Override
	public String queryPopedomTree(String userId,HttpSession session) {
		
		List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
		queryList.add(new QueryKeyValue("userId",userId));
		List<CmsGroupUser> resCmsGroupUser = cmsGroupUserDao.getGenList(queryList, null);	//根据用户对象获得组ID
		if(null != resCmsGroupUser && resCmsGroupUser.size() > 0){
			StringBuffer quanxian = new StringBuffer();
			for(CmsGroupUser cgu : resCmsGroupUser){
				CmsPopedomGroup resCmsPopedomGroup  = cmsPopedomGroupDao.get(cgu.getGroupId());	 //根据组ID获得组信息
				if(null != resCmsPopedomGroup && !StringUtil.isEmpty(resCmsPopedomGroup.getGroupPopedom())){
					quanxian.append("," +resCmsPopedomGroup.getGroupPopedom());
				}
			}
			if(!"".equals(quanxian.toString())){
				String popedom = quanxian.toString().substring(1);
				String[] quanxians = popedom.split(",");
				List<String> quanxianList = new ArrayList<String>();
				for(int i = 0;i<quanxians.length;i++){
					quanxianList.add(quanxians[i]);
				}
				session.setAttribute(SessionConstants.QUANXIAN_LIST, quanxianList);
				return getJonsTree(quanxianList);
			}else{
				return "{text: '无权限,请联系管理员!',attributes:{url:''},id:'-99',leaf:true}";
			}
		}else{
			return "{text: '无权限,请联系管理员!',attributes:{url:''},id:'-99',leaf:true}";
		}
		
	}
	
	private String getJonsTree(List<String> quanxian){
		
		List<OrderByBean> orderList = new ArrayList<OrderByBean>();
		orderList.add(new OrderByBean("sort",Constants.HqlSign.ASC));
		List<CmsMenu> allMenu = getCmsMenuListByFatherId(0, orderList);
		StringBuffer sb = new StringBuffer();
		for(CmsMenu cm : allMenu){
			if(quanxian.contains(cm.getId()+"")){
				StringBuffer sbc = new StringBuffer();
				List<CmsMenu> allChildrenMenu = getCmsMenuListByFatherId(cm.getId(), orderList);
				for(CmsMenu cmc : allChildrenMenu){
					if(quanxian.contains(cmc.getId()+"")){
						List<CmsMenu> allGrandsonMenu = getCmsMenuListByFatherId(cmc.getId(), orderList);
						if(null != allGrandsonMenu && allGrandsonMenu.size() > 0 && allGrandsonMenu.get(0).getMenuType() == 1){
							StringBuffer sbg = new StringBuffer();
							for(CmsMenu cmg : allGrandsonMenu){
								if(quanxian.contains(cmg.getId()+"")){
									sbg.append(",{text: '"+cmg.getMenuName()+"',attributes:{url:'"+cmg.getMenuUrl()+"'},id:'"+cmg.getId()+"',leaf:true}");
								}
							}
							if(sbg.length() > 1){
								sbc.append(",{text: '"+cmc.getMenuName()+"',id:'"+cmc.getId()+"',children:["+sbg.toString().substring(1)+"],iconCls: 'feed-icon',cls:'feed',leaf:false}");
							}
						}else{
							sbc.append(",{text: '"+cmc.getMenuName()+"',attributes:{url:'"+cmc.getMenuUrl()+"'},id:'"+cmc.getId()+"',iconCls: 'feed-icon',cls:'feed',leaf:true}");
						}
					}
				}
				if(sbc.length() > 1){
					sb.append(",{id:'"+cm.getId()+"',text:'"+cm.getMenuName()+"',leaf:false,children:["+sbc.toString().substring(1)+"]}");
				}
			}
		}
		if(sb.length() > 1){
			return sb.toString().substring(1);
		}else{
			return "{text: '无权限,请联系管理员!',attributes:{url:''},id:'-99',leaf:true}";
		}
	}
	
	private List<CmsMenu> getCmsMenuListByFatherId(int fatherId,List<OrderByBean> orderList){
		List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
		queryList.add(new QueryKeyValue("fatherId",fatherId));
		List<CmsMenu> allMenu = new ArrayList<CmsMenu>();
		try {
			allMenu = cmsMenuDao.getGenList(queryList, orderList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allMenu;
	}


	public ICmsUserDao getCmsUserDao() {
		return cmsUserDao;
	}
	public void setCmsUserDao(ICmsUserDao cmsUserDao) {
		this.cmsUserDao = cmsUserDao;
	}
	public ICmsGroupUserDao getCmsGroupUserDao() {
		return cmsGroupUserDao;
	}
	public void setCmsGroupUserDao(ICmsGroupUserDao cmsGroupUserDao) {
		this.cmsGroupUserDao = cmsGroupUserDao;
	}
	public ICmsPopedomGroupDao getCmsPopedomGroupDao() {
		return cmsPopedomGroupDao;
	}
	public void setCmsPopedomGroupDao(ICmsPopedomGroupDao cmsPopedomGroupDao) {
		this.cmsPopedomGroupDao = cmsPopedomGroupDao;
	}
	public ICmsMenuDao getCmsMenuDao() {
		return cmsMenuDao;
	}
	public void setCmsMenuDao(ICmsMenuDao cmsMenuDao) {
		this.cmsMenuDao = cmsMenuDao;
	}
}
