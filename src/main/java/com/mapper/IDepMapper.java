package com.mapper;

import com.po.Dep;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询部门所有的接口
 * */
@Service("DepDAO")
public interface IDepMapper {
	//查询部门所有的方法
	public List<Dep> findAll();
	//添加部门的方法
	public Integer save(Dep dep);
	//修改的方法
	public int update(Dep dep);
	//查询单个方法
	public Dep findById(Integer depid);
	//查询所有的方法(包括分页展示)
	public List<Dep> findPageAll(@Param(value = "page") Integer page, @Param(value = "rows") Integer rows);
	//总记录数（用来计算总页数的，总记录数/记录数）
    public Integer findMaxRows();

}
