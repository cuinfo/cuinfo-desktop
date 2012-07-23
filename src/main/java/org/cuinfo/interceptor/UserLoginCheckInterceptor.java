/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ResultConfig;
import java.util.Map;
import org.cuinfo.common.ProcessResult;
import org.cuinfo.util.Constant;
import org.cuinfo.util.UserList;
import org.cuinfo.util.UserVersion;

/**
 * *
 * 用户登录拦截器 主要功能就就是用户登录有效性检查<br/> 用户session有效性检查<br/>
 * 用户登录版本检查，主要是防止同一账户多个登录。采用的方法是版本最高的是有效登录！
 *
 * @author Administrator
 *
 */
public class UserLoginCheckInterceptor extends BaseInterceptor {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        //默认为请求,拦截器返回页面
        String interceptorResult = Constant.USER_LOGIN_RELOGIN_DISPATCHER_RESULT;

        Map<String, ResultConfig> maps = invocation.getProxy().getConfig().getResults();

        if (maps.containsKey("success")) {
            ResultConfig rc = maps.get("success");
            if (rc.getClassName().equals("org.apache.struts2.json.JSONResult")) {
                interceptorResult = Constant.USER_LOGIN_RELOGIN_JSON_RESULT;
                Map<String, String> params = rc.getParams();
                if (params.get("contentType") != null) {
                    interceptorResult = Constant.USER_LOGIN_RELOGIN_JSON_WITH_CONTENT_TYPE_RESULT;
                }
            }
        };
        //System.out.println(interceptorResult);



        //平台最新版本
        UserVersion currentVersion = null;
        //用户版本
        UserVersion userVersion = null;





        ActionContext actionContext = invocation.getProxy().getInvocation().getInvocationContext();
        Object object = actionContext.getSession().get("currentVersion");

        processResult = new ProcessResult(false);
        //session 失效
        if (object == null) {
            processResult.setRedirect(false);
            processResult.setRefresh(true);
            processResult.setMessage("登录用户失效，请登录");

            actionContext.put("processResult", processResult);
            invocation.getStack().push(processResult);
            invocation.getStack().set("processResult", processResult);
            return interceptorResult;
        }

        userVersion = (UserVersion) object;

      // System.out.println("version:" + userVersion.getVersionNum() + " name:" + userVersion.getUser().getLoginName());

        //登录版本检查
        UserList ul = UserList.getInstance();
        currentVersion = ul.getUserVersionByUserId(userVersion.getUser().getId());
        if (userVersion.getVersionNum() != currentVersion.getVersionNum()) {

            processResult.setRedirect(false);
            processResult.setRefresh(true);
            processResult.setMessage("用户已在异地登录!");

            actionContext.put("processResult", processResult);
            invocation.getStack().push(processResult);
            invocation.getStack().set("processResult", processResult);
           
            return interceptorResult;
        }

        //执行action

        return invocation.invoke();
    }
}
