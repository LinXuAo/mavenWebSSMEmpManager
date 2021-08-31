package com.mapper;

import com.po.Emp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工添加的接口
 * */
@Service("EmpDAO")
public interface IEmpMapper {
	//添加员工的方法
	public Integer save(Emp emp);
	//查询最大编号的方法（也就是最后一次添加的数据，因为编号是自增的，所有后边保存薪资的时候需要查询）
	public Integer findMaxEid();
	//查询所有的方法(包括分页展示)
	public List<Emp> findPageAll(@Param(value = "page") Integer page, @Param(value = "rows") Integer rows);
    //总记录数（用来计算总页数的，总记录数/记录数）
	public Integer findMaxRows();
	//删除员工方法
	public Integer delete(Integer eid);
	//查询单个方法
	public Emp findById(Integer eid);
	//修改的方法
	public int update(Emp emp);
}
