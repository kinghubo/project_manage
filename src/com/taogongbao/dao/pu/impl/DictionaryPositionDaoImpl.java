package com.taogongbao.dao.pu.impl;

import java.util.List;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.pu.IDictionaryPositionDao;
import com.taogongbao.entity.pu.DictionaryPosition;

/**
 * 公用代码表数据层   接口实现
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
@SuppressWarnings("unchecked")
public class DictionaryPositionDaoImpl extends GenericDaoImpl<DictionaryPosition, String> implements IDictionaryPositionDao{

	public DictionaryPositionDaoImpl(Class<DictionaryPosition> persistentClass) {
		super(persistentClass);
	}
	
	 /**
     * 取全部
     * 
     * @return
     */
	public List<DictionaryPosition> getAll(){
        return getHibernateTemplate().find(" from DictionaryPosition t order by t.id");
    }

}
