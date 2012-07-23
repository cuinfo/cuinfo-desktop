/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cuinfo.util;

import java.util.Date;
import java.util.Random;

/***
 * 实现一个简单的自己的加密解密算法
 * 
 * @author Administrator
 *
 */
public class MyPassword {
	
	public static final String  str="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String  encode(String password){
		//思想就是存储16进制和10进制的混合码。单位为奇数为10进制，单位为偶数为16进制
		if(password==null)
			password="123456";
		Random r=new Random((new Date()).getTime());
		
		
		byte[] bytes=password.getBytes();
		int len= bytes.length;
		String result="";
		for(int i=0;i<len;i++){
			if(result.length()>0)
				result+=str.charAt(r.nextInt(26));
			if(i%2==0)
			result+=Integer.toHexString(bytes[i]);
			else
				result+=bytes[i];
		}
		return result;
		
	}
	public static String  decode(String password){
		String []arr=password.split("[A-Z]");
		password="";
		for(int i=0;i<arr.length;i++){
			int radix=10;
			if(i%2==0)
				radix=16;
			if(!arr[i].trim().equals("")&&arr[i]!=null){
				char ch=(char) Integer.valueOf(arr[i], radix).byteValue();
				password+=String.valueOf(ch);
			}
		}
		return password;
		
	}
	
	public static void  main(String [] args){
		String result=MyPassword.encode("admin");
		System.out.println(result);
		System.out.println(MyPassword.decode(result));
	}
}

