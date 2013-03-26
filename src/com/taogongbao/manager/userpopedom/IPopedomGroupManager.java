package com.taogongbao.manager.userpopedom;

import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.utils.parse.Response;
import com.taogongbao.entity.cms.CmsGroupUser;
import com.taogongbao.entity.cms.CmsPopedomGroup;
import com.taogongbao.entity.cms.CmsUser;

public interface IPopedomGroupManager {
	
	public Response<String> addPopedomToGroup(CmsPopedomGroup cmsPopedomGroup);

	public Response<String> deletePopedomGroup(CmsPopedomGroup cmsPopedomGroup);
	
	public Response<String> addPopedomGroup(CmsPopedomGroup cmsPopedomGroup,CmsUser cmsUser);
	
	public Response<String> updatePopedomGroup(CmsPopedomGroup cmsPopedomGroup);
	
	public Response<PageModel<CmsPopedomGroup>> queryPopedomGroup(int pageNo, int pageSize);
	
	public Response<String> deleteGroupUser(CmsGroupUser cmsGroupUser);
	
	public Response<String> addGroupUser(CmsGroupUser cmsGroupUser,CmsUser cmsUser);
	
	public Response<PageModel<CmsGroupUser>> getGroupUserByGroupId(CmsGroupUser cmsGroupUser, int pageNo, int pageSize);
	
	public String getPopedomTree(CmsPopedomGroup cmsPopedomGroup);
	
}
