<%-- 
    Document   : index
    Created on : 2012-7-20, 16:19:11
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="GBK"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/extjs/resources/css/ext-all.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/common/common.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/use-system/login.css" />

        <script type="text/javascript"	src="${pageContext.servletContext.contextPath}/extjs/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/extjs/ext-all.js"></script>			
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/extjs/ext-lang-zh_CN.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/common/common.js"></script>
        
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/use-system/full-screen.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/js/use-system/Login.js"></script>
        <script>
            Ext.onReady(function(){
                var mainPanel=new Ext.cuinfo.Login();
                var viewport=new Ext.Viewport({
                    layout:{
                        type:'vbox',
                        padding:'5',
                        pack:'center',
                        align:'center'
                    },
                    items:[mainPanel]
		 
                });
//                document.onkeydown = function(e) {
//		e = e ? e : window.event;
//		var keyCode = e.which ? e.which : e.keyCode;
//		if (keyCode == 13) {
//                    mainPanel.userLogin();
//                }}
                
            });
            
        </script>

       
        <title>œµÕ≥≤‚ ‘</title>
    </head>
    <body>
        
       
    </body>
</html>
