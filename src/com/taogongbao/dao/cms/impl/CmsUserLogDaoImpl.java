package com.taogongbao.dao.cms.impl;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.cms.ICmsUserLogDao;
import com.taogongbao.entity.cms.CmsUserLog;

public class CmsUserLogDaoImpl extends GenericDaoImpl<CmsUserLog, Integer> implements
		ICmsUserLogDao {

	public CmsUserLogDaoImpl(Class<CmsUserLog> persistentClass) {
		super(persistentClass);
	}

}
