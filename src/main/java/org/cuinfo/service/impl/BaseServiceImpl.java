/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.cuinfo.dao.UserDAO;
import org.cuinfo.pojo.User;
import org.cuinfo.util.MyPassword;

/**
 *业务层基本类，提供数据口，数据口接口由Spring依赖注入
 * @author Administrator
 */
public class BaseServiceImpl {
    UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    //加密
    public User encodePsw(User user) {
        if (user == null || user.getLoginPsw() == null || user.getLoginPsw().isEmpty()) {
            return user;
        }
        try {
            user.setLoginPsw(MyPassword.encode(user.getLoginPsw()));
        } catch (Exception e) {
        }
        return user;
    }
    //加密

    public List<User> encodePsw(List<User> users) {
        if (users == null || users.isEmpty()) {
            return users;
        }
        List<User> result = new ArrayList<User>();
        for (User u : users) {

            u = encodePsw(u);

            result.add(u);
        }

        return result;
    }
    //解密

    public User decodePsw(User user) {
        if (user == null || user.getLoginPsw() == null || user.getLoginPsw().isEmpty()) {
            return user;
        }
        try {
            user.setLoginPsw(MyPassword.decode(user.getLoginPsw()));
        } catch (Exception e) {
        }
        return user;
    }
    //解密

    public List<User> decodePsw(List<User> users) {
        if (users == null || users.isEmpty()) {
            return users;
        }
        List<User> result = new ArrayList<User>();
        for (User u : users) {

            u = decodePsw(u);

            result.add(u);
        }

        return result;
    }
    
}
