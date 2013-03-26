package com.taogongbao.dao.cms.impl;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.cms.ICmsGroupDao;
import com.taogongbao.entity.cms.CmsGroup;

public class CmsGroupDaoImpl extends GenericDaoImpl<CmsGroup, Integer>
		implements ICmsGroupDao {

	public CmsGroupDaoImpl(Class<CmsGroup> persistentClass) {
		super(persistentClass);
	}

}
