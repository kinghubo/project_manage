package com.taogongbao.dao.cms.impl;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.cms.ICmsGroupUserDao;
import com.taogongbao.entity.cms.CmsGroupUser;

public class CmsGroupUserDaoImpl extends GenericDaoImpl<CmsGroupUser, Integer> implements
		ICmsGroupUserDao {

	public CmsGroupUserDaoImpl(Class<CmsGroupUser> persistentClass) {
		super(persistentClass);
	}

}
