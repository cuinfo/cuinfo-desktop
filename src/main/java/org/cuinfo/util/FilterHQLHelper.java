/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.util;

import java.util.List;

/**
 *
 * @author Administrator
 */
/***
 * һ�������࣬ʵ��һ��SQL��HQL������������.<br/>
 * ע������ಢ���ܱ�֤�������ȫ��ȷ��.�������еĹ������Ӵ�ֻʵ��and<br/>
 * �����Ǹ���filterHQLs������һ��sql.<br/>
 * ������Ҫͨ��getResult();���Ի���������ʽ������ԭ����������:<br/>
 * createSuccess=true��ʾ�û�����ͨ��conditionExpression����SQL(HQL),����conditionExpression,��conditionExpression==null��Ϊ���ַ��������ؿ��ַ���<br/>
 * createSuccess=false,��ϵͳ�����ַ���,��filterHQLs==null����filterHQLs.size()==0,���ؿ��ַ����������г����졣<br/>
 * example1:<br/>
 *FilterHQLHelper helper=new FilterHQLHelper();<br/>
 *helper.setCreateSuccess(true);<br/>
 *helper.setConditionExpression("user.name<10");<br/>
 *String result=helper.getResult();<br/>
 *result: result=user.name<10;<br/>
 *example2:<br/>
 *FilterHQLHelper helper=new FilterHQLHelper();<br/>
 *helper.setCreateSuccess(false);<br/>
 *FilterHQL fh=new FilterHQL();<br/>
 *fh.setExpression("<");<br/>
 *fh.setconditionName("user.name");<br/>
 *fh.setConditionValue("10");<br/>
 *List<FilterHQL> filterHQLs=new ArrayList<FilterHQL>();<br/>
 *filterHQLs.add(fh);<br/>
 *helper.setFilterHQLs(filterHQLs);<br/>
 *String result=helper.getResult();<br/>
 *result: result=user.name<10;<br/>
 * @author Administrator
 *
 */
public class FilterHQLHelper {
	/***
	 * ϵͳ������ʽ��Ҫ�ļ���
	 */
	private List<FilterHQL> filterHQLs;
	/***
	 * �Ƿ񴴽�true ��conditionExpression����������false��filterHQLs������
	 */
	private boolean createSuccess;
	/***
	 * �û��Լ�����ı��ʽ
	 */
	private String conditionExpression;
	public List<FilterHQL> getFilterHQLs() {
		return filterHQLs;
	}
	public void setFilterHQLs(List<FilterHQL> filterHQLs) {
		this.filterHQLs = filterHQLs;
	}
	private  boolean isCreateSuccess() {
		return createSuccess;
	}
	public void setCreateSuccess(boolean createSuccess) {
		this.createSuccess = createSuccess;
	}
	private  String getConditionExpression() {
		return conditionExpression;
	}
	public void setConditionExpression(String conditionExpression) {
		this.conditionExpression = conditionExpression;
	}
	/*****
	 * ��ý��
	 * @return
	 */
	public String getResult(){
		String result="";
		//createSuccess=true;
		if(this.isCreateSuccess()){
			if(this.getConditionExpression()==null)
				return result;
			
			return this.getConditionExpression().trim();
		}
		//createSuccess==false
		if(this.filterHQLs==null||this.filterHQLs.size()==0)
			return result;
		
		FilterHQL first=this.filterHQLs.get(0);
		result=first.getConditionName()+first.getExpression()+first.getConditionValue()+" "; 
		
		this.filterHQLs.remove(0);
		for(FilterHQL filterHQL:this.filterHQLs){
			result+="and "+filterHQL.getConditionName()+filterHQL.getExpression()+filterHQL.getConditionValue()+" ";
		}
		
		
		return " "+result.trim()+" ";
	};
}

