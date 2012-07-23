/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.service.impl;

import org.cuinfo.service.BaseInformationService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Administrator
 */
public class BaseInformationServiceImplTest {
    BaseInformationService bis;
    public BaseInformationServiceImplTest() {
    }
    
    @Before
    public void setUp() {
        
//        String baseDir="";
//          ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
//                   baseDir+ "appliaction-context.xml",  baseDir+"dao-context.xml", baseDir+ "service-context.xml"
//                });
//
//
//
//      bis = (BaseInformationService) context.getBean("baseInformationService");
    }

    /**
     * Test of getAll method, of class BaseInformationServiceImpl.
     */
    @Test
    public void testGetAll() {
       // System.out.println(bis.getAll().size());
    }

    /**
     * Test of save method, of class BaseInformationServiceImpl.
     */
    @Test
    public void testSave() {
        
    }

    /**
     * Test of update method, of class BaseInformationServiceImpl.
     */
    @Test
    public void testUpdate() {
    }

    /**
     * Test of delete method, of class BaseInformationServiceImpl.
     */
    @Test
    public void testDelete() {
    }

    /**
     * Test of view method, of class BaseInformationServiceImpl.
     */
    @Test
    public void testView() {
    }
}
