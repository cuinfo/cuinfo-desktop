/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.action;

import com.opensymphony.xwork2.Action;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cuinfo.common.ProcessResult;
import org.cuinfo.exception.SystemException;
import org.cuinfo.pojo.User;

/**
 *
 * @author Administrator
 */
public class UserCenterAction extends BaseAction {

    private static Log log = LogFactory.getLog(UseSystemAction.class);
   
   private User user;

    /**
     * ÐÞ¸ÄÃÜÂë
     *
     * @return
     */
    public String modifyPassword() {
       
    
        try {

            processResult = new ProcessResult(true);
            this.useSystemService.modifyPassword(this.user, this.getCurrentUser());
            processResult.setMessage(this.getText("update.success", "update.success"));

        } catch (SystemException e) {
             String message = this.getText(e.getExceptionMessage(), e.getExceptionMessage());
             log.error(message, e);
             processResult.setSuccess(false);
              processResult.setMessage(message);
        } catch (Exception e) {
            String message = this.getText("update.failure", "update.failure");
            log.error(message, e);
             processResult.setSuccess(false);
            processResult.setMessage(message);
        }

        return Action.SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
