<%-- 
    Document   : index
    Created on : 2012-7-18, 11:14:47
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="GBK"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/extjs/resources/css/ext-all.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/common/common.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/user-center/user-manager.css" />
        
        
        <script type="text/javascript"	src="${pageContext.servletContext.contextPath}/extjs/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/extjs/ext-all.js"></script>			
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/extjs/ext-lang-zh_CN.js"></script>
         <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/common/common.js"></script>
       
         <script type="text/javascript"	src="${pageContext.servletContext.contextPath}/js/user-center/UserManager.js"></script>
         <script type="text/javascript"	src="${pageContext.servletContext.contextPath}/js/user-center/ModifyUserPasswordWin.js"></script>

         <script type="text/javascript">
	Ext.onReady(function(){
	var view=	new Ext.Viewport({
		
		layout:'fit',
		items:[{
		
			xtype:'usermanager'
			
		}]
		
	});
	
	
	
	});
</script>
        <title>用户管理</title>
    </head>
    <body>

    </body>
</html>
