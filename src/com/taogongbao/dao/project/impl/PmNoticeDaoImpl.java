package com.taogongbao.dao.project.impl;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.project.IPmNoticeDao;
import com.taogongbao.entity.project.PmNotice;

public class PmNoticeDaoImpl extends GenericDaoImpl<PmNotice, Integer> implements
		IPmNoticeDao {

	public PmNoticeDaoImpl(Class<PmNotice> persistentClass) {
		super(persistentClass);
	}

}
