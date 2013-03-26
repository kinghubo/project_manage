package com.taogongbao.dao.project.impl;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.project.IPmLogDao;
import com.taogongbao.entity.project.PmLog;

public class PmLogDaoImpl extends GenericDaoImpl<PmLog, Integer> implements
		IPmLogDao {

	public PmLogDaoImpl(Class<PmLog> persistentClass) {
		super(persistentClass);
	}

}
