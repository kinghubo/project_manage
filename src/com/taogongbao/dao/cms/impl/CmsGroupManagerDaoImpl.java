package com.taogongbao.dao.cms.impl;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.cms.ICmsGroupManagerDao;
import com.taogongbao.entity.cms.CmsGroupManager;

public class CmsGroupManagerDaoImpl extends GenericDaoImpl<CmsGroupManager, Integer>
		implements ICmsGroupManagerDao {

	public CmsGroupManagerDaoImpl(Class<CmsGroupManager> persistentClass) {
		super(persistentClass);
	}

}
