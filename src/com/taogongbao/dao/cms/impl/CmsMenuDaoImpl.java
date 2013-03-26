package com.taogongbao.dao.cms.impl;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.cms.ICmsMenuDao;
import com.taogongbao.entity.cms.CmsMenu;

public class CmsMenuDaoImpl extends GenericDaoImpl<CmsMenu, Integer> implements
		ICmsMenuDao {

	public CmsMenuDaoImpl(Class<CmsMenu> persistentClass) {
		super(persistentClass);
	}

}
