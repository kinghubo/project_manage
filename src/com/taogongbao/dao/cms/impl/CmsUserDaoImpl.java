package com.taogongbao.dao.cms.impl;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.cms.ICmsUserDao;
import com.taogongbao.entity.cms.CmsUser;

/**
 * 
 *
 * @createTime: Mar 31, 2011 10:45:05 AM
 * @author: <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
 * @changesSum: 
 * 
 */
public class CmsUserDaoImpl extends GenericDaoImpl<CmsUser, String> implements ICmsUserDao{

	public CmsUserDaoImpl(Class<CmsUser> persistentClass) {
		super(persistentClass);
	}
}


