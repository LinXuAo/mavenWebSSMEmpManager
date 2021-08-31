package com.service.Impl;

import com.po.Welfare;
import com.service.IWelfareService;
import com.util.DaoServiceUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("WelfareBiz")
@Transactional
public class WelfareServiceImpl implements IWelfareService {
	@Resource(name="DaoService")
	private DaoServiceUtil daoService;
	

	public DaoServiceUtil getDaoService() {
		return daoService;
	}


	public void setDaoService(DaoServiceUtil daoService) {
		this.daoService = daoService;
	}


	@Override
	public List<Welfare> findAll() {
		System.out.println("!!!!!!!!!!!!");
		return daoService.getWelfareMapper().findAll();
	}







}
