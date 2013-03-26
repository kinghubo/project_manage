package com.taogongbao.dao.cms.impl;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.cms.ICmsPopedomGroupDao;
import com.taogongbao.entity.cms.CmsPopedomGroup;

public class CmsPopedomGroupDaoImpl extends GenericDaoImpl<CmsPopedomGroup, Integer> implements
		ICmsPopedomGroupDao {

	public CmsPopedomGroupDaoImpl(Class<CmsPopedomGroup> persistentClass) {
		super(persistentClass);
	}

}
