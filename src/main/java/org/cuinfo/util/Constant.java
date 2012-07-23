/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.util;

/**
 *这个类存储了系统所有常量
 * @author Administrator
 */
public final class Constant {
    //////////////////////authority///////////////////////////////////////
	/***
	 * 用户登录检查，是否重新登录结果传回页面
	 */
	public final static String USER_LOGIN_RELOGIN_DISPATCHER_RESULT="reloginDispatcherResult";
	/***
	 * 用户登录检查，是否重新登录结果传回Json
	 */
	public final static String USER_LOGIN_RELOGIN_JSON_RESULT="reloginJsonResult";
	/***
	 * 用户登录检查，是否重新登录,结果要求ContextType(text/html)结果传回Json
	 */
	public static String USER_LOGIN_RELOGIN_JSON_WITH_CONTENT_TYPE_RESULT="reloginJsonWithContentTypeResult";
	
	
	/***
	 * 用户请求权限检测，无权限请求，结果传回页面
	 */
	public final static String AUTHORITY_CHECK_NO_AUTHORITY_DISPATCHER_RESULT="userNoAuthorityDispatcherResult";
	
	/***
	 * 用户请求权限检测，无权限请求,结果传回Json
	 */
	public final static String AUTHORITY_CHECK_NO_AUTHORITY_JSON_RESULT="userNoAuthorityJsonResult";
	/***
	 * 用户请求权限检测，无权限请求,要求ContextType(text/html)结果传回Json
	 */
	public final static String AUTHORITY_CHECK_NO_AUTHORITY_JSON_WITH_CONTENT_TYPE_RESULT="userNoAuthorityJsonWithContentTypeResult";
	
}
