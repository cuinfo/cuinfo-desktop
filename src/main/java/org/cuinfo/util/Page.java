/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.util;

import java.util.List;

/**
 * 分页对象
 *
 * @author Administrator
 */
/**
 * 封装分页和排序查询的结果,并继承QueryParameter的所有查询请求参数. 
 * 
 * @param <T> Page中的记录类型. 
 */  
public class Page<T> extends QueryParameter {  
      
    private List<T> result = null;  
  
    
    private int totalCount = -1;
    
    private FilterHQLHelper filterHQLHelper;

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public FilterHQLHelper getFilterHQLHelper() {
		
		return filterHQLHelper;
	}

	public void setFilterHQLHelper(FilterHQLHelper filterHQLHelper) {
		this.filterHQLHelper = filterHQLHelper;
	}  
	
  
   
    
}
