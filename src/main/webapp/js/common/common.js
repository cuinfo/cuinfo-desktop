/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.cuinfo={};
Ext.cuinfo.webRoot="/CuInfo";
Ext.BLANK_IMAGE_URL = Ext.cuinfo.webRoot+'/resources/common/s.gif';
//��Ȩ��Ĭ��ҳ��s
Ext.cuinfo.noAuthorityRedirection=Ext.cuinfo.webRoot+"/userNoAuthority.action";

//����jsģ��
Ext.cuinfo.modules={};
//��ҳ
Ext.cuinfo.modules.Main={};
Ext.cuinfo.modules.Main.namespace='/main';
Ext.cuinfo.modules.Main.baseUrl=Ext.cuinfo.webRoot+Ext.cuinfo.modules.Main.namespace;

//�û�����
Ext.cuinfo.modules.UserCenter={};
Ext.cuinfo.modules.UserCenter.namespace='/userCenter';
Ext.cuinfo.modules.UserCenter.baseUrl=Ext.cuinfo.webRoot+Ext.cuinfo.modules.UserCenter.namespace;
//ʹ��ϵͳ
Ext.cuinfo.modules.UseSystem={};
Ext.cuinfo.modules.UseSystem.namespace='/useSystem';
Ext.cuinfo.modules.UseSystem.baseUrl=Ext.cuinfo.webRoot+Ext.cuinfo.modules.UseSystem.namespace;


Ext.cuinfo.PoweredBy=function(){
	var dom=document.createElement("a");
	
	var el=Ext.get(dom);
	el.set({href:'http://www.baidu.com'}).set({target:'_blank'});
	el.addClass('poweredby');
	el.dom.innerHTML='��Ȩ����:&nbsp;�ٶȼ���';
	
	Ext.getDom(Ext.getBody()).appendChild(el.dom);
}
var hasDisplay;
Ext.onReady(function(){
	if(hasDisplay)
	Ext.cuinfo.PoweredBy();
	
	Ext.getBody().addClass('body-bk-class');
	
});


//Action �쳣����,��Json String ת����json����Ҫ�Ĳ�׽�쳣����������{parseState:true,json:string,jsonStr:jsonStr}�������Ƿ���{parseState:false,exceptionMessage:string,jsonStr:jsonStr}

Ext.cuinfo.ParseResult=function(jsonStr){
	
	var result={};
	result.jsonStr=jsonStr;
	try{
	result.json=Ext.decode(jsonStr);
	result.parseState=true;
	}catch(e){
		result.parseState=false;
		result.exceptionMessage=e;
	}
	return result;
	
}


/**
 * ͨ��response.responseText�ж��Ƿ���Ҫ����ˢ��loaction
 * ���첽����������true
 * @param res response
 */
Ext.cuinfo.RefreshLoaction=function(response){
	var hasRequest=false;
	
	if(!response||!response.responseText)
	 return hasRequest;
	
	var result=Ext.cuinfo.ParseResult(response.responseText);
	if(result.parseState&&result.json.refresh){
		location.reload();
		hasRequest=true;
	}
        
	if(result.parseState&&result.json.redirect){
		location.href=Ext.cuinfo.noAuthorityRedirection;
		hasRequest=true;
	}
	
	return hasRequest;
	
};