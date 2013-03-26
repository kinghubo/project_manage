package com.taogongbao.manager;

import java.util.ArrayList;
import java.util.List;

import com.feinno.baigong.common.tools.StringUtil;
import com.taogongbao.common.entity.QueryKeyValue;
import com.taogongbao.dao.pu.ILocationDao;
import com.taogongbao.dao.pu.IOptionsDao;
import com.taogongbao.entity.pu.Location;
import com.taogongbao.entity.pu.Options;

public class CommonManagerImpl implements ICommonManager {
	
	private IOptionsDao optionsDao;
	private ILocationDao locationDao;

	@Override
	public List<Location> queryLocation(String parentId) {
		if(StringUtil.isEmpty(parentId)){
			return null;
		}
		List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
		queryList.add(new QueryKeyValue("parentId", parentId));
		return locationDao.getGenList(queryList, null);
	}

	@Override
	public List<Options> queryOptions() {
		List<QueryKeyValue> queryList = new ArrayList<QueryKeyValue>();
		queryList.add(new QueryKeyValue("typeId", "QYZHLY"));
		return optionsDao.getGenList(queryList, null);
	}
	
	public IOptionsDao getOptionsDao() {
		return optionsDao;
	}
	public void setOptionsDao(IOptionsDao optionsDao) {
		this.optionsDao = optionsDao;
	}
	public ILocationDao getLocationDao() {
		return locationDao;
	}
	public void setLocationDao(ILocationDao locationDao) {
		this.locationDao = locationDao;
	}
}
