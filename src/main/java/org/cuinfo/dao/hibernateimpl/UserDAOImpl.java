/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.dao.hibernateimpl;

import java.util.List;
import org.cuinfo.dao.UserDAO;
import org.cuinfo.pojo.User;

/**
 *
 * @author Administrator
 */
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO { 
    /**
     * ���ݵ�½�û������û��б�
     * @param user
     * @return 
     */
    public List<User> viewByLoginName(User user) {
      return this.find(this.getSql("User.viewByLoginName"), user.getLoginName());
    }

   
}
