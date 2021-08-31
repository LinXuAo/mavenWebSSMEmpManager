package com.util;

import com.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("DaoService")
public class DaoServiceUtil {
	@Resource(name="DepDAO")
	private IDepMapper depmapper;
	@Resource(name="WelfareDAO")
	private IWelfareMapper welfareMapper;
	@Resource(name="EmpDAO")
	private IEmpMapper empMapper;
	@Resource(name="SalaryDAO")
	private ISalaryMapper salaryMapper;
	@Resource(name="EmpWelfareDAO")
	private IEmpWelfareMapper empWelfareMapper;
	
	public IDepMapper getDepmapper() {
		return depmapper;
	}
	public void setDepmapper(IDepMapper depmapper) {
		this.depmapper = depmapper;
	}
	public IWelfareMapper getWelfareMapper() {
		return welfareMapper;
	}
	public void setWelfareMapper(IWelfareMapper welfareMapper) {
		this.welfareMapper = welfareMapper;
	}
	public IEmpMapper getEmpMapper() {
		return empMapper;
	}
	public void setEmpMapper(IEmpMapper empMapper) {
		this.empMapper = empMapper;
	}
	public ISalaryMapper getSalaryMapper() {
		return salaryMapper;
	}
	public void setSalaryMapper(ISalaryMapper salaryMapper) {
		this.salaryMapper = salaryMapper;
	}
	public IEmpWelfareMapper getEmpWelfareMapper() {
		return empWelfareMapper;
	}
	public void setEmpWelfareMapper(IEmpWelfareMapper empWelfareMapper) {
		this.empWelfareMapper = empWelfareMapper;
	}
	

}
