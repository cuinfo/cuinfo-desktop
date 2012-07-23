/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.onReady(function(){
	var view=new Ext.Viewport({
		layout: {type:'vbox',
                 padding:'5',
                 pack:'center',
                 align:'center'
                 },
		items:[{
			xtype:'panel',
			border:false,
			height:80,
			width:300,
			html:'<img src="'+Ext.cuinfo.webRoot+'/resources/images/default/global/userNoAuthority.png" />'
		}]
	});
});

