package com.taogongbao.dao.pu.impl;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.pu.ISysParaDao;
import com.taogongbao.entity.pu.SysPara;

public class SysParaDaoImpl extends GenericDaoImpl<SysPara, Integer> implements
		ISysParaDao {

	public SysParaDaoImpl(Class<SysPara> persistentClass) {
		super(persistentClass);
	}

}
