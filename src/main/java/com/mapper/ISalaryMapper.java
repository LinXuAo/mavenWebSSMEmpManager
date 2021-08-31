package com.mapper;

import com.po.Salary;
import org.springframework.stereotype.Service;

/**
 * 薪资添加的接口
 * */
@Service("SalaryDAO")
public interface ISalaryMapper {
	public Integer save(Salary salary);
	//通过员工编号删除薪资方法
	public Integer delete(Integer eid);
	//根据员工编号查Salary询员工的薪资
	public Salary findByEid(Integer eid);
	//根据员工编号修改员工薪资
	public Integer update(Salary salary);

}
