/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var center;
var left;

function layout(){
    window.location=Ext.cuinfo.modules.UseSystem.baseUrl+"/logout.action"
}

Ext.onReady(function() {

   
    //  Ext.QuickTips.init();
    Ext.lib.Ajax.defaultPostHeader += ";charset=utf-8";

    // 1������head����
    var top = new Ext.Panel( {
        region : 'north',
        border : false,
        baseCls:"mangPageTop",
        autoLoad: {
            url: Ext.cuinfo.modules.Main.baseUrl+"/mainTop.action"//common.js�ж���Ext.cuinfo.modules.Main.baseUrl
        }, 
        height : 80
    });
	
   

	
    //�м��ѡ�����
    center = new Ext.TabPanel({                                                                                           
        region: "center",
		
        defaults: {
            autoScroll:true
        },//�Զ����ֹ�����
        items:[
        {
            id: "index",
            title: "��ҳ",
            xtype:'panel',
            baseCls:'x-plain',
            layout:'border',
            items:[
            {
                region:'center',
                baseCls:'x-plain',
                bodyCfg: {
                    tag: 'center',
                    cls: 'body-bk-class'
                }					
            }
            ]
				
								
        }
       
        ],
        enableTabScroll: true
    });
    center.setActiveTab("index");
    
    /******/
    left=getLeft();
    var vp = new Ext.Viewport({
        layout: "border",
        items: [top, left, center]
    });
	
	
    var task = {
        run: function(){
            var t=Ext.get('top-page-date');
            var welcome=Ext.get('top-page-user-welcome');
        
            var date=new Date();
            if(t){
                t.update(date.format('Y��m��d�� H:i:s'));
            }
            if(welcome){
                welcome.update(date.format('A�ã�'));
            }
        },
        interval: 1000 //1 second
    }
    Ext.TaskMgr.start(task);
});
	


function getLeft(){
    return {
        xtype:'cuinforMianPageLeft',
        region: "west",
        title: "���ܵ���",
        collapsible: true,
        split: true,
        containerScroll: true,
        autoScroll: true,
        maxWidth:300,
        minWidth:180,
        width: 230,
        listeners:{
            'treeNodeClick':treeNodeClick
        }
    };
}

function treeNodeClick(n){
    //alert(n.attributes.url);
    var url = n.attributes.url;
    var id = n.attributes.id;
    if(url){
        if(center.getItem(id)){
            //��ʾ��ǩ�Ѵ�,�򼤻�
            center.setActiveTab(id);
        }else{
            //��ʾ��ǩ��û�д�,������ҳ��
            //��url�Ŵ�ҳ��
            var p = new Ext.Panel({
                title: n.attributes.text, //������ǽڵ���ı�
                id: id, //��ǩ��ID�ͽڵ��IDһ��
                //autoLoad: {url: url, scripts: true},
                html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="' + url + '"></iframe>',				
                closable: true
            });
            center.add(p);
            center.setActiveTab(p);
        }
    }
}



