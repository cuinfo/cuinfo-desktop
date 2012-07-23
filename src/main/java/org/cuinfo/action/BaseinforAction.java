/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.action;

import com.opensymphony.xwork2.Action;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cuinfo.common.ProcessResult;
import org.cuinfo.pojo.User;

/**
 * ������Ϣ
 *
 * @author Administrator
 */
public class BaseinforAction extends BaseAction {
   
   
    
    
    private static Log log = LogFactory.getLog(BaseinforAction.class);
    
    User user;
    List<User> users;

    public String saveUser() {
      
        //������
        processResult = new ProcessResult(true);
        //����ҵ���
        try {
            baseInformationService.save(user);
            //���ô�����Ϣ
            processResult.setMessage(getText("save.success", "save success!"));
        } catch (Exception e) { //��׽�����쳣
            log.error(getText("save.failure"), e);
            //���ô�����Ϣ��ҵ��ʧ����
            processResult.setSuccess(false);
            processResult.setMessage(getText("save.failure", "save failure!"));
        }


        //��Զ����Action����ɹ�
        return Action.SUCCESS;
    }

    public String updateUser() {
        //������
        processResult = new ProcessResult(true);
        //����ҵ���
        try {
            baseInformationService.update(user);
            //���ô�����Ϣ
            processResult.setMessage(getText("update.success", "update success!"));
            log.info(getText("update.success", "update success!"));
        } catch (Exception e) { //��׽�����쳣
            log.error(getText("update.failure", "update failure!"), e);
            //���ô�����Ϣ��ҵ��ʧ����
            processResult.setSuccess(false);
            processResult.setMessage(getText("update.failure", "update failure!"));
        }


        //��Զ����Action����ɹ�
        return Action.SUCCESS;
    }

    public String deleteUser() {
        //������
        processResult = new ProcessResult(true);
        //����ҵ���
        try {
            baseInformationService.delete(user);
            //���ô�����Ϣ
            processResult.setMessage(getText("delete.success", "delete success!"));
            log.info(getText("delete.success", "delete success!"));
        } catch (Exception e) { //��׽�����쳣
            //���ô�����Ϣ��ҵ��ʧ����
            log.error(getText("delete.failure", "delete failure!"), e);
            processResult.setSuccess(false);
            processResult.setMessage(getText("delete.failure", "delete failure!"));
        }


        //��Զ����Action����ɹ�
        return Action.SUCCESS;
    }

    public String viewAllUser() {
        //������
        processResult = new ProcessResult(true);
        //����ҵ���
        try {
           List<User> us= baseInformationService.getAll();
            //���ô�����Ϣ
           processResult.setResult(us);
            processResult.setMessage(getText("query.success", "query success!"));
            log.info(getText("query.success", "query success!"));
        } catch (Exception e) { //��׽�����쳣
            //���ô�����Ϣ��ҵ��ʧ����
            log.error(getText("query.failure", "query failure!"), e);
            processResult.setSuccess(false);
            processResult.setMessage(getText("query.failure", "query failure!"));
        }


        //��Զ����Action����ɹ�
        return Action.SUCCESS;
    }

  

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    
    @Override
    public ProcessResult getProcessResult() {
        return this.processResult;
    }

    @Override
    public void setProcessResult(ProcessResult processResult) {
        this.processResult = processResult;
    }
}
