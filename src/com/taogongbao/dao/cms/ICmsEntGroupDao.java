package com.taogongbao.dao.cms;

import java.util.List;

import com.taogongbao.dao.IGenericDao;
import com.taogongbao.entity.cms.CmsEntGroup;

public interface ICmsEntGroupDao extends IGenericDao<CmsEntGroup, Integer> {
    
    public List<CmsEntGroup> getCmsEntGroupByGroupIds(String groupIds);
}
