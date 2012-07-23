/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.util;

import java.util.Enumeration;
import java.util.Vector;

public class UserList {
	private static final UserList userList=new UserList();
	   private Vector<UserVersion> v;
	   private UserList()
	   {
	        v=new Vector<UserVersion>();
	   }
	   public static UserList getInstance()
	   {
	        return userList;
	   }
	   public void addUserVersion(UserVersion userVersion)

	   {

	        if(userVersion!=null)
	        	v.addElement(userVersion);

	   }

	   

	   public void removeUserVersion(UserVersion userVersion)

	   {

	        if(userVersion!=null)

	              v.remove(userVersion);

	   }

	   

	   public Enumeration<UserVersion> getUserList()

	   {

	        return v.elements();

	   }

	   

	   public int getUserVersionCount()

	   {

	        return v.size();

	   }
	   /***
	    * 获得版本最新的user
	    * @param userId
	    * @return
	    */
	   public UserVersion getUserVersionByUserId(int userId){
		   
		   Enumeration<UserVersion> uvs=this.getUserList();
		   
		   int maxVersion=0;
		   UserVersion result=null;
		   while(uvs.hasMoreElements()){
			   UserVersion uv=uvs.nextElement();
			   
			   if(uv.getUser().getId().intValue()==userId){
				   if(maxVersion<=uv.getVersionNum()){
					   result=uv;
					   maxVersion=uv.getVersionNum();
				   }
			   }
		   }
		   
		   return result;
	   }
}
