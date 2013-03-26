package com.taogongbao.dao.project.impl;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.project.IPmProjectInfoDao;
import com.taogongbao.entity.project.PmProjectInfo;

public class PmProjectInfoDaoImpl extends GenericDaoImpl<PmProjectInfo, Integer> implements
		IPmProjectInfoDao {

	public PmProjectInfoDaoImpl(Class<PmProjectInfo> persistentClass) {
		super(persistentClass);
	}

}
