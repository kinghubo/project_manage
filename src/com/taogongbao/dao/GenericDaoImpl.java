package com.taogongbao.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.feinno.baigong.common.db.DbOperation;
import com.feinno.baigong.common.ex.AppException;
import com.taogongbao.common.constant.Constants;
import com.taogongbao.common.db.SqlUtil;
import com.taogongbao.common.entity.OrderByBean;
import com.taogongbao.common.entity.PageModel;
import com.taogongbao.common.entity.QueryKeyValue;

/**
 * 该类作为所有其他DAO的基类，也就是包含了所有可能用到的通用的CRUD方法，如果需要自定义CRUD逻辑，只需要继承该类
 * 
 * <p>
 * 在Spring中注册该类，请参考使用如下XML
 * 
 * <pre>
 *      &lt;bean id="fooDao" class="com.dhcc.itsm.core.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="com.dhcc.itsm.core.model.Foo"/&gt;
 *          &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 * 
 * @author <a href="mailto:yangcy@yangcy.com.cn">Yang Chongyang</a>
 * @param <T>
 *            类型变量
 * @param <PK>
 *            类型主键
 */
public class GenericDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements IGenericDao<T, PK> {
	
    /**
     * 作用于所有子类的Log变量
     */
    protected final Log log = LogFactory.getLog(getClass());
    private final Class<T> persistentClass;

    /**
     * 构造函数
     * 
     * @param persistentClass 要持久化的类
     */
    public GenericDaoImpl(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAll() {
        return super.getHibernateTemplate().loadAll(this.persistentClass);
    }

    /**
     * {@inheritDoc}
     */
    public T get(PK id) {
        return super.getHibernateTemplate().get(this.persistentClass, id);
    }

    /**
     * {@inheritDoc}
     */
    public T save(T object) {
        return super.getHibernateTemplate().merge(object);
    }

    /**
     * {@inheritDoc}
     */
    public int remove(PK id) {
    	
    	int res = -1;
    	T obj = this.get(id);
    	if(null == obj){
    		res = 0;
    	}else{
    		super.getHibernateTemplate().delete(obj);
    		res = 1;
    	}
        return res;
    }

    /**
     * {@inheritDoc}
     */
    public void remove(T object) {
        super.getHibernateTemplate().delete(object);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findPageAll(final String hql, final int first, final int max) {
        return super.getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                return query.setFirstResult(first).setMaxResults(max).list();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public int getInfosCount(String hqlQuery) {
        StringBuilder sb = new StringBuilder("select count(*) ");
        int value = 0;
        Session session = null;
        try {

            String lowerStr = hqlQuery.toLowerCase();
            if (lowerStr.trim().startsWith("select")) {
                String fromHql = hqlQuery.substring(hqlQuery.indexOf("from") + 4, hqlQuery.length());
                sb.append("from ").append(fromHql).append(" ");
            } else if (lowerStr.trim().startsWith("from")) {
                sb.append(hqlQuery.trim());
            }
            session = getSession();
            Query query = session.createQuery(sb.toString());
            value = ((Long) query.iterate().next()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new HibernateException(e);
        } finally {
            if (null != session) {
                releaseSession(session);
            }
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    public List<T> findPageAll(final String hql, final int first, final int max, final Object... values){
        return super.getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        if (values[i] instanceof Integer) {
                            query.setInteger(i, (Integer) values[i]);
                        } else if (values[i] instanceof String) {
                            query.setString(i, (String) values[i]);
                        } else if (values[i] instanceof Double) {
                            query.setDouble(i, (Double) values[i]);
                        } else if (values[i] instanceof Date) {
                            query.setDate(i, (Date) values[i]);
                        } else if (values[i] instanceof Boolean) {
                            query.setBoolean(i, (Boolean) values[i]);
                        } else {
                            System.err.println("11111111111未判断参数:" + values[i].getClass() + "," + values[i]);
                        }
                    }
                }
                int limit = max;
                if (limit == 0) {
                    limit = PageModel.PAGE_SIZE;
                }
                return query.setFirstResult(first).setMaxResults(limit).list();
            }
        });
    }

    public int getInfosCount(String hqlQuery, Object... values) {
        StringBuilder sb = new StringBuilder("select count(*) ");
        int value = 0;
        String lowerStr = hqlQuery.toLowerCase();
        Session session = null;
        try {
            if (lowerStr.trim().startsWith("select")) {
                String fromHql = hqlQuery.substring(hqlQuery.indexOf("from") + 4, hqlQuery.length());
                sb.append("from ").append(fromHql).append(" ");
            } else if (lowerStr.trim().startsWith("from")) {
                sb.append(hqlQuery.trim());
            }
            session = getSession();
            Query query = session.createQuery(sb.toString());
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    if (values[i] instanceof Integer) {
                        query.setInteger(i, (Integer) values[i]);
                    } else if (values[i] instanceof String) {
                        query.setString(i, (String) values[i]);
                    } else if (values[i] instanceof Double) {
                        query.setDouble(i, (Double) values[i]);
                    } else if (values[i] instanceof Date) {
                        query.setDate(i, (Date) values[i]);
                    } else if (values[i] instanceof Boolean) {
                        query.setBoolean(i, (Boolean) values[i]);
                    } else {
                        System.err.println("22222222222222未判断参数:" + values[i].getClass() + "," + values[i]);
                    }
                }
            }
            value = ((Long) query.iterate().next()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new HibernateException(e);
        } finally {
            if (null != session) {
                releaseSession(session);
            }
        }
        return value;
    }

