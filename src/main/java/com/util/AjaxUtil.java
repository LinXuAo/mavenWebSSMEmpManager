package com.util;

import com.alibaba.fastjson.serializer.PropertyFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxUtil {
	/**
	 * 将后台转换成JSON的字符串对象输出到客户端的响应
	 * */
  public static void printString(HttpServletResponse response,String s){
	  response.setCharacterEncoding("utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			//System.out.println("ss:"+s);
			out.print(s);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
  }
  
  
  /**
   *过滤属性的方法，固定写法 ，由fastjosn提供的
   *将不需要传递的属性名称传入参数，fastjosn或自动过滤
   * **/
  public static PropertyFilter filterproperts(final String...propNames){
	  PropertyFilter propertyFilter=new PropertyFilter() {
		
		@Override
		public boolean apply(Object arg0, String arg1, Object arg2) {
           for (String pname : propNames) {
			if (propNames.equals(pname)) {
				return false;//过滤
			}
		}
           return true;
		}
	};
	return propertyFilter;
	
  } 
  
}
