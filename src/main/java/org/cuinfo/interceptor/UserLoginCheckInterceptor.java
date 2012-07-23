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
 * �û���¼������ ��Ҫ���ܾ;����û���¼��Ч�Լ��<br/> �û�session��Ч�Լ��<br/>
 * �û���¼�汾��飬��Ҫ�Ƿ�ֹͬһ�˻������¼�����õķ����ǰ汾��ߵ�����Ч��¼��
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

        //Ĭ��Ϊ����,����������ҳ��
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



        //ƽ̨���°汾
        UserVersion currentVersion = null;
        //�û��汾
        UserVersion userVersion = null;





        ActionContext actionContext = invocation.getProxy().getInvocation().getInvocationContext();
        Object object = actionContext.getSession().get("currentVersion");

        processResult = new ProcessResult(false);
        //session ʧЧ
        if (object == null) {
            processResult.setRedirect(false);
            processResult.setRefresh(true);
            processResult.setMessage("��¼�û�ʧЧ�����¼");

            actionContext.put("processResult", processResult);
            invocation.getStack().push(processResult);
            invocation.getStack().set("processResult", processResult);
            return interceptorResult;
        }

        userVersion = (UserVersion) object;

      // System.out.println("version:" + userVersion.getVersionNum() + " name:" + userVersion.getUser().getLoginName());

        //��¼�汾���
        UserList ul = UserList.getInstance();
        currentVersion = ul.getUserVersionByUserId(userVersion.getUser().getId());
        if (userVersion.getVersionNum() != currentVersion.getVersionNum()) {

            processResult.setRedirect(false);
            processResult.setRefresh(true);
            processResult.setMessage("�û�������ص�¼!");

            actionContext.put("processResult", processResult);
            invocation.getStack().push(processResult);
            invocation.getStack().set("processResult", processResult);
           
            return interceptorResult;
        }

        //ִ��action

        return invocation.invoke();
    }
}
