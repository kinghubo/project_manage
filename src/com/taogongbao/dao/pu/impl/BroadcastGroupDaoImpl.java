package com.taogongbao.dao.pu.impl;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.pu.IBroadcastGroupDao;
import com.taogongbao.entity.pu.BroadcastGroup;

public class BroadcastGroupDaoImpl extends GenericDaoImpl<BroadcastGroup, Integer> implements
		IBroadcastGroupDao {

	public BroadcastGroupDaoImpl(Class<BroadcastGroup> persistentClass) {
		super(persistentClass);
	}

}
