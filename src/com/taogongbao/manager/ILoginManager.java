package com.taogongbao.manager;

import javax.servlet.http.HttpSession;
import com.taogongbao.entity.cms.CmsUser;

public interface ILoginManager {
	
	public CmsUser login(String username, String password);
	
	public String queryPopedomTree(String userId,HttpSession session);

}
