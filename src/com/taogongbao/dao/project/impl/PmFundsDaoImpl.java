package com.taogongbao.dao.project.impl;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.project.IPmFundsDao;
import com.taogongbao.entity.project.PmFunds;

public class PmFundsDaoImpl extends GenericDaoImpl<PmFunds, Integer> implements
		IPmFundsDao {

	public PmFundsDaoImpl(Class<PmFunds> persistentClass) {
		super(persistentClass);
	}

}
