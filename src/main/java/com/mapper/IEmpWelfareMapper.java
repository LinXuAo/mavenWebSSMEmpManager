package com.mapper;

import com.po.EmpWelfare;
import com.po.Welfare;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工福利添加接口
 * */
@Service("EmpWelfareDAO")
public interface IEmpWelfareMapper {
	public Integer save(EmpWelfare empWelfare);
	//通过员工编号删除员工福利方法
	public Integer delete(Integer eid);
	//通过员工编号查询该员工福利关系
	public List<Welfare>  findByEid(Integer eid);

}
