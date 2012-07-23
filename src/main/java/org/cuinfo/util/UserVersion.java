/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.util;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import org.cuinfo.pojo.User;
/***
 * 用户登录版本检查
 * @author Administrator
 *
 */
public class UserVersion implements HttpSessionBindingListener{
	private User user;
	private String sessionId;
	private int versionNum;
	
	private UserList ul=UserList.getInstance();
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public void valueBound(HttpSessionBindingEvent event) {
	
		ul.addUserVersion(this);
		
	}
	public void valueUnbound(HttpSessionBindingEvent event) {
		ul.removeUserVersion(this);
	}
	
	public UserList getUl() {
		return ul;
	}
	public void setVersionNum(int versionNum) {
		
		this.versionNum = versionNum;
	}
	public int getVersionNum() {
		return versionNum;
	}
}
