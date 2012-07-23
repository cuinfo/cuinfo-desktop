/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cuinfo.pojo.User;
import org.cuinfo.service.BaseInformationService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Administrator
 */
public class Test {

    private static final Logger LOG = Logger.getLogger(Test.class.getName());

    public static void main(String[] args) {

        testService();
    }

    static void testService() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                    "appliaction-context.xml", "dao-context.xml", "service-context.xml"
                });



        BaseInformationService bis = (BaseInformationService) context.getBean("baseInformationService");

        User u1 = new User("细细1", "123");
        u1.setSex(1);
        User u2 = new User("细细2", "123");
        u2.setSex(0);
        List<User> us = bis.getAll();
        LOG.log(Level.INFO, "========================插入前记录：" + us.size() + "条！=========================");
        for (User u : us) {
            print(u);
        }
        
        LOG.log(Level.INFO, "================================开始插入记录=============================");
        bis.save(u1);
        bis.save(u2);
        LOG.log(Level.INFO, "=============================完成插入记录=======================================");

        us = bis.getAll();
        LOG.log(Level.INFO, "====================插入后记录：" + us.size() + "条！===========================");
        for (User u : us) {
            print(u);
        }

        LOG.log(Level.INFO, "============================删除记录===================================");
        bis.delete(u1);
        bis.delete(u2);
        LOG.log(Level.INFO, "==============================完成删除记=====================");

        us = bis.getAll();
        LOG.log(Level.INFO, "====================删除后记录：" + us.size() + "条！===========================");
        for (User u : us) {
            print(u);
        }
    }

    static void print(Object o) {
        System.out.println(o);
    }

    static void addPerson(User person) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(person);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;  //这个时候最好是把异常抛出去，因为如果只是回滚的话，就没有任何提示给调用者。
        } finally {
            session.close();
        }
    }
}
