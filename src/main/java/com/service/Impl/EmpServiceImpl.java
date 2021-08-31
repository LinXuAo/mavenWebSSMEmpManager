package com.service.Impl;

import com.po.*;
import com.service.IEmpService;
import com.util.DaoServiceUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("EmpBiz")
@Transactional
public class EmpServiceImpl implements IEmpService {
	@Resource(name="DaoService")
	private DaoServiceUtil daoService;
	

	public DaoServiceUtil getDaoService() {
		return daoService;
	}


	public void setDaoService(DaoServiceUtil daoService) {
		this.daoService = daoService;
	}

	@Override
	public Integer save(Emp emp) {
		System.out.println(emp.toString());
		//处理员工添加
		int code=daoService.getEmpMapper().save(emp);
		if (code>0) {
			//处理薪资和员工福利，必须获取该员工的编号
			int eid=daoService.getEmpMapper().findMaxEid();
			    /*薪资保存begin*/
			Salary sa=new Salary(eid,emp.getEmoney());
			daoService.getSalaryMapper().save(sa);
			    /*薪资保存end*/
			   /*员工福利保存begin*/
			//获取员工编号的数组
			String[] wids=emp.getWids();
			if (wids!=null && wids.length>0) {
				for (int i = 0; i < wids.length; i++) {
					EmpWelfare ewf= new EmpWelfare(eid,new Integer(wids[i]));
					daoService.getEmpWelfareMapper().save(ewf);
				}
				
			}
			    /*员工福利保存end*/
			return code;
		}
		return null;
	}


	@Override
	public List<Emp> findPageAll(PageBean pb) {
		System.out.println("111111111111111");
		return daoService.getEmpMapper().findPageAll(pb.getPage(),pb.getRows());
	}


	@Override
	public Integer findMaxRows() {
		// TODO Auto-generated method stub
		return daoService.getEmpMapper().findMaxRows();
	}


	@Override
	public Integer delete(Integer eid) {
		//要删主表先删从表
		daoService.getSalaryMapper().delete(eid);
		daoService.getEmpWelfareMapper().delete(eid);
		int code=daoService.getEmpMapper().delete(eid);
		if (code>0) {
			System.out.println("删除成功！.....");
			return code;
		}else{
			System.out.println("删除失败！.....");
			return null;
		}

	}


	@Override
	public Emp findById(Integer eid) {
		//先获取要查看的员工对象
		Emp oldemp=daoService.getEmpMapper().findById(eid);
		//获取该员工的薪资
		Salary salary=daoService.getSalaryMapper().findByEid(eid);
		if (salary!=null && salary.getEmoney()!=null) {
			oldemp.setEmoney(salary.getEmoney());
		}
		//获取员工福利集合
		List<Welfare> lswf=daoService.getEmpWelfareMapper().findByEid(eid);
		System.out.println("员工福利集合："+lswf.size());
		//处理福利的ID
		String [] wids=new String[lswf.size()];
		for (int i = 0; i < wids.length; i++) {
			//获取到员工福利集合中拿出来每一个福利对象
			Welfare wf=lswf.get(i);
			//将拿出来的每一个对象中的福利编号放入福利ID数组中
			wids[i]=wf.getWid().toString();
		}
		oldemp.setLswf(lswf);
		oldemp.setWids(wids);

		return oldemp;
	}


	@Override
	public boolean update(Emp emp) {
		//修改员工信息
		Integer code=daoService.getEmpMapper().update(emp);
		//System.out.println("333333333333");
		//System.out.println("code00:"+code+"");
		if (code>0) {
		 /*---------修改薪资------------*/
		   //先获取之前对象存入的薪资
			Salary oldsalary=daoService.getSalaryMapper().findByEid(emp.getEid());
			if (oldsalary!=null && oldsalary.getEmoney()!=null) {
				//下来修改薪资的情况
				System.out.println("修改薪资！");
				oldsalary.setEmoney(emp.getEmoney());
				daoService.getSalaryMapper().update(oldsalary);
			}else{
				//没有薪资的情况
				System.out.println("没有薪资！");
				Salary salary=new Salary(emp.getEid(),emp.getEmoney());
				daoService.getSalaryMapper().save(salary);
			}
		/*---------修改福利-----------*/
			//先获取之前对象的福利
			List<Welfare> lswf=daoService.getEmpWelfareMapper().findByEid(emp.getEid());
			if (lswf!=null && lswf.size()>0) {
				//删除原有的福利
				daoService.getEmpWelfareMapper().delete(emp.getEid());
			}
			//下来保存修改后的福利
			  //获取员工福利编号的数组
			String[] wids=emp.getWids();
			if (wids!=null && wids.length>0) {
				for (int i = 0; i < wids.length; i++) {
					EmpWelfare ewf= new EmpWelfare(emp.getEid(),new Integer(wids[i]));
					daoService.getEmpWelfareMapper().save(ewf);
				}
			}
			return true;
		}
		return false;

		
	}

}
