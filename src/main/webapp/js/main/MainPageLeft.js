/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

Ext.cuinfo.MainPageLeft=Ext.extend(Ext.Panel, {
    initComponent : function(){
        
        var items=this.initItmes();
		
        var defConfig = {
            plain: true,
            margins:'5 0 5 5',
            layout:'accordion',
            border: false,
            items:items
        };
        Ext.applyIf(this,defConfig);
	   
        
        Ext.cuinfo.MainPageLeft.superclass.initComponent.call(this);        

    },
    initItmes:function(){
		
        //用户中心
        var userCenter=this.getUserCenter();
		
		
        return [userCenter];
		
    },
	
	
    /***
    * 用户中心
    * 
    */
    getUserCenter:function(){
        var icons=this.getIconClass();
        var moduleName="用户中心";
        var userCenter=new Ext.tree.TreeNode({
            iconCls:icons.node,
            text: moduleName, 
            leaf: "false"
        });
        //用户管理
        var system_userCenter_index=new Ext.tree.TreeNode({
            iconCls:icons.node,
            text: "用户管理", 
            url:Ext.cuinfo.modules.UserCenter.baseUrl+ "/index.action"
        });
		
        userCenter.appendChild([system_userCenter_index]);
		
        return this.getNewTreePanel(userCenter,moduleName);
    },
	
    getIconClass:function(){
        return {
            iconItem:'icon_item',
            node:'tree_node'
        };
    },
	
	
    getNewTreePanel:function(root,title){
        var icons=this.getIconClass();
		
        var tp=new Ext.tree.TreePanel({
            iconCls:icons.iconItem,
            title: title,
            rootVisible: false,
            listeners: {
                click: {
                    scope:this,
                    fn:this.treeClickProcess
                }
            }
        });
		
        tp.setRootNode(root);
		
        return tp;
		
    },
	
    treeClickProcess:function(node){
        this.fireEvent("treeNodeClick",node);
    },
	
	
	
    afterRender : function(){
        Ext.cuinfo.MainPageLeft.superclass.afterRender.call(this); 
        this.addEvents('treeNodeClick');
		
    }
});

Ext.reg('cuinforMianPageLeft',Ext.cuinfo.MainPageLeft);
