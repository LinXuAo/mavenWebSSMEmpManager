package com.util;

import com.alibaba.fastjson.serializer.PropertyFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxUtil {
	/**
	 * ����̨ת����JSON���ַ�������������ͻ��˵���Ӧ
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
   *�������Եķ������̶�д�� ����fastjosn�ṩ��
   *������Ҫ���ݵ��������ƴ��������fastjosn���Զ�����
   * **/
  public static PropertyFilter filterproperts(final String...propNames){
	  PropertyFilter propertyFilter=new PropertyFilter() {
		
		@Override
		public boolean apply(Object arg0, String arg1, Object arg2) {
           for (String pname : propNames) {
			if (propNames.equals(pname)) {
				return false;//����
			}
		}
           return true;
		}
	};
	return propertyFilter;
	
  } 
  
}
