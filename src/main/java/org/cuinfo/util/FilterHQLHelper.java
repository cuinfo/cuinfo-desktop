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
 * 一个帮助类，实现一条SQL（HQL）的条件构造.<br/>
 * 注：这个类并不能保证构造的完全正确性.并且所有的构造连接词只实用and<br/>
 * 仅仅是根据filterHQLs来连接一条sql.<br/>
 * 仅仅需要通过getResult();可以获得条件表达式，工作原理是这样的:<br/>
 * createSuccess=true表示用户负责，通过conditionExpression传入SQL(HQL),返回conditionExpression,若conditionExpression==null或为空字符串，返回空字符串<br/>
 * createSuccess=false,由系统连接字符串,若filterHQLs==null或者filterHQLs.size()==0,返回空字符串，否则有程序构造。<br/>
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
	 * 系统构造表达式需要的集合
	 */
	private List<FilterHQL> filterHQLs;
	/***
	 * 是否创建true ，conditionExpression是输出结果，false由filterHQLs输出结果
	 */
	private boolean createSuccess;
	/***
	 * 用户自己构造的表达式
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
	 * 获得结果
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

