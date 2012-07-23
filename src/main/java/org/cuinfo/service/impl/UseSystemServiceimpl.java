/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.service.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cuinfo.exception.SystemException;
import org.cuinfo.pojo.User;
import org.cuinfo.service.UseSystemService;
import org.cuinfo.util.MyPassword;

/**
 *
 * @author Administrator
 */
public class UseSystemServiceimpl extends BaseServiceImpl implements UseSystemService {

    private static Log log = LogFactory.getLog(UseSystemServiceimpl.class);

    public User checkUser(User user) throws SystemException {
        if (user == null) {
            throw new SystemException("parameter.required");
        }
        if (user.getLoginName() == null || user.getLoginName().trim().equals("")) {
            throw new SystemException("loginUser.login.name.required");
        }

        if (user.getLoginPsw() == null || user.getLoginPsw().trim().equals("")) {
            throw new SystemException("loginUser.login.password.required");
        }

        List<User> users = this.userDAO.viewByLoginName(user);
        if (users.isEmpty()) {
            throw new SystemException("loginUser.name.noregister");//这个用户没有注册
        }
        for (User u : users) {
            try {
                String p = MyPassword.decode(u.getLoginPsw());//使用加密算法
                if (p.equals(user.getLoginPsw())) {
                    return u;
                }
            } catch (Exception e) {
                log.error(e);
            }
        }
        throw new SystemException("loginUser.password.error");//用户名密码错误
    }

    public void modifyPassword(User newUser, User oldUser) {
         if (newUser == null) {
            throw new SystemException("parameter.required");
        }
       
        if (newUser.getLoginPsw() == null || newUser.getLoginPsw().trim().equals("")) {
            throw new SystemException("loginUser.login.password.required");
        }
        
        oldUser.setLoginPsw(encodePsw(newUser).getLoginPsw());
        this.userDAO.update(oldUser);
        
    }
}
