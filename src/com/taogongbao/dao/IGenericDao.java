package com.taogongbao.dao;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.taogongbao.common.entity.OrderByBean;
import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.entity.QueryKeyValue;
		
/**
 * 泛型DAO接口
 */

public interface IGenericDao <T, PK extends Serializable> {
	
    /**
     * 根据键值对,删除对应数据
     */
    public int removeByCondition(List<QueryKeyValue> kvList);

    /**
     * 该方法用于获取对象列表，相当于查找表中所有的行。
     */
    List<T> getAll();    

    /**
     * 该方法用于基于ID获取对象
     * @param id 要获取对象的唯一标识符(主键)
     */
    T get(PK id);

    /**
     * 该方法用于保存对象 - 既可用于update也可用于insert操作。
     * @param object 要保存的对象
     * @return 保存后的对象
     */
    T save(T object);

    /**
     * 该方法用于基于id删除对象
     * @param id 要删除对象的唯一标识符(主键)
     * @return int 0:根据id查询无数据  1：删除成功 -1：删除报错失败
     */
    int remove(PK id);
    
    /**
     * 该方法用于基于实例删除对象
     * @param object 要删除对象
     */
    void remove(T object);
    
    /**
     * 分页获取对象列表
     * @param hql 
     * @param first 开始记录数
     * @param max 显示记录数
     * @return
     * @throws Exception
     */
    List<T> findPageAll(final String hql,final int first,final int max);
    
    /**
     * 分页获取对象列表
     * @param hql
     * @param first
     * @param max
     * @param values
     * @return
     * @throws Exception
     */
    List<T> findPageAll(final String hql,final int first,final int max,Object...values);
    
    /**
     * 获取对象总记录数
     * @param hqlQuery
     * @return
     * @throws Exception
     */
    int getInfosCount(String hqlQuery);
    /**
     * 获取对象总记录数
     * @param hqlQuery
     * @param values
     * @return
     * @throws Exception
     */
    int getInfosCount(String hqlQuery,Object...values);
    
    /**
     * 通过hql查询分页对象
     *
     * @param queryHql 查询列表hql
     * @param countHql 查询总条数hql
     * @param pageNo 当前页码
     * @param pageSize 每页大小
     * @param valus 查询参数列表
     * @return
     * @auther <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
     * Apr 2, 2011 12:29:31 PM
     */
    PageModel<T> getGenPage(String queryHql, String countHql, int start, int limit, Object... valus);
    
    /**
     * 根据条件查询相关数据，返回列表
     * 如果pageNo， pageSize其中之一为空则不分页列表
     * @param queryList 所有条件以and连接
     * @param orderList
     * @param start 起始位置
     * @param limit 数据长度
     */
    public List<T> getGenList(List<QueryKeyValue> queryList, List<OrderByBean> orderList, int start, int limit);
    
    public List<T> getGenList(List<QueryKeyValue> queryList, List<OrderByBean> orderList);
    
    @SuppressWarnings("unchecked")
	public List getGenList(String sql,Class beanClass,Object[] paramsl, int start, int limit);
    
    /**
     * 根据条件查询统计数 
     */
    public int getGenCount(List<QueryKeyValue> queryList);
    
    public int getGenCount(String sql, Object[] params);
    
    /**
     * 根据查询参数更新状态,查询条件均以and连接
     * 
     * @param queryList 需要更新的查询条件
     * @param updateList 更新字段的值设置
     * @return
     * @auther <a href="mailto:zhaoxinnw@feinno.com">Zhao xin</a>
     * Mar 31, 2011 3:55:36 PM
     */
    public int updateGen(List<QueryKeyValue> queryList, List<QueryKeyValue> updateList);
    
    /**
     * 根据查询参数查询并组装响应的页面对象
     * @param queryList
     * @param orderList
     * @param start 起始位置
     * @param limit 数据长度
     * @return
     */
    public PageModel<T> getGenPage(List<QueryKeyValue> queryList, List<OrderByBean> orderList, int start, int limit);
    
    /**
     * 通过hql查询分页对象
     * @param queryHql 查询列表hql
     * @param countHql 查询总条数hql
     * @param pageNo 当前页码
     * @param pageSize 每页大小
     * @param valus 查询参数列表
     * @return
     */
    PageModel<Object[]> getObjectPage(String queryHql, String countHql, int pageNo, int pageSize, Object... valus);
    
    public PageModel<Map<String, Object>> getObjectSqlPage(String querySql,String countSql, int start, int limit, Object... values)throws Exception;
    
    public Session getConn();
}