    @SuppressWarnings("unchecked")
	public List<T> getGenList(List<QueryKeyValue> queryList, List<OrderByBean> orderList, int start, int limit) {
        List<T> list = null;
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Session sess = getSession();
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("from ");
            sb.append(entityClass.getSimpleName());
            sb.append(" t where 1=1");
            if(queryList != null && queryList.size() > 0) {
                for (QueryKeyValue kv : queryList) {
                    if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.LIKE)) {
                        sb.append(" and t." + kv.getKey() + " " + Constants.HqlSign.LIKE + " ? ");
                    } else if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.IN)) {
                        sb.append(" and t." + kv.getKey() + " " + kv.getSign() + " (" + kv.getValue() + ") ");
                    } else {
                        sb.append(" and t." + kv.getKey() + " " + kv.getSign() + " ? ");
                    }
                }
            }
            if(orderList != null && orderList.size() > 0) {
                sb.append(" order by ");
                Iterator<OrderByBean> orderItr = orderList.iterator();
                while (orderItr.hasNext()) {
                    OrderByBean ob = orderItr.next();
                    sb.append(" t." + ob.getOrderField() + " " + ob.getOrderValue() + (orderItr.hasNext() ? "," : ""));
                }
            }
            Query query = sess.createQuery(sb.toString());
            int i = 0;
            if (queryList != null && queryList.size() > 0) {
                for (QueryKeyValue kv : queryList) {
                    if (kv.getValue() instanceof Integer) {
                        query.setInteger(i, (Integer) kv.getValue());
                    } else if (kv.getValue() instanceof String) {
                        if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.LIKE)) {
                            query.setString(i, "%" + kv.getValue() + "%");
                        } else if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.IN)) {
                            continue;
                        } else {
                            query.setString(i, (String) kv.getValue());
                        }
                    } else if (kv.getValue() instanceof Double) {
                        query.setDouble(i, (Double) kv.getValue());
                    } else if (kv.getValue() instanceof Date) {
                        query.setDate(i, (Date) kv.getValue());
                    } else if (kv.getValue() instanceof Boolean) {
                        query.setBoolean(i, (Boolean) kv.getValue());
                    } else if (kv.getValue() instanceof Long) {
                        query.setLong(i, (Long) kv.getValue());
                    } else {
                        System.err.println("333333333333333未判断参数:" + kv.getClass() + "," + kv);
                    }
                    i++;
                }
            }
            if (limit == 0) {
                limit = PageModel.PAGE_SIZE;
            }
            list = query.setFirstResult(start).setMaxResults(limit).list();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new HibernateException(e);
        } finally {
            if (null != sess) {
                releaseSession(sess);
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public int getGenCount(List<QueryKeyValue> queryList) {
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        Session sess = getSession();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        try {
            sb.append(" select count(*) from");
            sb.append(" " + entityClass.getSimpleName());
            sb.append(" t where 1=1 ");
            if (queryList != null && queryList.size() > 0) {
                for (QueryKeyValue kv : queryList) {
                    if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.LIKE)) {
                        sb.append(" and t." + kv.getKey() + " " + Constants.HqlSign.LIKE + " ? ");
                    } else if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.IN)) {
                        sb.append(" and t." + kv.getKey() + " " + kv.getSign() + " (" + kv.getValue() + ") ");
                    } else {
                        sb.append(" and t." + kv.getKey() + " " + kv.getSign() + " ? ");
                    }
                }
            }
            Query query = sess.createQuery(sb.toString());
            int i = 0;
            if (queryList != null && queryList.size() > 0) {
                for (QueryKeyValue kv : queryList) {
                    if (kv.getValue() instanceof Integer) {
                        query.setInteger(i, (Integer) kv.getValue());
                    } else if (kv.getValue() instanceof String) {
                        if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.LIKE)) {
                            query.setString(i, "%" + kv.getValue() + "%");
                        } else if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.IN)) {
                            continue;
                        } else {
                            query.setString(i, (String) kv.getValue());
                        }
                    } else if (kv.getValue() instanceof Double) {
                        query.setDouble(i, (Double) kv.getValue());
                    } else if (kv.getValue() instanceof Date) {
                        query.setDate(i, (Date) kv.getValue());
                    } else if (kv.getValue() instanceof Long) {
                        query.setLong(i, (Long) kv.getValue());
                    }
                    i++;
                }
            }
            count = ((Long) query.iterate().next()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new HibernateException(e);
        } finally {
            if (null != sess) {
                releaseSession(sess);
            }
        }
        return count;
    }

    @SuppressWarnings("unchecked")
    public int updateGen(List<QueryKeyValue> queryList, List<QueryKeyValue> updateList) {
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        Session session = getSession();
        int count = 0;
        try {

            StringBuilder sb = new StringBuilder();
            sb.append("update " + entityClass.getSimpleName() + " t");
            if (updateList != null && updateList.size() > 0) {
                sb.append(" set");
                Iterator<QueryKeyValue> updateItr = updateList.iterator();
                while (updateItr.hasNext()) {
                    QueryKeyValue updateCon = updateItr.next();
                    sb.append(" t." + updateCon.getKey() + "= ? " + (updateItr.hasNext() ? "," : ""));
                }
            }
            if (queryList != null && queryList.size() > 0) {
                sb.append(" where 1=1");
                for (QueryKeyValue queryCon : queryList) {
                    if (queryCon.getSign().equalsIgnoreCase(Constants.HqlSign.LIKE)) {
                        sb.append(" and t." + queryCon.getKey() + " " + Constants.HqlSign.LIKE + " ? ");
                    } else if (queryCon.getSign().equalsIgnoreCase(Constants.HqlSign.IN)) {
                        sb.append(" and t." + queryCon.getKey() + " " + queryCon.getSign() + " (" + queryCon.getValue()
                                + ") ");
                    } else {
                        sb.append(" and t." + queryCon.getKey() + " " + queryCon.getSign() + " ? ");
                    }
                }
            }

            Query query = session.createQuery(sb.toString());
            int i = 0;
            if (updateList != null && updateList.size() > 0) {
                for (QueryKeyValue kv : updateList) {
                    if (kv.getValue() instanceof Integer) {
                        query.setInteger(i, (Integer) kv.getValue());
                    } else if (kv.getValue() instanceof String) {
                        if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.LIKE)) {
                            query.setString(i, "%" + kv.getValue() + "%");
                        } else if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.IN)) {
                            continue;
                        } else {
                            query.setString(i, (String) kv.getValue());
                        }
                    } else if (kv.getValue() instanceof Double) {
                        query.setDouble(i, (Double) kv.getValue());
                    } else if (kv.getValue() instanceof Date) {
                        query.setDate(i, (Date) kv.getValue());
                    } else if (kv.getValue() instanceof Boolean) {
                        query.setBoolean(i, (Boolean) kv.getValue());
                    } else {
                        System.err.println("444444444444444未判断参数:" + kv.getValue().getClass() + "," + kv.getValue());
                    }
                    i++;
                }
            }
            if (queryList != null && queryList.size() > 0) {
                for (QueryKeyValue kv : queryList) {
                    if (kv.getValue() instanceof Integer) {
                        query.setInteger(i, (Integer) kv.getValue());
                    } else if (kv.getValue() instanceof String) {
                        if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.LIKE)) {
                            query.setString(i, "%" + kv.getValue() + "%");
                        } else if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.IN)) {
                            continue;
                        } else {
                            query.setString(i, (String) kv.getValue());
                        }
                    } else if (kv.getValue() instanceof Double) {
                        query.setDouble(i, (Double) kv.getValue());
                    } else if (kv.getValue() instanceof Date) {
                        query.setDate(i, (Date) kv.getValue());
                    } else if (kv.getValue() instanceof Boolean) {
                        query.setBoolean(i, (Boolean) kv.getValue());
                    } else if (kv.getValue() instanceof Long) {
                        query.setLong(i, (Long) kv.getValue());
                    } else {
                        System.err.println("55555555555555555555未判断参数:" + kv.getClass() + "," + kv);
                    }
                    i++;
                }
            }
            count = query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new HibernateException(e);
        } finally {
            if (null != session) {
                releaseSession(session);
            }
        }
        return count;
    }

    public PageModel<T> getGenPage(List<QueryKeyValue> queryList, List<OrderByBean> orderList, int start, int limit) {
        PageModel<T> page = new PageModel<T>();
        List<T> list = getGenList(queryList, orderList, start, limit);
        int totalRows = getGenCount(queryList);
        int totalPages = totalRows / PageModel.PAGE_SIZE + (totalRows % PageModel.PAGE_SIZE > 0 ? 1 : 0);
        page.setTotalRows(totalRows);
        page.setDataList(list);
        page.setCurrentpage(start / PageModel.PAGE_SIZE + (start % PageModel.PAGE_SIZE > 0 ? 1 : 0));
        page.setTotalPages(totalPages);
        page.setStart(start);
        page.setLimit(limit);
        return page;
    }

    public PageModel<T> getGenPage(String queryHql, String countHql, int start, int limit, Object... valus) {
        PageModel<T> pageModel = new PageModel<T>();
        List<T> list = findPageAll(queryHql, start, limit, valus);
        int rows = getInfosCount(countHql, valus);
        int totalPages = rows / PageModel.PAGE_SIZE + (rows % PageModel.PAGE_SIZE > 0 ? 1 : 0);
        pageModel.setCurrentpage(start / PageModel.PAGE_SIZE + (start % PageModel.PAGE_SIZE > 0 ? 1 : 0));
        pageModel.setDataList(list);
        pageModel.setTotalPages(totalPages);
        pageModel.setStart(start);
        pageModel.setLimit(limit);
        pageModel.setTotalRows(rows);
        return pageModel;
    }

    @SuppressWarnings("unchecked")
    public PageModel<Object[]> getObjectPage(final String queryHql, final String countHql, final int start,
            final int limit, final Object... values) {
        PageModel<Object[]> pageModel = new PageModel<Object[]>();
        List<Object[]> list = super.getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(queryHql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        if (values[i] instanceof Integer) {
                            query.setInteger(i, (Integer) values[i]);
                        } else if (values[i] instanceof String) {
                            query.setString(i, (String) values[i]);
                        } else if (values[i] instanceof Double) {
                            query.setDouble(i, (Double) values[i]);
                        } else if (values[i] instanceof Date) {
                            query.setDate(i, (Date) values[i]);
                        } else if (values[i] instanceof Boolean) {
                            query.setBoolean(i, (Boolean) values[i]);
                        } else {
                            System.err.println("6666666666666666其他类型的参数未做判断:" + values[i].getClass() + "," + values[i]);
                        }
                    }
                }
                return query.setFirstResult(start).setMaxResults(limit).list();
            }
        });
        ;
        int rows = getInfosCount(countHql, values);
        int totalPages = rows / PageModel.PAGE_SIZE + (rows % PageModel.PAGE_SIZE > 0 ? 1 : 0);
        pageModel.setCurrentpage(start / PageModel.PAGE_SIZE + (start % PageModel.PAGE_SIZE > 0 ? 1 : 0));
        pageModel.setDataList(list);
        pageModel.setTotalPages(totalPages);
        pageModel.setTotalRows(rows);
        pageModel.setStart(start);
        pageModel.setLimit(limit);
        return pageModel;
    }

    public PageModel<Map<String, Object>> getObjectSqlPage(String querySql, String countSql, int start, int limit,
            Object... values) throws Exception {

        PageModel<Map<String, Object>> pageModel = new PageModel<Map<String, Object>>();
        // 获取数据库连接
        Session session = getConn();
        Connection conn = SessionFactoryUtils.getDataSource(session.getSessionFactory()).getConnection();
        // 执行数据库操作
        PreparedStatement queryStmt = conn.prepareStatement(querySql);
        PreparedStatement cntStmt = conn.prepareStatement(countSql);

        // 组装sql参数
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                int index = i + 1;
                if (values[i] instanceof Integer) {
                    queryStmt.setInt(index, (Integer) values[i]);
                    cntStmt.setInt(index, (Integer) values[i]);
                } else if (values[i] instanceof String) {
                    queryStmt.setString(index, (String) values[i]);
                    cntStmt.setString(index, (String) values[i]);
                } else if (values[i] instanceof Double) {
                    queryStmt.setDouble(index, (Double) values[i]);
                    cntStmt.setDouble(index, (Double) values[i]);
                } else if (values[i] instanceof java.sql.Date) {
                    queryStmt.setDate(index, (java.sql.Date) values[i]);
                    cntStmt.setDate(index, (java.sql.Date) values[i]);
                } else if (values[i] instanceof Boolean) {
                    queryStmt.setBoolean(index, (Boolean) values[i]);
                    cntStmt.setBoolean(index, (Boolean) values[i]);
                } else {
                    System.err.println("77777其他类型的参数未做判断:" + values[i].getClass() + "," + values[i]);
                }
            }
        }

        ResultSet queryRs = queryStmt.executeQuery();
        ResultSet cntRs = cntStmt.executeQuery();
        //取得列名
        ResultSetMetaData metaData = queryStmt.getMetaData();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int colsCnt = metaData.getColumnCount();
        Map<String, Object> map = null;
        while (queryRs.next()) {
            map = new HashMap<String, Object>();
            for (int i = 1; i < colsCnt + 1; i++) {
                String colName = metaData.getColumnName(i).toLowerCase();
                Object colValue = queryRs.getObject(i);
                map.put(colName, colValue);
            }
            list.add(map);
        }

        int rows = 0;
        if (cntRs.next()) {
            rows = cntRs.getInt(1);
        }

        int totalPages = rows / PageModel.PAGE_SIZE + (rows % PageModel.PAGE_SIZE > 0 ? 1 : 0);
        pageModel.setCurrentpage(start / PageModel.PAGE_SIZE + (start % PageModel.PAGE_SIZE > 0 ? 1 : 0));
        pageModel.setDataList(list);
        pageModel.setTotalPages(totalPages);
        pageModel.setTotalRows(rows);
        pageModel.setStart(start);
        pageModel.setLimit(limit);
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (session != null) {
            session.close();
        }

        return pageModel;
    }

    /**
     * 获取Session连接
     */
    public Session getConn() {
        Session session = getSession();
        return session;
    }

    private int getShiftidindex(String loactionid) {
        char detail = '0';
        int index = 0;
        char temp = '0';
        for (int i = loactionid.length() - 1; i >= 0; i--) {
            temp = loactionid.charAt(i);
            if (temp == detail) {
                continue;
            } else {
                index = i + 1;
                return index;
            }
        }
        return 0;
    }

    @SuppressWarnings("unused")
    private String getShiftid(String location) {
        int index = getShiftidindex(location);
        return location.substring(0, index);
    }

    @SuppressWarnings("unchecked")
    public int removeByCondition(List<QueryKeyValue> kvList) {
    	int result = 0;
        Class<T> entityClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        StringBuffer hql = new StringBuffer();
        hql.append(" delete from " + entityClass.getSimpleName() + " t where 1 = 1");
        // 组查询条件
        if (null != kvList && kvList.size() > 0) {
            for (QueryKeyValue kv : kvList) {
                if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.IN)) {
                    hql.append(" and t." + kv.getKey() + " " + kv.getSign() + " (" + kv.getValue() + ")");
                } else {
                    hql.append(" and t." + kv.getKey() + " " + kv.getSign() + " ? ");
                }
            }
        }
        Session sess = getSession();
        try {
	        Query query = sess.createQuery(hql.toString());
	        int i = 0;
	        if (null != kvList && kvList.size() > 0) {
	            for (QueryKeyValue kv : kvList) {
	                if (kv.getValue() instanceof Integer) {
	                    query.setInteger(i, (Integer) kv.getValue());
	                } else if (kv.getValue() instanceof String) {
	                    if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.LIKE)) {
	                        query.setString(i, "%" + kv.getValue() + "%");
	                    } else if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.IN)) {
	                        continue;
	                    } else {
	                        query.setString(i, (String) kv.getValue());
	                    }
	                } else if (kv.getValue() instanceof Double) {
	                    query.setDouble(i, (Double) kv.getValue());
	                } else if (kv.getValue() instanceof Date) {
	                    query.setDate(i, (Date) kv.getValue());
	                } else if (kv.getValue() instanceof Boolean) {
	                    query.setBoolean(i, (Boolean) kv.getValue());
	                } else if (kv.getValue() instanceof Long) {
	                    query.setLong(i, (Long) kv.getValue());
	                } else {
	                    System.err.println("333333333333333未判断参数:" + kv.getClass() + "," + kv);
	                }
	                i++;
	            }
	        }
	        
	        result = query.executeUpdate();
    	} catch (HibernateException e) {
            e.printStackTrace();
            throw new HibernateException(e);
        } finally {
            if (null != sess) {
                releaseSession(sess);
            }
        }
        
        return result;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getGenList(List<QueryKeyValue> queryList, List<OrderByBean> orderList) {
        List<T> list = null;
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        Session sess = getSession();
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("from ");
            sb.append(entityClass.getSimpleName());
            sb.append(" t where 1=1");
            if (queryList != null && queryList.size() > 0) {
                for (QueryKeyValue kv : queryList) {
                    if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.LIKE)) {
                        sb.append(" and t." + kv.getKey() + " " + Constants.HqlSign.LIKE + " ? ");
                    } else if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.IN)) {
                        sb.append(" and t." + kv.getKey() + " " + kv.getSign() + " (" + kv.getValue() + ") ");
                    } else {
                        sb.append(" and t." + kv.getKey() + " " + kv.getSign() + " ? ");
                    }
                }
            }
            if (orderList != null && orderList.size() > 0) {
                sb.append(" order by ");
                Iterator<OrderByBean> orderItr = orderList.iterator();
                while (orderItr.hasNext()) {
                    OrderByBean ob = orderItr.next();
                    sb.append(" t." + ob.getOrderField() + " " + ob.getOrderValue() + (orderItr.hasNext() ? "," : ""));
                }
            }
            Query query = sess.createQuery(sb.toString());
            int i = 0;
            if (queryList != null && queryList.size() > 0) {
                for (QueryKeyValue kv : queryList) {
                    if (kv.getValue() instanceof Integer) {
                        query.setInteger(i, (Integer) kv.getValue());
                    } else if (kv.getValue() instanceof String) {
                        if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.LIKE)) {
                            query.setString(i, "%" + kv.getValue() + "%");
                        } else if (kv.getSign().equalsIgnoreCase(Constants.HqlSign.IN)) {
                            continue;
                        } else {
                            query.setString(i, (String) kv.getValue());
                        }
                    } else if (kv.getValue() instanceof Double) {
                        query.setDouble(i, (Double) kv.getValue());
                    } else if (kv.getValue() instanceof Date) {
                        query.setDate(i, (Date) kv.getValue());
                    } else if (kv.getValue() instanceof Boolean) {
                        query.setBoolean(i, (Boolean) kv.getValue());
                    } else if (kv.getValue() instanceof Long) {
                        query.setLong(i, (Long) kv.getValue());
                    } else {
                        System.err.println("333333333333333未判断参数:" + kv.getClass() + "," + kv);
                    }
                    i++;
                }
            }
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new HibernateException(e);
        } finally {
            if (null != sess) {
                releaseSession(sess);
            }
        }
        return list;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List getGenList(String sql, Class beanClass, Object[] paramsl, int start, int limit) {
		
		Connection conn;
		List list = null;
		Session session = getConn();
		try {
			conn = SessionFactoryUtils.getDataSource(session.getSessionFactory()).getConnection();
//			sql = sql + " limit " + start + "," + limit;
			sql = SqlUtil.convertPagingSql(sql, start, limit);
			list = DbOperation.queryToBean(conn, sql, beanClass, paramsl, 0, 0);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (AppException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public int getGenCount(String sql, Object[] params) {
		int count = -1;
		Session session = null;
		Connection conn = null;
		try {
			session = getConn();
			conn = SessionFactoryUtils.getDataSource(session.getSessionFactory()).getConnection();
			count = DbOperation.getCount(conn, sql, params);
		} catch (AppException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return count;
	}

}
