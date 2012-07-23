/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.dao;

import java.util.List;
import org.cuinfo.pojo.User;

/**
 *用户表接口
 * @author Administrator
 */
public interface UserDAO extends BaseDAO<User> {

    public List<User> viewByLoginName(User user);
    
}
