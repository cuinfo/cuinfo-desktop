/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.interceptor;

import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.cuinfo.common.ProcessResult;

/**
 * *
 * action �������ӿڻ��࣬����ҵ������ע��
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
