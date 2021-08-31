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
	 * 查询部门和福利的方法
	 * */
	@RequestMapping(value="doinit_Emp.do")
	public String doinit(HttpServletRequest request,HttpServletResponse response){
		System.out.println("查询部门和福利的方法......");
		Map<String,Object> map=new HashMap<String,Object>();
		List<Dep> lsdp=bizService.getDepService().findAll();
		List<Welfare> lswf=bizService.getWelfareService().findAll();
		map.put("lsdp", lsdp);
		map.put("lswf", lswf);
		PropertyFilter propertyFilter= AjaxUtil.filterproperts("birthday","pic");
		//将查询结果集合转化为json字符串
		String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	  	System.out.println("json:"+jsonstr);
	  //将本方法执行结果的json字符串返回到前台
	  	AjaxUtil.printString(response, jsonstr);
		return null;
		
	}
	/**
	 * 员工添加的方法
	 * */
	@RequestMapping(value="save_Emp.do")
	public String save(HttpServletRequest request, HttpServletResponse response, Emp emp){
		System.out.println("员工添加的方法......");
		//做文件上传处理
		//网站根路径
				String realpath=request.getRealPath("/");
				System.out.println("网站根路径:"+realpath);
				/*--------------文件上传begin----------------*/
				//获取上传照片对象
				MultipartFile multipartFile=emp.getPic();
				if(multipartFile!=null&&!multipartFile.isEmpty()){
					//获取上传文件名称
					String fname=multipartFile.getOriginalFilename();
					//改名
					if(fname.lastIndexOf(".")!=-1){
						String ext=fname.substring(fname.lastIndexOf("."));
						if(ext.equalsIgnoreCase(".jpg")){
							String newfname=new Date().getTime()+ext;
							//创建文件对象，指定文件路径
							File dostFile=new File(realpath+"/uppic/"+newfname);
							try {
								//上传(将请求传递的文件内容复制一份到刚才生成的文件中)
								FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),dostFile);
								emp.setFname(newfname);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				/*------------------文件上传end-----------------*/
				System.out.println("controller层:"+emp.toString());
				int code=bizService.getEmpService().save(emp);
				if (code>0) {
					//将本方法执行结果的json字符串返回到前台
					AjaxUtil.printString(response, "1");
				  }else{
					 AjaxUtil.printString(response, "0");
				  }
				
		return null;
	}
	/**
	 * 查询员工所有并分页的方法
	 * */
	@RequestMapping(value="findPageAll_Emp.do")
	public String findPageAll(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows){
		System.out.println("查询员工所有并分页的方法......"+page+"/"+rows);
		//因为easy UI的分页需要的参数是固定的复杂格式
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
	     //拼接前台数据 easy UI分页需要的固定数据,固定写法
	     map.put("page", page);
	     map.put("rows", lsep);
	     map.put("total", maxRows);
		PropertyFilter propertyFilter= AjaxUtil.filterproperts("birthday","pic");
		//将查询结果集合转化为json字符串
		String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	  	System.out.println("json:"+jsonstr);
	  //将本方法执行结果的json字符串返回到前台
	  	AjaxUtil.printString(response, jsonstr);
		return null;
		
	}
	/**
	 * 删除员工信息方法
	 * */
	@RequestMapping(value="delete_Emp.do")
	public String delete(HttpServletRequest request,HttpServletResponse response,Integer eid){
		System.out.println("删除员工信息方法......");
		int code=bizService.getEmpService().delete(eid);
		if (code>0) {
			 //将本方法执行结果的json字符串返回到前台
		  	AjaxUtil.printString(response, "1");
		}else{
			//将本方法执行结果的json字符串返回到前台
			AjaxUtil.printString(response, "0");
		}
		return null;
		
	}
	/**
	 *修改员工信息方法
	 * */
	@RequestMapping(value="update_Emp.do")
	public String update(HttpServletRequest request, HttpServletResponse response, Emp emp){
		System.out.println("修改员工信息方法......"+emp.toString());
		//做文件上传处理
				//网站根路径
						String realpath=request.getRealPath("/");
						System.out.println("网站根路径:"+realpath);
						/*--------------文件上传begin----------------*/
						//获取上传照片对象
						MultipartFile multipartFile=emp.getPic();
						if(multipartFile!=null&&!multipartFile.isEmpty()){
							//获取上传文件名称
							String fname=multipartFile.getOriginalFilename();
							//改名
							if(fname.lastIndexOf(".")!=-1){
								String ext=fname.substring(fname.lastIndexOf("."));
								if(ext.equalsIgnoreCase(".jpg")){
									String newfname=new Date().getTime()+ext;
									//创建文件对象，指定文件路径
									File dostFile=new File(realpath+"/uppic/"+newfname);
									try {
										//上传(将请求传递的文件内容复制一份到刚才生成的文件中)
										FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),dostFile);
										emp.setFname(newfname);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
						/*------------------文件上传end-----------------*/
		boolean flag=bizService.getEmpService().update(emp);
		if (flag) {
			 //将本方法执行结果的json字符串返回到前台
			//System.out.println("11111111111");
		  	AjaxUtil.printString(response, "1");
		}else{
			//将本方法执行结果的json字符串返回到前台
			//System.out.println("2222222222222");
			AjaxUtil.printString(response, "0");
		}
		return null;
		
	}
	/**
	 *修改员工信息的查询单个方法
	 * */
	@RequestMapping(value="findById_Emp.do")
	public String findById(HttpServletRequest request,HttpServletResponse response,Integer eid){
		System.out.println("修改员工信息的查询单个方法......");
		Emp emp=bizService.getEmpService().findById(eid);
		PropertyFilter propertyFilter= AjaxUtil.filterproperts("birthday","pic");
		//将查询结果集合转化为json字符串
	  	String jsonstr=JSONObject.toJSONString(emp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	  	System.out.println("json:"+jsonstr);
	  	//将本方法执行结果的json字符串返回到前台
	  	AjaxUtil.printString(response, jsonstr);
		return null;
		
	}


}
