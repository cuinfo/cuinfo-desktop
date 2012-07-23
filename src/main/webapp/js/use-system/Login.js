/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.cuinfo.Login=new Ext.extend(Ext.Panel,{
    urls:{
        login:Ext.cuinfo.modules.UseSystem.baseUrl+"/login.action"
        ,main:Ext.cuinfo.modules.Main.baseUrl+"/index.action"
  
    },
    initComponent:function(){
	
        this.headerPic=this.getPicPanel();
        this.centerPanel=this.getCenterPanel();
        this.southPanel=this.getSouthPanel();
        
        this.items=[this.headerPic,this.centerPanel,this.southPanel];
        var defaultConfig={
            
            layout:'border',
            bodyStyle:'background-color:#CCD9E8;',
            width:600,
            height:365	
        }
        Ext.applyIf(this,defaultConfig);
		
		
        Ext.cuinfo.Login.superclass.initComponent.call(this);
        
        this.addEvents("keydown");
        this.addListener( "keydown", this.userLogin, this );
    },
    getPicPanel:function(){
        if(this.headerPic) 
            return this.headerPic;
        
        var tpl='<div>'
        +'<div id="image-bg"><img src="'+Ext.cuinfo.webRoot+'/resources/images/default/use-system/loginpic.jpg" width ="600" height="250"></div>'
        +'<div id="title1">系统技术测试中间</div>'
        +' <div id="title2">后台管理V1.0</div>'
        +'</div>'
        
        return new Ext.Panel({
            region:'north',
            height:250,
            html:tpl
			  
        });
    },
    getCenterPanel:function(){
        if(this.centerPanel) 
            return this.centerPanel;
        this.formPanel=this.getFormPanel();
        
        var cfg={
            layoutConfig: {
                padding:'10',
                pack:'center',
                align:'middle'
            },
            region:'center',
            layout:'hbox',
            border:false,
            bodyBorder:false,
            bodyStyle:'background-color:#CCD9E8',
            defaults:{
                margins:'0 5 0 0'
            },
            items:[this.formPanel]
        };
        
        
        
        
        return new Ext.Panel(cfg);
    },
    getSouthPanel:function(){
        if(this.southPanel) 
            return this.southPanel;
        return new Ext.Panel({
            region:'south',
            border:false,
            bodyBorder:false,
            bodyStyle:'background-color:#CCD9E8;padding:10px;',
            height:10
        });
         
    },
    getFormPanel:function(){
        
       
        
        return new Ext.form.FormPanel({
            bodyStyle:'background-color:#CCD9E8',
            border:false,
            bodyBorder:false,
            labelAlign :"right",
            labelPad:10,
            defaults:{
                labelStyle: 'font-weight:bold;'
            },
            items:[{
                    
                xtype:'textfield',
                baseCls:"x-plain",
                name:'user.loginName',
                fieldLabel:'登陆名称',
                labelAlign:'center',
                emptyText:'请输入用户名！',      
                height:20,
                allowBlank:false,
                enableKeyEvents:true,
                listeners:{
                    scope:this,
                    "keydown":this.enterPress
                }
            },{
                xtype:'textfield',
                inputType:'password',
                   
                   
                baseCls:"x-plain",
                name : 'user.loginPsw',
                height:20,
                emptyText:'请输入登录密码！',
                fieldLabel:'登陆密码',
                allowBlank:false,
                enableKeyEvents:true,
                listeners:{
                    scope:this,
                    "keydown":this.enterPress
                }
            }],
            monitorValid:true,
            buttonAlign:'center',
            buttons:[
            {
                formBind: true ,
                text:'登陆',
                scope:this,
                handler:this.userLogin
            },
            {
                text:'重置',
                scope:this,
                handler:this.formReset
            }]
        });
    },
    enterPress:function(o,e){
        if(e.getKey() ==13){
            this.userLogin();
        }
    },
    userLogin:function(){
        
        var form=this.formPanel.getForm();
        if(!form.isValid()){
            this.showMsg("警告","用户或密码不能为空！");
            return;
        }
        var action={};
    	action.url=this.urls.login;
    	action.params={'user.loginPsw':form.findField("user.loginPsw").getValue()
            ,'user.loginName':form.findField("user.loginName").getValue()
        };
        
        
      //  this.fromMask = new Ext.LoadMask(formPanel.body, {msg:"正在登陆"});
      this.showMask();
    	this.actionHandler(action,this.userLoginSuccess,this);
    },
    
    formReset:function(){
        this.formPanel.getForm().reset();
    },
    
    userLoginSuccess:function(json){
//        this.hide();
//    	this.showMsg('处理信息',json.message.result);
   // OpenFullWin(Ext.cuinfo.modules.Main.baseUrl+"/index.action");//full-screen.js
   
       OpenFullWin(this.urls.main);//full-screen.js 
    },
    
    afterRender:function(){
        Ext.cuinfo.Login.superclass.afterRender.call(this);
      this.fromMask = new Ext.LoadMask(this.getEl() , {msg:"正在登陆...."});
       
    },
    showMask:function(){
         this.fromMask.show();
    },
    hideMask:function(){
        this.fromMask.hide();
    },
    /***
	 * 
	 * @param {Object} action
	 * @param {Object} successFn 业务成功Fn
	 * @memberOf {TypeName} 
	 * @return {TypeName} 
	 */
    actionHandler:function(action,successFn,scope){
        if(!action||!action.url){
            scope.showMsg('警告','请不要提供错误的请求参数！');
            return;
        }else{
            if(!action.params){
                action.params='';
            }
        }
		
        Ext.Ajax.request({
            scope:scope,
            url:action.url,
            params:action.params,
            successFn:successFn,
            success:function(res,op){
			 scope.hideMask();		
			 
                //Ext.cgit.RefreshLoaction(res);
                //登录检查返回结果
                if(!Ext.cuinfo.RefreshLoaction(res)){
                    var result= Ext.cuinfo.ParseResult(res.responseText);
                    var json;
                    if(result.parseState){
                        json=result.json;
                    }
                    if(json){
							 
                        if(json.success){
                            if(op.successFn)
                                op.successFn.defer(10,scope,[json]);
                            else{
                                scope.showMsg("信息",json.message);
                            }
                        }else{
                            scope.showMsg("信息",json.message);
                        }
								
                    }else{
                        scope.showMsg("抱歉！返回异常数据！",result.jsonStr);	
                    }
                }
			 
            },
            failure:function(res,op){
                //this.showMsg("信息","操作可能失败了！,请刷新页面保证操作的正确性！<br/>返回代码："+res.status+"<br/>Text:"+res.statusText);	
		 scope.hideMask();			
                scope.showMsg("信息","请求失败！(status code "+ res.status+")f");	

            }
        });
		
		
		
		
    },
    
    
    showMsg:function(title,context){
        Ext.Msg.alert(title,context);
    }
});
Ext.reg( "login",Ext.cuinfo.Login);

