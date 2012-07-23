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
 * 基本信息
 *
 * @author Administrator
 */
public class BaseinforAction extends BaseAction {
   
   
    
    
    private static Log log = LogFactory.getLog(BaseinforAction.class);
    
    User user;
    List<User> users;

    public String saveUser() {
      
        //定义结果
        processResult = new ProcessResult(true);
        //调用业务层
        try {
            baseInformationService.save(user);
            //设置处理信息
            processResult.setMessage(getText("save.success", "save success!"));
        } catch (Exception e) { //捕捉所有异常
            log.error(getText("save.failure"), e);
            //设置处理信息，业务失败了
            processResult.setSuccess(false);
            processResult.setMessage(getText("save.failure", "save failure!"));
        }


        //永远返回Action处理成功
        return Action.SUCCESS;
    }

    public String updateUser() {
        //定义结果
        processResult = new ProcessResult(true);
        //调用业务层
        try {
            baseInformationService.update(user);
            //设置处理信息
            processResult.setMessage(getText("update.success", "update success!"));
            log.info(getText("update.success", "update success!"));
        } catch (Exception e) { //捕捉所有异常
            log.error(getText("update.failure", "update failure!"), e);
            //设置处理信息，业务失败了
            processResult.setSuccess(false);
            processResult.setMessage(getText("update.failure", "update failure!"));
        }


        //永远返回Action处理成功
        return Action.SUCCESS;
    }

    public String deleteUser() {
        //定义结果
        processResult = new ProcessResult(true);
        //调用业务层
        try {
            baseInformationService.delete(user);
            //设置处理信息
            processResult.setMessage(getText("delete.success", "delete success!"));
            log.info(getText("delete.success", "delete success!"));
        } catch (Exception e) { //捕捉所有异常
            //设置处理信息，业务失败了
            log.error(getText("delete.failure", "delete failure!"), e);
            processResult.setSuccess(false);
            processResult.setMessage(getText("delete.failure", "delete failure!"));
        }


        //永远返回Action处理成功
        return Action.SUCCESS;
    }

    public String viewAllUser() {
        //定义结果
        processResult = new ProcessResult(true);
        //调用业务层
        try {
           List<User> us= baseInformationService.getAll();
            //设置处理信息
           processResult.setResult(us);
            processResult.setMessage(getText("query.success", "query success!"));
            log.info(getText("query.success", "query success!"));
        } catch (Exception e) { //捕捉所有异常
            //设置处理信息，业务失败了
            log.error(getText("query.failure", "query failure!"), e);
            processResult.setSuccess(false);
            processResult.setMessage(getText("query.failure", "query failure!"));
        }


        //永远返回Action处理成功
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
