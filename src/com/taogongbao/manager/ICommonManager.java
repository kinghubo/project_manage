package com.taogongbao.manager;

import java.util.List;

import com.taogongbao.entity.pu.Location;
import com.taogongbao.entity.pu.Options;

public interface ICommonManager {
	
	public List<Location> queryLocation(String parentId);
	
	public List<Options> queryOptions();

}
