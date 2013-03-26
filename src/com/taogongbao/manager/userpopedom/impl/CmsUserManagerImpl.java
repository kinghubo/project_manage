package com.taogongbao.manager.userpopedom.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.feinno.baigong.common.tools.StringUtil;
import com.taogongbao.common.Crypt;
import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.entity.QueryKeyValue;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.dao.cms.ICmsGroupUserDao;
import com.taogongbao.dao.cms.ICmsUserDao;
import com.taogongbao.entity.cms.CmsGroupUser;
import com.taogongbao.entity.cms.CmsUser;
import com.taogongbao.manager.userpopedom.ICmsUserManager;


public class CmsUserManagerImpl implements ICmsUserManager{
	
	private ICmsUserDao cmsUserDao;
	private ICmsGroupUserDao cmsGroupUserDao;

	@Override
	public Response<String> addCmsUser(CmsUser cmsUser) {
		
		Response<String> res = new Response<String>();
		if(cmsUser == null || StringUtil.isEmpty(cmsUser.getUsername()) || StringUtil.isEmpty(cmsUser.getPassword())){
			res.setCode(-1);
			res.setMessage("用户名或密码不能为空。");
		}else{
			List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
			queryList.add(new QueryKeyValue("username",cmsUser.getUsername()));
			int isHave = cmsUserDao.getGenCount(queryList);
			if(isHave == 0){
				cmsUser.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				cmsUser.setCreatetime(new Timestamp(new Date().getTime()));
				try {
					cmsUser.setPassword(Crypt.StrEnCrypt(cmsUser.getPassword()));
					cmsUserDao.save(cmsUser);
					res.setCode(1);
					res.setMessage("成功添加用户名为"+cmsUser.getUsername()+"的CMS管理员。");
				} catch (Exception e) {
					res.setCode(-2);
					res.setMessage("密码加密时报错，添加失败。");
					e.printStackTrace();
				}
			}else{
				res.setCode(-2);
				res.setMessage("用户名"+cmsUser.getUsername()+"已存在，添加失败。");
			}
		}
		return res;
		
	}
	
	@Override
	public Response<String> deleteCmsUsers(String ids) {
		
		Response<String> res = new Response<String>();
		if(StringUtil.isEmpty(ids)){
			res.setCode(-1);
			res.setMessage("用户ID不能为空。");
		}else{
			StringBuffer message = new StringBuffer();
			String[] id = ids.split(",");
			for(int i=0;i<id.length;i++){
				List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
				queryList.add(new QueryKeyValue("userId",id[i]));
				List<CmsGroupUser> groupRes = cmsGroupUserDao.getGenList(queryList, null);
				if(null != groupRes && groupRes.size() > 0){
					for(CmsGroupUser cgu : groupRes){
						cmsGroupUserDao.remove(cgu);
					}
				}
				int removeInfo = cmsUserDao.remove(id[i]);
				if(removeInfo == 0){
					message.append("无ID为 "+id[i]+" 的数据。");
				}else if(removeInfo == -1){
					message.append("删除ID为 "+id[i]+" 的数据时，系统报错。");
				}else{
					message.append("成功删除ID为 "+id[i]+" 的数据。");
				}
			}
			res.setCode(1);
			res.setMessage(message.toString());
		}
		return res;
		
	}
	
	@Override
	public Response<List<CmsUser>> queryCmsUsers() {
		
		Response<List<CmsUser>> res = new Response<List<CmsUser>>();
		res.setData(cmsUserDao.getAll());
		return res;
	}
	
    public Response<PageModel<CmsUser>> queryCmsUsers(String userName,int userType,int offset,int length){

        List<QueryKeyValue> list = new ArrayList<QueryKeyValue>();
        if(!StringUtil.isEmpty(userName))
            list.add(new QueryKeyValue("name","%"+userName+"%","like"));
        
        if(userType>0)
            list.add(new QueryKeyValue("userType",userType,"="));
        
        PageModel<CmsUser> pageModel = new PageModel<CmsUser>();
        pageModel.setDataList(cmsUserDao.getGenList(list, null, offset, length));
        pageModel.setTotalRows(cmsUserDao.getGenCount(list));

        Response<PageModel<CmsUser>> res = new Response<PageModel<CmsUser>>();
        res.setData(pageModel);
        res.getData().setLimit(length);
        
        
        return res;
    }
    
