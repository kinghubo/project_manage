package com.taogongbao.dao.cms.impl;

import java.util.List;

import com.taogongbao.dao.GenericDaoImpl;
import com.taogongbao.dao.cms.ICmsEntGroupDao;
import com.taogongbao.entity.cms.CmsEntGroup;

public class CmsEntGroupDaoImpl extends GenericDaoImpl<CmsEntGroup, Integer> implements ICmsEntGroupDao {

    public CmsEntGroupDaoImpl(Class<CmsEntGroup> persistentClass) {
        super(persistentClass);
    }
    
    public List<CmsEntGroup> getCmsEntGroupByGroupIds(String groupIds){
        StringBuilder hql = new StringBuilder();
        hql.append(" from CmsEntGroup where groupId in(").append(groupIds).append(")");
        
        return this.getHibernateTemplate().find(hql.toString());
    }
}
