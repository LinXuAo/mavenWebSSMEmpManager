package com.util;

import com.service.IDepService;
import com.service.IEmpService;
import com.service.IWelfareService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("BizService")
@Transactional
public class BizServiceUtil {
	@Resource(name="DepBiz")
	private IDepService depService;
	@Resource(name="WelfareBiz")
	private IWelfareService welfareService;
	@Resource(name="EmpBiz")
	private IEmpService empService;
	public IDepService getDepService() {
		return depService;
	}
	public void setDepService(IDepService depService) {
		this.depService = depService;
	}
	public IWelfareService getWelfareService() {
		return welfareService;
	}
	public void setWelfareService(IWelfareService welfareService) {
		this.welfareService = welfareService;
	}
	public IEmpService getEmpService() {
		return empService;
	}
	public void setEmpService(IEmpService empService) {
		this.empService = empService;
	}
	

}
