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
 * ���ݿ�ӿڵ�ʵ���࣬�谳Ҫע��sessionFactory��ʵ��T
 *
 * @author Administrator
 */
class BaseDAOImpl<T extends BasePOJO> extends HibernateDaoSupport {

    /**
     * ģ��
     */
    private T t;
    /**
     * SQL
     */
    protected String sql;
    /**
     * SQL�����ļ�
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
     * ��ѯָ��HQL�������ؼ���
     *
     * @param hql HQL���
     * @param values �ɱ�Ĳ����б�
     * @return ����
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
     * ���϶���ת��
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
     * ����HQL����ѯΨһ����.
     *
     * @param hql HQL���
     * @param values �ɱ��������
     * @return OBJECT����
     */
    public Object findUnique(final String hql, final Object... values) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("��ʼ��ѯ����Ψһ�����HQL���," + hql);
            }
            return getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session s)
                        throws HibernateException, SQLException {
                    Query query = createQuery(s, hql, values);
                    return query.uniqueResult();
                }
            });
        } catch (RuntimeException e) {
            logger.error("��ѯָ��HQL�쳣��HQL��" + hql, e);
            throw e;
        }
    }

    /**
     * ����ָ��HQL������INT��
     *
     * @param hql HQL���
     * @param values �ɱ�����б�
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
     * ��ȡָ��ʵ��Classָ�������ļ�¼����
     *
     * @param entityClass ʵ��Class
     * @param where HQL�Ĳ�ѯ����,֧�ֲ����б�
     * @param values �ɱ�����б�
     * @return ��¼����
     */
    public int findTotalCount(final String where,
            final Object... values) {
        String hql = "select count(e) from " + t.getClass().getName() + " as e "
                + where;
        return findInt(hql, values);
    }

    /**
     * ��ȡָ��ʵ��Class�ļ�¼����
     *
     * @param entityClass ʵ��Class
     * @return ��¼����
     */
    public int findTotalCount(Class<T> entityClass) {
        return findTotalCount(t.getClass().getName(), "");
    }

    /**
     * ����ָ�����Ե�ʵ�弯��
     *
     * @param propertyName ������
     * @param value ����
     * @return
     */
    public List<T> findByProperty(String propertyName,
            Object value) {

        String queryStr = "from " + t.getClass().getName()
                + " as model where model." + propertyName + "=?";
        return returnObjects(getHibernateTemplate().find(queryStr, value));

    }

    
    /**
     * ��HQL��ҳ��ѯ.
     *
     * @param page ҳ�����
     * @param hql HQL���
     * @param values �ɱ�����б�
     * @return ��ҳ����
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
                logger.debug("��ʼ����ָ��HQL��ҳ����," + hqlResult);

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
                                logger.debug("����ָ��HQL��ҳ���ݳɹ�," + hqlResult);
                            }

                            //***�鿴�������/////
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
            logger.error("��ҳ��ѯ�쳣��HQL��" + hql, e);
            throw e;
        }
    }
    
    /**
		 * �鿴���е�ʵ��
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
     * ���ݲ�ѯ����������б���Query����
     *
     * @param session Hibernate�Ự
     * @param hql HQL���
     * @param objects �����б�
     * @return Query����
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
     * ���ݲ�ѯ����������б���SQL Query����
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
