/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.dao.hibernateimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cuinfo.pojo.BasePOJO;
import org.cuinfo.util.Page;
import org.cuinfo.util.SpringPropertiesConfigurer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 数据库接口的实现类，需俺要注入sessionFactory及实体T
 *
 * @author Administrator
 */
class BaseDAOImpl<T extends BasePOJO> extends HibernateDaoSupport {

    /**
     * 模板
     */
    private T t;
    /**
     * SQL
     */
    protected String sql;
    /**
     * SQL属性文件
     */
    protected SpringPropertiesConfigurer sqls;

    public String getSql(String sqlKey) {
        String sql = sqls.get(sqlKey);
        if (sql != null) {
            return sql;
        } else {
            throw new UnsupportedOperationException("The sql statement is not defined yet.");
        }
    }

    public void setT(T t) {
        this.t = t;
    }

    public void setSqls(SpringPropertiesConfigurer sqls) {
        this.sqls = sqls;
    }

    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    public void update(T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }

    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    public List<T> viewAll() {
        return this.returnObjects(this.getHibernateTemplate().find("from " + t.getClass().getName()));
    }

    
    public T view(int id) {
        return returnObject(this.getHibernateTemplate().get(t.getClass(), id));
    }

    /**
     * 查询指定HQL，并返回集合
     *
     * @param hql HQL语句
     * @param values 可变的参数列表
     * @return 集合
     */
    public List<T> find(String hql, Object... values) {
        return returnObjects(getHibernateTemplate().find(hql, values));
    }

    public T returnObject(Object o) {
        if (o != null) {
            return (T) o;
        } else {
            return null;
        }
    }

    /**
     * 集合对象转换
     *
     * @param os
     * @return
     */
    public List<T> returnObjects(List<?> os) {
        List<T> result = new ArrayList<T>();
        for (Object object : os) {
            result.add((T) object);
        }
        return result;
    }

    /**
     * 按照HQL语句查询唯一对象.
     *
     * @param hql HQL语句
     * @param values 可变参数集合
     * @return OBJECT对象
     */
    public Object findUnique(final String hql, final Object... values) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("开始查询返回唯一结果的HQL语句," + hql);
            }
            return getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session s)
                        throws HibernateException, SQLException {
                    Query query = createQuery(s, hql, values);
                    return query.uniqueResult();
                }
            });
        } catch (RuntimeException e) {
            logger.error("查询指定HQL异常，HQL：" + hql, e);
            throw e;
        }
    }

    /**
     * 查找指定HQL并返回INT型
     *
     * @param hql HQL语句
     * @param values 可变参数列表
     * @return INT
     */
    public int findInt(final String hql, final Object... values) {
        Object object = findUnique(hql, values);
        if (object == null) {
            return 0;
        } else {
            return ((Long) object).intValue();
        }


    }

    /**
     * 获取指定实体Class指定条件的记录总数
     *
     * @param entityClass 实体Class
     * @param where HQL的查询条件,支持参数列表
     * @param values 可变参数列表
     * @return 记录总数
     */
    public int findTotalCount(final String where,
            final Object... values) {
        String hql = "select count(e) from " + t.getClass().getName() + " as e "
                + where;
        return findInt(hql, values);
    }

    /**
     * 获取指定实体Class的记录总数
     *
     * @param entityClass 实体Class
     * @return 记录总数
     */
    public int findTotalCount(Class<T> entityClass) {
        return findTotalCount(t.getClass().getName(), "");
    }

    /**
     * 查找指定属性的实体集合
     *
     * @param propertyName 属性名
     * @param value 参数
     * @return
     */
    public List<T> findByProperty(String propertyName,
            Object value) {

        String queryStr = "from " + t.getClass().getName()
                + " as model where model." + propertyName + "=?";
        return returnObjects(getHibernateTemplate().find(queryStr, value));

    }

    
    /**
     * 按HQL分页查询.
     *
     * @param page 页面对象
     * @param hql HQL语句
     * @param values 可变参数列表
     * @return 分页数据
     */
    public Page<T> findByPage(final Page<T> page, final String hql,
            final Object... values) {
        try {
            String hql2 = hql;
            if (page != null && page.getFilterHQLHelper() != null) {
                String result = page.getFilterHQLHelper().getResult();
                if (!result.equals("")) {
                    String temp = hql2.toLowerCase();
                    temp = temp.replaceAll("\\s+", " ");
                    int index = temp.indexOf("order by");
                    if (index > 0) {
                        String pre = hql2.substring(0, index);
                        String last = hql2.substring(index);
                        if (pre.contains("where")) {
                            pre += " and" + result;
                            hql2 = pre + " " + last;
                        } else {
                            pre += " where" + result;
                            hql2 = pre + " " + last;
                        }
                    } else {
                        if (hql2.contains("where")) {
                            hql2 += " and" + result;

                        } else {
                            hql2 += " where" + result;

                        }
                    }
                }
            }

            final String hqlResult = hql2.replaceAll("\\s+", " ");

            if (logger.isDebugEnabled()) {
                logger.debug("开始查找指定HQL分页数据," + hqlResult);

            }
            return (Page<T>) getHibernateTemplate().execute(
                    new HibernateCallback() {
                        public Object doInHibernate(Session s)
                                throws HibernateException, SQLException {
                            if (page == null) {
                                return page;
                            }



                            Query query = createQuery(s, hqlResult, values);


                            query.setFirstResult(page.getFirstRow());

                            query.setMaxResults(page.getPageSize());


                            page.setResult(returnObjects(query.list()));
                            if (logger.isDebugEnabled()) {
                                logger.debug("查找指定HQL分页数据成功," + hqlResult);
                            }

                            //***查看结果总数/////
                            String temp = hqlResult;

                            temp = temp.toLowerCase();
                            temp = temp.replaceAll("\\s+", " ");
                            int index = temp.indexOf("order by");

                            String result = hqlResult;
                            if (index > 0) {
                                result = hqlResult.substring(0, index);
                            }

                            if (page.getTotalCount() == -1) {

                                page.setTotalCount(findInt(
                                        "select count(*) " + result,
                                        values));
                            }
                            return page;
                        }
                    });
        } catch (RuntimeException e) {
            logger.error("分页查询异常，HQL：" + hql, e);
            throw e;
        }
    }
    
    /**
		 * 查看所有的实体
		 * @param page
		 * @return
		 */
		public Page<T> viewAllByPage(Page<T> page){
			String hql="from " + t.getClass().getName()+" order by id desc";
			/****
			if(page.getFilterHQLHelper()!=null){
				String result=page.getFilterHQLHelper().getResult();
				if(!result.equals("")){
				hql+=" where ";
				hql+=result;
				}
			}
			******/
			return this.findByPage(page, hql);
    }

    /**
     * 根据查询条件与参数列表创建Query对象
     *
     * @param session Hibernate会话
     * @param hql HQL语句
     * @param objects 参数列表
     * @return Query对象
     */
    public Query createQuery(Session session, String hql, Object... objects) {
        Query query = session.createQuery(hql);
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                query.setParameter(i, objects[i]);
            }
        }
        return query;
    }

    /**
     * *
     * 根据查询条件与参数列表创建SQL Query对象
     *
     * @param session
     * @param sql
     * @param objects
     * @return query
     */
    public Query createSQLQuery(Session session, String sql, Object... objects) {
        Query query = session.createSQLQuery(sql);
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                query.setParameter(i, objects[i]);
            }
        }
        return query;
    }
}
