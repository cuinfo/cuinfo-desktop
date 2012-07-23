/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.util;

/****
 * 
 *注意: 对于HQL 设置，适用于Hibernate属性配置
 * @author Administrator
 *
 */
public class FilterHQL {
	/****
	 * 简单sql表达式;
	 */
	private String expression;
	/****
	 * 条件名称
	 */
	private String conditionName;
	/****
	 * 条件值
	 */
	private String conditionValue;
	
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getExpression() {
		return expression;
	}
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	public String getConditionName() {
		return conditionName;
	}
	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}
	public String getConditionValue() {
		return conditionValue;
	}
}

