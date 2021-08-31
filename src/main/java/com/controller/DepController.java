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
	 * 查询部门和福利的方法
	 * */
	/*@RequestMapping(value="findAll_Dep.do")
	public String findAll(HttpServletRequest request,HttpServletResponse response){
		System.out.println("查询部门的方法......");
		List<Dep> lsdp=bizService.getDepService().findAll();
		PropertyFilter propertyFilter=AjaxUtil.filterproperts("birthday","pic");
		//将查询结果集合转化为json字符串
		String jsonstr=JSONObject.toJSONString(lsdp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	  	System.out.println("json:"+jsonstr);
	  //将本方法执行结果的json字符串返回到前台
	  	AjaxUtil.printString(response, jsonstr);
		return null;
		
	}*/
	/**
	 * 部门添加的方法
	 * */
	@RequestMapping(value="save_Dep.do")
	public String save(HttpServletRequest request, HttpServletResponse response, Dep dep){
		System.out.println("部门添加的方法......");
				System.out.println("controller层:"+dep.toString());
				int code=bizService.getDepService().save(dep);
				if (code>0) {
					//将本方法执行结果的json字符串返回到前台
					AjaxUtil.printString(response, "1");
				  }else{
					 AjaxUtil.printString(response, "0");
				  }
				
		return null;
	}
	
	/**
	 *修改部门信息方法
	 * */
	@RequestMapping(value="update_Dep.do")
	public String update(HttpServletRequest request, HttpServletResponse response, Dep dep){
		System.out.println("修改部门信息方法......"+dep.toString());
		int code=bizService.getDepService().update(dep);
		if (code>0) {
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
	 *修改部门信息的查询单个方法
	 * */
	@RequestMapping(value="findById_Dep.do")
	public String findById(HttpServletRequest request,HttpServletResponse response,Integer depid){
		System.out.println("修改部门信息的查询单个方法......");
		Dep dep=bizService.getDepService().findById(depid);
		PropertyFilter propertyFilter= AjaxUtil.filterproperts("birthday","pic");
		//将查询结果集合转化为json字符串
	  	String jsonstr=JSONObject.toJSONString(dep,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	  	System.out.println("json:"+jsonstr);
	  	//将本方法执行结果的json字符串返回到前台
	  	AjaxUtil.printString(response, jsonstr);
		return null;
		
	}
	/**
	 * 查询部门所有并分页的方法
	 * */
	@RequestMapping(value="findPageAll_Dep.do")
	public String findPageAll(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows){
		System.out.println("查询员工所有并分页的方法......"+page+"/"+rows);
		//因为easy UI的分页需要的参数是固定的复杂格式
	     Map<String,Object> map=new HashMap<>();
	     PageBean pb=new PageBean();
	     page=page==null || page<1?pb.getPage():page;
	     rows=rows==null || rows<1?pb.getRows():rows;
	     pb.setPage(page);
	     pb.setRows(rows);
	     List<Dep> lsdp=bizService.getDepService().findPageAll(page, rows);
	     int maxRows=bizService.getDepService().findMaxRows();
	     //拼接前台数据 easy UI分页需要的固定数据,固定写法
	     map.put("page", page);
	     map.put("rows", lsdp);
	     map.put("total", maxRows);
		PropertyFilter propertyFilter= AjaxUtil.filterproperts("birthday","pic");
		//将查询结果集合转化为json字符串
		String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
	  	System.out.println("json:"+jsonstr);
	  //将本方法执行结果的json字符串返回到前台
	  	AjaxUtil.printString(response, jsonstr);
		return null;
		
	}

}
