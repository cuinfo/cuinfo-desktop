/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.util;

/**
 *·ÖÒ³²ÎÊý
 * @author Administrator
 */
public class QueryParameter {
	 
	   
	     protected int pageNo = 0;  
	     protected int pageSize = -1;  
	     protected int firstRow=0;
	     
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getFirstRow() {
			return firstRow;
		}
		public void setFirstRow(int firstRow) {
			this.firstRow = firstRow;
		}
	     
	     
	    
	     
}

