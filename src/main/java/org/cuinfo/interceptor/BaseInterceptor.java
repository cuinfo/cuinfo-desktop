/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.interceptor;

import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.cuinfo.common.ProcessResult;

/**
 * *
 * action 拦截器接口基类，负责业务属性注入
 *
 * @author Administrator
 *
 */
public abstract class BaseInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;
    ProcessResult processResult;

    public ProcessResult getProcessResult() {
        return processResult;
    }

    public void setProcessResult(ProcessResult processResult) {
        this.processResult = processResult;
    }
}
