/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.service.impl;

import org.cuinfo.pojo.User;
import org.cuinfo.service.BaseInformationService;
import org.cuinfo.service.UseSystemService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Administrator
 */
public class UseSystemServiceimplTest {
    
    public UseSystemServiceimplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
    UseSystemService useSystemService;
     BaseInformationService bis;
    @Before
    public void setUp() {
        String baseDir="";
          ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                   baseDir+ "appliaction-context.xml",  baseDir+"dao-context.xml", baseDir+ "service-context.xml"
                });
          
          useSystemService=(UseSystemService)context.getBean("useSystemService");
          bis = (BaseInformationService) context.getBean("baseInformationService");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkUser method, of class UseSystemServiceimpl.
     */
    @Test
    public void testCheckUser() {
      
//        bis.save(  new User("admin", "admin"));
//        try{
//       User user= useSystemService.checkUser(new User("admin", "admin"));
//        System.out.println(user.getLoginName());
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }

    /**
     * Test of modifyPassword method, of class UseSystemServiceimpl.
     */
    @Test
    public void testModifyPassword() {
        
    }
}
