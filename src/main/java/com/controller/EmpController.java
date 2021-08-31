package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.po.Dep;
import com.po.Emp;
import com.po.PageBean;
import com.po.Welfare;
import com.util.AjaxUtil;
import com.util.BizServiceUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmpController {
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
	@RequestMapping(value="doinit_Emp.do")
	public String doinit(HttpServletRequest request,HttpServletResponse response){
		System.out.println("��ѯ���ź͸����ķ���......");
		Map<String,Object> map=new HashMap<String,Object>();
		List<Dep> lsdp=bizService.getDepService().findAll();
		List<Welfare> lswf=bizService.getWelfareService().findAll();
		map.put("lsdp", lsdp);
		map.put("lswf", lswf);
		PropertyFilter propertyFilter= AjaxUtil.filterproperts("birthday","pic");
		//����ѯ�������ת��Ϊjson�ַ���
		String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	  	System.out.println("json:"+jsonstr);
	  //��������ִ�н����json�ַ������ص�ǰ̨
	  	AjaxUtil.printString(response, jsonstr);
		return null;
		
	}
	/**
	 * Ա����ӵķ���
	 * */
	@RequestMapping(value="save_Emp.do")
	public String save(HttpServletRequest request, HttpServletResponse response, Emp emp){
		System.out.println("Ա����ӵķ���......");
		//���ļ��ϴ�����
		//��վ��·��
				String realpath=request.getRealPath("/");
				System.out.println("��վ��·��:"+realpath);
				/*--------------�ļ��ϴ�begin----------------*/
				//��ȡ�ϴ���Ƭ����
				MultipartFile multipartFile=emp.getPic();
				if(multipartFile!=null&&!multipartFile.isEmpty()){
					//��ȡ�ϴ��ļ�����
					String fname=multipartFile.getOriginalFilename();
					//����
					if(fname.lastIndexOf(".")!=-1){
						String ext=fname.substring(fname.lastIndexOf("."));
						if(ext.equalsIgnoreCase(".jpg")){
							String newfname=new Date().getTime()+ext;
							//�����ļ�����ָ���ļ�·��
							File dostFile=new File(realpath+"/uppic/"+newfname);
							try {
								//�ϴ�(�����󴫵ݵ��ļ����ݸ���һ�ݵ��ղ����ɵ��ļ���)
								FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),dostFile);
								emp.setFname(newfname);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				/*------------------�ļ��ϴ�end-----------------*/
				System.out.println("controller��:"+emp.toString());
				int code=bizService.getEmpService().save(emp);
				if (code>0) {
					//��������ִ�н����json�ַ������ص�ǰ̨
					AjaxUtil.printString(response, "1");
				  }else{
					 AjaxUtil.printString(response, "0");
				  }
				
		return null;
	}
	/**
	 * ��ѯԱ�����в���ҳ�ķ���
	 * */
	@RequestMapping(value="findPageAll_Emp.do")
	public String findPageAll(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows){
		System.out.println("��ѯԱ�����в���ҳ�ķ���......"+page+"/"+rows);
		//��Ϊeasy UI�ķ�ҳ��Ҫ�Ĳ����ǹ̶��ĸ��Ӹ�ʽ
	     Map<String,Object> map=new HashMap<>();
	     PageBean pb=new PageBean();
	     page=page==null || page<1?pb.getPage():page;
	     rows=rows==null || rows<1?pb.getRows():rows;
	     pb.setPage(page);
	     pb.setRows(rows);
	     System.out.println("2222222222222");
	     List<Emp> lsep=bizService.getEmpService().findPageAll(pb);
		System.out.println("3333333333333");
	     int maxRows=bizService.getEmpService().findMaxRows();
	     //ƴ��ǰ̨���� easy UI��ҳ��Ҫ�Ĺ̶�����,�̶�д��
	     map.put("page", page);
	     map.put("rows", lsep);
	     map.put("total", maxRows);
		PropertyFilter propertyFilter= AjaxUtil.filterproperts("birthday","pic");
		//����ѯ�������ת��Ϊjson�ַ���
		String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	  	System.out.println("json:"+jsonstr);
	  //��������ִ�н����json�ַ������ص�ǰ̨
	  	AjaxUtil.printString(response, jsonstr);
		return null;
		
	}
	/**
	 * ɾ��Ա����Ϣ����
	 * */
	@RequestMapping(value="delete_Emp.do")
	public String delete(HttpServletRequest request,HttpServletResponse response,Integer eid){
		System.out.println("ɾ��Ա����Ϣ����......");
		int code=bizService.getEmpService().delete(eid);
		if (code>0) {
			 //��������ִ�н����json�ַ������ص�ǰ̨
		  	AjaxUtil.printString(response, "1");
		}else{
			//��������ִ�н����json�ַ������ص�ǰ̨
			AjaxUtil.printString(response, "0");
		}
		return null;
		
	}
	/**
	 *�޸�Ա����Ϣ����
	 * */
	@RequestMapping(value="update_Emp.do")
	public String update(HttpServletRequest request, HttpServletResponse response, Emp emp){
		System.out.println("�޸�Ա����Ϣ����......"+emp.toString());
		//���ļ��ϴ�����
				//��վ��·��
						String realpath=request.getRealPath("/");
						System.out.println("��վ��·��:"+realpath);
						/*--------------�ļ��ϴ�begin----------------*/
						//��ȡ�ϴ���Ƭ����
						MultipartFile multipartFile=emp.getPic();
						if(multipartFile!=null&&!multipartFile.isEmpty()){
							//��ȡ�ϴ��ļ�����
							String fname=multipartFile.getOriginalFilename();
							//����
							if(fname.lastIndexOf(".")!=-1){
								String ext=fname.substring(fname.lastIndexOf("."));
								if(ext.equalsIgnoreCase(".jpg")){
									String newfname=new Date().getTime()+ext;
									//�����ļ�����ָ���ļ�·��
									File dostFile=new File(realpath+"/uppic/"+newfname);
									try {
										//�ϴ�(�����󴫵ݵ��ļ����ݸ���һ�ݵ��ղ����ɵ��ļ���)
										FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),dostFile);
										emp.setFname(newfname);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
						/*------------------�ļ��ϴ�end-----------------*/
		boolean flag=bizService.getEmpService().update(emp);
		if (flag) {
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
	 *�޸�Ա����Ϣ�Ĳ�ѯ��������
	 * */
	@RequestMapping(value="findById_Emp.do")
	public String findById(HttpServletRequest request,HttpServletResponse response,Integer eid){
		System.out.println("�޸�Ա����Ϣ�Ĳ�ѯ��������......");
		Emp emp=bizService.getEmpService().findById(eid);
		PropertyFilter propertyFilter= AjaxUtil.filterproperts("birthday","pic");
		//����ѯ�������ת��Ϊjson�ַ���
	  	String jsonstr=JSONObject.toJSONString(emp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	  	System.out.println("json:"+jsonstr);
	  	//��������ִ�н����json�ַ������ص�ǰ̨
	  	AjaxUtil.printString(response, jsonstr);
		return null;
		
	}


}
