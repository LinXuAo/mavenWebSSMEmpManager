package com.service.Impl;

import com.po.Dep;
import com.service.IDepService;
import com.util.DaoServiceUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("DepBiz")
@Transactional
public class DepServiceImpl implements IDepService {
	@Resource(name="DaoService")
	private DaoServiceUtil daoService;
	

	public DaoServiceUtil getDaoService() {
		return daoService;
	}


	public void setDaoService(DaoServiceUtil daoService) {
		this.daoService = daoService;
	}


	@Override
	public List<Dep> findAll() {
		System.out.println("8888888888888");
		return daoService.getDepmapper().findAll();
	}


	@Override
	public Integer save(Dep dep) {
		int code=daoService.getDepmapper().save(dep);
		return code;
	}


	@Override
	public int update(Dep dep) {
		int code=daoService.getDepmapper().update(dep);
		return code;
	}


	@Override
	public Dep findById(Integer depid) {
		//获取要查看的部门对象
		Dep dep=daoService.getDepmapper().findById(depid);
		return dep;
	}


	@Override
	public List<Dep> findPageAll(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return daoService.getDepmapper().findPageAll(page, rows);
	}


	@Override
	public Integer findMaxRows() {
		// TODO Auto-generated method stub
		return daoService.getDepmapper().findMaxRows();
	}

}
