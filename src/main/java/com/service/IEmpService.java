package com.service;

import com.po.Emp;
import com.po.PageBean;

import java.util.List;

public interface IEmpService {
	public Integer save(Emp emp);
	//查询所有的方法(包括分页展示)
		public List<Emp> findPageAll(PageBean pb);
	    //总记录数（用来计算总页数的，总记录数/记录数）
		public Integer findMaxRows();
		//删除员工方法
		public Integer delete(Integer eid);
		//查询单个方法
		public Emp findById(Integer eid);
		//修改的方法
		public boolean update(Emp emp);

}
