/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.cuinfo.ModifyUserPasswordWin=new Ext.extend(Ext.Window,{
    urls:{
        modifyPassword:Ext.cuinfo.modules.UserCenter.baseUrl+'/modifyPassword.action'
    },
    initComponent:function(){
        this.formPanel=this.getFormPanel();
        this.items=[this.formPanel];
	
        var defaultConfig={
            width:400,
            height:180,
            title:'修改密码',
            layout:'fit',
            bodyStyle:'padding:10',
            closeAction:'hide',
            closeable:true,
            draggable:false,
            modal:true
        }
        Ext.applyIf(this,defaultConfig);
		
		
        Ext.cuinfo.ModifyUserPasswordWin.superclass.initComponent.call(this);
    },
    getFormPanel:function(){
        if(this.formPanel)
            return this.formPanel;
        var items=[
        {
            xtype:'textfield',
            allowBlank:false,
            fieldLabel:'请输入新密码',
            name:'user.loginPsw',
            inputType:'password'
    				
        },
        {
            xtype:'textfield',
            allowBlank:false,
            fieldLabel:'请再次输入新密码',
            name:'repassword',
            inputType:'password'
        }
        ];
        var buttons,saveBtn,resetBtn,backBtn;
        saveBtn={
            text:'保存',
            ref:'../saveBtn',
            scope:this,
            handler:this.preSaveForm
            };
        resetBtn={
            text:'重置',
            ref:'../resetBtn',
            scope:this,
            handler:this.resetForm
            };
        backBtn={
            text:'返回',
            ref:'../saveBtn',
            scope:this,
            handler:this.backForm
            };
        buttons=[saveBtn,resetBtn,backBtn]
        var formP;
        formP=new Ext.form.FormPanel({
            labelWidth : 108,
            //frame : true,
            header : false,
            bodyStyle : 'padding:15px 15px 0',
            defaults : {
                labelSeparator:':',
                width : 200
            },
            defaultType : 'textfield',
            items:items,
            buttonAlign:'center',
            buttons:buttons
        });
        return formP;
    },
    preSaveForm:function(){
        var form=this.formPanel.form;
        if(!form.isValid()){
            this.showMsg('警告','请填写完整表单！');
            return;
        }
    	
        var password=form.findField('user.loginPsw').getValue();
        var repassword=form.findField('repassword').getValue();
    	
        if(password==repassword){
            this.saveForm(password);
        }else{
            this.showMsg('错误','两次输入密码不一致!');
        }
    },
    saveForm:function(password){
        var action={};
        action.url=this.urls.modifyPassword;
        action.params={
          
            'user.loginPsw':password
        };
        this.actionHandler(action,this.saveFormSuccess,this);
    },
    saveFormSuccess:function(json){
        this.hide();
        this.showMsg('处理信息',json.message);
    	
    },
    resetForm:function(){
        this.formPanel.form.reset();
    },
    backForm:function(){
        this.hide();
    },
    
    afterRender:function(){
        Ext.cuinfo.ModifyUserPasswordWin.superclass.afterRender.call(this);
      this.fromMask = new Ext.LoadMask(this.getEl() , {msg:"数据处理中...."});
       
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
        Ext.Msg.alert(''+title,''+context);
    }
	
});
    

Ext.reg('usermanagerpsw',Ext.cuinfo.ModifyUserPasswordWin);

