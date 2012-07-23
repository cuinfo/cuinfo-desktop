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

        User u1 = new User("ϸϸ1", "123");
        u1.setSex(1);
        User u2 = new User("ϸϸ2", "123");
        u2.setSex(0);
        List<User> us = bis.getAll();
        LOG.log(Level.INFO, "========================����ǰ��¼��" + us.size() + "����=========================");
        for (User u : us) {
            print(u);
        }
        
        LOG.log(Level.INFO, "================================��ʼ�����¼=============================");
        bis.save(u1);
        bis.save(u2);
        LOG.log(Level.INFO, "=============================��ɲ����¼=======================================");

        us = bis.getAll();
        LOG.log(Level.INFO, "====================������¼��" + us.size() + "����===========================");
        for (User u : us) {
            print(u);
        }

        LOG.log(Level.INFO, "============================ɾ����¼===================================");
        bis.delete(u1);
        bis.delete(u2);
        LOG.log(Level.INFO, "==============================���ɾ����=====================");

        us = bis.getAll();
        LOG.log(Level.INFO, "====================ɾ�����¼��" + us.size() + "����===========================");
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
            throw e;  //���ʱ������ǰ��쳣�׳�ȥ����Ϊ���ֻ�ǻع��Ļ�����û���κ���ʾ�������ߡ�
        } finally {
            session.close();
        }
    }
}
