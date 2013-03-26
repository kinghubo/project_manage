package com.taogongbao.dao.pu;

import java.util.List;

import com.taogongbao.dao.IGenericDao;
import com.taogongbao.entity.pu.Location;

/**
 * 地区表数据层   接口
 *
 * @createTime: 2010-11-19 下午03:46:00
 * @author: <a href="mailto:lihanpei@feinno.com">Hanpei Li</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:lihanpei@feinno.com">Hanpei Li</a>
 * @changesSum: 
 * 
 */
public interface ILocationDao extends IGenericDao<Location, Long> {
	
	 /**
     * 取全部
     * 
     * @return
     */
    public List<Location> getAll();
}