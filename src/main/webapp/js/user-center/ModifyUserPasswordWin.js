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
            title:'�޸�����',
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
            fieldLabel:'������������',
            name:'user.loginPsw',
            inputType:'password'
    				
        },
        {
            xtype:'textfield',
            allowBlank:false,
            fieldLabel:'���ٴ�����������',
            name:'repassword',
            inputType:'password'
        }
        ];
        var buttons,saveBtn,resetBtn,backBtn;
        saveBtn={
            text:'����',
            ref:'../saveBtn',
            scope:this,
            handler:this.preSaveForm
            };
        resetBtn={
            text:'����',
            ref:'../resetBtn',
            scope:this,
            handler:this.resetForm
            };
        backBtn={
            text:'����',
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
            this.showMsg('����','����д��������');
            return;
        }
    	
        var password=form.findField('user.loginPsw').getValue();
        var repassword=form.findField('repassword').getValue();
    	
        if(password==repassword){
            this.saveForm(password);
        }else{
            this.showMsg('����','�����������벻һ��!');
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
        this.showMsg('������Ϣ',json.message);
    	
    },
    resetForm:function(){
        this.formPanel.form.reset();
    },
    backForm:function(){
        this.hide();
    },
    
    afterRender:function(){
        Ext.cuinfo.ModifyUserPasswordWin.superclass.afterRender.call(this);
      this.fromMask = new Ext.LoadMask(this.getEl() , {msg:"���ݴ�����...."});
       
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
	 * @param {Object} successFn ҵ��ɹ�Fn
	 * @memberOf {TypeName} 
	 * @return {TypeName} 
	 */
    actionHandler:function(action,successFn,scope){
        if(!action||!action.url){
            scope.showMsg('����','�벻Ҫ�ṩ��������������');
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
                //��¼��鷵�ؽ��
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
                                scope.showMsg("��Ϣ",json.message);
                            }
                        }else{
                            scope.showMsg("��Ϣ",json.message);
                        }
								
                    }else{
                        scope.showMsg("��Ǹ�������쳣���ݣ�",result.jsonStr);	
                    }
                }
			 
            },
            failure:function(res,op){
                //this.showMsg("��Ϣ","��������ʧ���ˣ�,��ˢ��ҳ�汣֤��������ȷ�ԣ�<br/>���ش��룺"+res.status+"<br/>Text:"+res.statusText);	
                scope.hideMask();			
                scope.showMsg("��Ϣ","����ʧ�ܣ�(status code "+ res.status+")f");	

            }
        });	
		
    },
    showMsg:function(title,context){
        Ext.Msg.alert(''+title,''+context);
    }
	
});
    

Ext.reg('usermanagerpsw',Ext.cuinfo.ModifyUserPasswordWin);

