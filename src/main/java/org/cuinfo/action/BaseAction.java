/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Enumeration;
import org.cuinfo.common.ProcessResult;
import org.cuinfo.pojo.User;
import org.cuinfo.service.BaseInformationService;
import org.cuinfo.service.UseSystemService;
import org.cuinfo.util.UserList;
import org.cuinfo.util.UserVersion;

/**
 * Action 基类提供业务层接口
 *
 * @author Administrator
 */
public abstract class BaseAction extends ActionSupport {

    ProcessResult processResult;
    /**
     * 基本信息业务层接口
     */
    BaseInformationService baseInformationService;
    /**
     * 使用系统
     */
    UseSystemService useSystemService;

    public void setBaseInformationService(BaseInformationService baseInformationService) {
        this.baseInformationService = baseInformationService;
    }

    public void setUseSystemService(UseSystemService useSystemService) {
        this.useSystemService = useSystemService;
    }

    /**
     * 统一格式的业务处理结果，利于Json格式传输数据
     */
    public abstract ProcessResult getProcessResult();

    /**
     * 统一格式的业务处理结果
     */
    public abstract void setProcessResult(ProcessResult processResult);

    /**
     * 从session中获取当前平台用户
     *
     * @return
     */
    public User getCurrentUser() {
        User user = null;

        Object o = ActionContext.getContext().getSession().get("currentVersion");
        UserVersion currentVersion = null;

        UserList ul = UserList.getInstance();
        Enumeration<UserVersion> userVersions = ul.getUserList();

        if (o != null && o instanceof UserVersion) {
            currentVersion = (UserVersion) o;
        }

        while (userVersions.hasMoreElements()) {
            UserVersion uv = userVersions.nextElement();
            if (uv.getUser().getId() == currentVersion.getUser().getId()) {
                if (uv.getVersionNum() > currentVersion.getVersionNum()) {
                    return null;
                }
            }
        }


        if (currentVersion != null) {

            user = currentVersion.getUser();
        }


        return user;
    }
}
