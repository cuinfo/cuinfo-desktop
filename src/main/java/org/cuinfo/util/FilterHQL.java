/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.util;

/****
 * 
 *ע��: ����HQL ���ã�������Hibernate��������
 * @author Administrator
 *
 */
public class FilterHQL {
	/****
	 * ��sql���ʽ;
	 */
	private String expression;
	/****
	 * ��������
	 */
	private String conditionName;
	/****
	 * ����ֵ
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