	@Override
	public Response<String> updatePassword(CmsUser cmsUser,String nowPassword,String newPassword,String confirmPassword) {
		
		Response<String> res = new Response<String>();
		if(null == cmsUser){
			res.setCode(-1);
			res.setMessage("session已失效，请重新登录。");
		}else if(StringUtil.isEmpty(nowPassword)){
			res.setCode(-1);
			res.setMessage("原密码为空，请填写原密码。");
		}else if(StringUtil.isEmpty(newPassword)){
			res.setCode(-1);
			res.setMessage("新密码为空，请填写新密码。");
		}else if(StringUtil.isEmpty(confirmPassword)){
			res.setCode(-1);
			res.setMessage("确认密码为空，请确认密码。");
		}else if(!newPassword.equals(confirmPassword)){
			res.setCode(-1);
			res.setMessage("两次密码不相同，请重新填写。");
		}else{
			CmsUser user = cmsUserDao.get(cmsUser.getId());
			try {
				String oldPassword = Crypt.StrEnCrypt(nowPassword);
				if(oldPassword.equals(user.getPassword())){
					List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
					queryList.add(new QueryKeyValue("id",cmsUser.getId()));
					List<QueryKeyValue> updateList = new ArrayList<QueryKeyValue>();
					updateList.add(new QueryKeyValue("password",Crypt.StrEnCrypt(newPassword)));
					int updateRes = cmsUserDao.updateGen(queryList, updateList);
					if(updateRes == 1){
						res.setCode(1);
						res.setMessage("成功修改登录密码。");
					}else{
						res.setCode(-2);
						res.setMessage("修改登录密码返回值为： "+updateRes+" ，修改失败。");
					}
				}else{
					res.setCode(-2);
					res.setMessage("原密码填写错误，修改失败。");
				}
			} catch (Exception e) {
				res.setCode(-2);
				res.setMessage("系统报错(密码加密或修改密码过程中)，请联系管理员。");
				e.printStackTrace();
			}
		}
		return res;
		
	}

	@Override
	public Response<String> updateCmsUserPassword(CmsUser cmsUser) {
		
		Response<String> res = new Response<String>();
		if(null == cmsUser || StringUtil.isEmpty(cmsUser.getId()) || StringUtil.isEmpty(cmsUser.getPassword())){
			res.setCode(-1);
			res.setMessage("用户ID或密码传输错误。");
		}else{
			List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
			queryList.add(new QueryKeyValue("id",cmsUser.getId()));
			List<QueryKeyValue> updateList = new ArrayList<QueryKeyValue>();
			try {
				updateList.add(new QueryKeyValue("password",Crypt.StrEnCrypt(cmsUser.getPassword())));
				int updateInfo = cmsUserDao.updateGen(queryList, updateList);
				if(updateInfo > 0){
					res.setCode(1);
					res.setMessage("成功修改ID为"+cmsUser.getId()+"的用户的登录密码。");
				}else{
					res.setCode(-2);
					res.setMessage("修改ID为"+cmsUser.getId()+"的用户登录密码时，返回值为： "+updateInfo+" ，修改失败。");
				}
			} catch (Exception e) {
				res.setCode(-2);
				res.setMessage("修改ID为"+cmsUser.getId()+"的用户登录密码时，系统报错(密码加密中)，请联系管理员。");
				e.printStackTrace();
			}
		}
		return res;
		
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

    @Override
    public Response<String> updateCmsUser(CmsUser cmsUser) {
        Response<String> res = new Response<String>();
        if (null == cmsUser || StringUtil.isEmpty(cmsUser.getId())) {
            res.setCode(-1);
            res.setMessage("对象或用户ID传输错误。");
        }
        else {
            List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
            queryList.add(new QueryKeyValue("id", cmsUser.getId()));
            
            List<QueryKeyValue> updateList = new ArrayList<QueryKeyValue>();
            try {
                if(!StringUtil.isEmpty(cmsUser.getPassword()))
                    updateList.add(new QueryKeyValue("password", Crypt.StrEnCrypt(cmsUser.getPassword())));
                
                int updateInfo = cmsUserDao.updateGen(queryList, updateList);
                if (updateInfo > 0) {
                    res.setCode(1);
                    res.setMessage("成功修改ID为" + cmsUser.getId() + "的用户信息。");
                }
                else {
                    res.setCode(-2);
                    res.setMessage("修改ID为" + cmsUser.getId() + "的用户信息时，返回值为： " + updateInfo + " ，修改失败。");
                }
            }
            catch (Exception e) {
                res.setCode(-2);
                res.setMessage("修改ID为" + cmsUser.getId() + "的用户信息时，系统报错(密码加密中)，请联系管理员。");
                e.printStackTrace();
            }
        }
        return res;
    }
    
    public String getCmsUserByUserName(String userName){
        String hql = " from CmsUser where username=?";
        List<CmsUser> vos = cmsUserDao.findPageAll(hql, 0, 20, userName);
        if(vos != null && vos.size() > 0)
            return vos.get(0).getId();
        else 
            return null;
    }
}
