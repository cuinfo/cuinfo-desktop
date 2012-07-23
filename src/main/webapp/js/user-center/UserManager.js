/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

Ext.cuinfo.UserManager=Ext.extend(Ext.Panel,{
    imageDatas:[
    {
        id:'modifyPassword',
        name:'修改密码',
        url:Ext.cuinfo.webRoot+'/resources/images/default/base-infor/modifypassword.png'
    },

    {
        id:'contactus',
        name:'关于我们',
        url:Ext.cuinfo.webRoot+'/resources/images/default/base-infor/contact.gif'
    }

    ],
	
    initComponent:function(){
        //data view
        this.dataView=this.getDataView();
        //css class
		
        this.bodyCfg= {
            tag: 'center',
            cls: 'user-manager body-bk-class'
        },
		
        this.items=[this.dataView];
		
	
        Ext.cuinfo.UserManager.superclass.initComponent.call(this);
    },
    getDataView:function(){
        if(this.dataView)
            return this.dataView;
        if(!this.imageDatas)
            this.imageDatas=[];
        var imageDatas={
            images:this.imageDatas
        }
    
    
        var store =new Ext.data.JsonStore({
            root: 'images',
            id:'name',
            fields:[
            'id','name', 'url' 
            ]
        });
        store.loadData(imageDatas); 
		
        var 	listeners ={
            click:{
                scope:this,
                fn:function(d,index,node,e){
                    d.select(index);
                }
            },
            dblclick:{
                scope:this,
                fn:this.menuDblClickHandler
            }
        };
		
        var dataView=new Ext.DataView({
			
            listeners:listeners,
            itemSelector: 'div.thumb-wrap',
            style:'overflow:auto',
            multiSelect: true,
            // plugins: new Ext.DataView.DragSelector({dragSafe:true}),
            store:store ,
            tpl: new Ext.XTemplate(
                '<tpl for=".">',
                '<div class="thumb-wrap" id="{id}">',
                '<div class="thumb"><img src="{url}" class="thumb-img">',
                '</div>',
                '<span>{name}</span></div>',
                '<tpl if="xindex % 2 == 0"><div style="clear:both;"></div></tpl>',
                '</tpl>'
                )
        });
		
        return dataView;
		
    },
    menuDblClickHandler:function(d,index,node,e){
        var r=d.getRecord(node);
        //修改密码
        if(r.get('id')=='modifyPassword'){
            this.showModifyPasswordWin();	
        }
        else if(r.get('id')=='contactus'){
            this.showContactUsWin();	
        }
    	   	
    },
    showModifyPasswordWin:function(){
        if(console)
        console.log("modifyPassword");
        if(!this.modifyPasswordWin)
            this.modifyPasswordWin=new Ext.cuinfo.ModifyUserPasswordWin({
                closeAction:'hide',
                closeable:true
            });
        	
        this.modifyPasswordWin.resetForm();
        this.modifyPasswordWin.show();
    },
    showContactUsWin:function(){
        console.log("contact");
    //    	if(!this.contactUsWin)
    //    		this.contactUsWin=new Ext.cgit.ContactUsWin({
    //    			closeAction:'hide',
    //				closeable:true
    //    		});
    //    	
    //    	
    //    	this.contactUsWin.show();
    }
    
    



});
Ext.reg('usermanager',Ext.cuinfo.UserManager);


