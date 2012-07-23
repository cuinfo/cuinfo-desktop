/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.cuinfo.common.ProcessResult;
import org.cuinfo.exception.SystemException;
import org.cuinfo.pojo.User;
import org.cuinfo.util.UserList;
import org.cuinfo.util.UserVersion;

/**
 *
 * @author Administrator
 */
public class UseSystemAction extends BaseAction {

    private static Log log = LogFactory.getLog(UseSystemAction.class);
    public User user;
    /**
     * session 保持时间
     */
    private int maxInactiveInterval;

    /**
     * 登陆系统
     *
     * @return
     */
    public String login() {
        processResult = new ProcessResult(true);
        try {
            User u = this.useSystemService.checkUser(user);
            
            if(u!=null){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(this.maxInactiveInterval);

        UserList ul = UserList.getInstance();
        Enumeration<UserVersion> users = ul.getUserList();

        UserVersion currentVersion = null;
        Object o = ActionContext.getContext().getSession().get("currentVersion");
        if (o != null && o instanceof UserVersion) {
            currentVersion = (UserVersion) o;
        }
        UserVersion oldVersion = ul.getUserVersionByUserId(u.getId());


//        while (users.hasMoreElements()) {
//            UserVersion userVersion = users.nextElement();
//             
//            if (userVersion.getUser().getId().intValue() == u.getId().intValue()) {
//                oldVersion = userVersion;
//                break;
//            }
//        }

        if (oldVersion == null) {
            log.info("未登录用户!");
            if (currentVersion != null) {
                ul.removeUserVersion(currentVersion);
            }
            UserVersion newUserVersion = new UserVersion();
            newUserVersion.setUser(u);
            newUserVersion.setSessionId(request.getSession(true).getId());//采用用户的session Id限制一个用户登陆
            newUserVersion.setVersionNum(0);
            ActionContext.getContext().getSession()
                    .put("currentVersion", newUserVersion);

        } else if (oldVersion != null) {

            log.info("已登录用户!");
            UserVersion newUserVersion = new UserVersion();
            newUserVersion.setUser(u);
            newUserVersion.setSessionId(request.getSession(true).getId());
            newUserVersion.setVersionNum(oldVersion.getVersionNum() + 1);//版本+1
            ActionContext.getContext().getSession()
                    .put("currentVersion", newUserVersion);

        }

        processResult.setSuccess(true);
        processResult.setMessage(this.getText("login.success","login.success"));
        }
            
            
        } catch (SystemException e) {
            String errorMessage = getText(e.getExceptionMessage(), e.getExceptionMessage());
         //   log.error(errorMessage, e);
            processResult.setSuccess(false);
            processResult.setMessage(errorMessage);
        } catch (Exception e) {
            String errorMessage = getText("login.failure", "login failure!");
            log.error(errorMessage, e);
            processResult.setSuccess(false);
            processResult.setMessage(errorMessage);
        }


        

        return Action.SUCCESS;
    }

    /**
     * 退出系统
     *
     * @return
     */
    public String logout() {
        HttpServletRequest request = ServletActionContext.getRequest();
		if(request.isRequestedSessionIdValid()){
			request.getSession().invalidate();}
        return Action.SUCCESS;
    }

    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
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
