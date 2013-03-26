package com.taogongbao.dao.project.impl;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.project.IPmResourceDao;
import com.taogongbao.entity.project.PmResource;

public class PmResourceDaoImpl extends GenericDaoImpl<PmResource, Integer> implements
		IPmResourceDao {

	public PmResourceDaoImpl(Class<PmResource> persistentClass) {
		super(persistentClass);
	}

}
