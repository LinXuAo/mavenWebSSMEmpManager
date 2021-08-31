package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.po.Dep;
import com.po.PageBean;
import com.util.AjaxUtil;
import com.util.BizServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DepController {
	@Resource(name="BizService")
	private BizServiceUtil bizService;

	public BizServiceUtil getBizService() {
		return bizService;
	}

	public void setBizService(BizServiceUtil bizService) {
		this.bizService = bizService;
	}
	/**
	 * ��ѯ���ź͸����ķ���
	 * */
	/*@RequestMapping(value="findAll_Dep.do")
	public String findAll(HttpServletRequest request,HttpServletResponse response){
		System.out.println("��ѯ���ŵķ���......");
		List<Dep> lsdp=bizService.getDepService().findAll();
		PropertyFilter propertyFilter=AjaxUtil.filterproperts("birthday","pic");
		//����ѯ�������ת��Ϊjson�ַ���
		String jsonstr=JSONObject.toJSONString(lsdp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	  	System.out.println("json:"+jsonstr);
	  //��������ִ�н����json�ַ������ص�ǰ̨
	  	AjaxUtil.printString(response, jsonstr);
		return null;
		
	}*/
	/**
	 * ������ӵķ���
	 * */
	@RequestMapping(value="save_Dep.do")
	public String save(HttpServletRequest request, HttpServletResponse response, Dep dep){
		System.out.println("������ӵķ���......");
				System.out.println("controller��:"+dep.toString());
				int code=bizService.getDepService().save(dep);
				if (code>0) {
					//��������ִ�н����json�ַ������ص�ǰ̨
					AjaxUtil.printString(response, "1");
				  }else{
					 AjaxUtil.printString(response, "0");
				  }
				
		return null;
	}
	
	/**
	 *�޸Ĳ�����Ϣ����
	 * */
	@RequestMapping(value="update_Dep.do")
	public String update(HttpServletRequest request, HttpServletResponse response, Dep dep){
		System.out.println("�޸Ĳ�����Ϣ����......"+dep.toString());
		int code=bizService.getDepService().update(dep);
		if (code>0) {
			 //��������ִ�н����json�ַ������ص�ǰ̨
			//System.out.println("11111111111");
		  	AjaxUtil.printString(response, "1");
		}else{
			//��������ִ�н����json�ַ������ص�ǰ̨
			//System.out.println("2222222222222");
			AjaxUtil.printString(response, "0");
		}
		return null;
		
	}
	/**
	 *�޸Ĳ�����Ϣ�Ĳ�ѯ��������
	 * */
	@RequestMapping(value="findById_Dep.do")
	public String findById(HttpServletRequest request,HttpServletResponse response,Integer depid){
		System.out.println("�޸Ĳ�����Ϣ�Ĳ�ѯ��������......");
		Dep dep=bizService.getDepService().findById(depid);
		PropertyFilter propertyFilter= AjaxUtil.filterproperts("birthday","pic");
		//����ѯ�������ת��Ϊjson�ַ���
	  	String jsonstr=JSONObject.toJSONString(dep,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	  	System.out.println("json:"+jsonstr);
	  	//��������ִ�н����json�ַ������ص�ǰ̨
	  	AjaxUtil.printString(response, jsonstr);
		return null;
		
	}
	/**
	 * ��ѯ�������в���ҳ�ķ���
	 * */
	@RequestMapping(value="findPageAll_Dep.do")
	public String findPageAll(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows){
		System.out.println("��ѯԱ�����в���ҳ�ķ���......"+page+"/"+rows);
		//��Ϊeasy UI�ķ�ҳ��Ҫ�Ĳ����ǹ̶��ĸ��Ӹ�ʽ
	     Map<String,Object> map=new HashMap<>();
	     PageBean pb=new PageBean();
	     page=page==null || page<1?pb.getPage():page;
	     rows=rows==null || rows<1?pb.getRows():rows;
	     pb.setPage(page);
	     pb.setRows(rows);
	     List<Dep> lsdp=bizService.getDepService().findPageAll(page, rows);
	     int maxRows=bizService.getDepService().findMaxRows();
	     //ƴ��ǰ̨���� easy UI��ҳ��Ҫ�Ĺ̶�����,�̶�д��
	     map.put("page", page);
	     map.put("rows", lsdp);
	     map.put("total", maxRows);
		PropertyFilter propertyFilter= AjaxUtil.filterproperts("birthday","pic");
		//����ѯ�������ת��Ϊjson�ַ���
		String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	  	System.out.println("json:"+jsonstr);
	  //��������ִ�н����json�ַ������ص�ǰ̨
	  	AjaxUtil.printString(response, jsonstr);
		return null;
		
	}

}
