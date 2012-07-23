/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

Ext.cuinfo.ReLogin=new Ext.extend(Ext.cuinfo.Login,{
    
    initComponent:function(){
        Ext.cuinfo.ReLogin.superclass.initComponent.call(this);
    },
    userLoginSuccess:function(json){
        location.reload(true);
    }
});
Ext.reg( "relogin",Ext.cuinfo.ReLogin);