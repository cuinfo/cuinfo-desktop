/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.service.impl;

import java.util.List;
import java.util.logging.Logger;
import org.cuinfo.pojo.User;
import org.cuinfo.service.BaseInformationService;

public class BaseInformationServiceImpl extends BaseServiceImpl implements BaseInformationService {

    private static final Logger LOG = Logger.getLogger(BaseInformationServiceImpl.class.getName());

    /**
     * ��������û�
     *
     * @return ��ü�������
     */
    public List<User> getAll() {
        return decodePsw(this.userDAO.viewAll());
    }

    /**
     * ����һ���û���Ϣ
     *
     * @param user
     */
    public void save(User user) {
        if (user == null) {
            return;
        }


        this.userDAO.save(encodePsw(user));
    }

    /**
     * ����һ���û���Ϣ
     *
     * @param user
     */
    public void update(User user) {
        if (user == null) {
            return;
        }


        this.userDAO.update(encodePsw(user));
    }

    /**
     * ɾ��һ���û���Ϣ
     *
     * @param user
     */
    public void delete(User user) {
        if (user == null) {
            return;
        }
        this.userDAO.delete(user);
    }

    /**
     * �鿴һ���û���Ϣ
     *
     * @param user
     */
    public User view(User user) {
        if (user == null) {
            return null;
        }
        return decodePsw(this.userDAO.view(user.getId()));
    }

    
}
