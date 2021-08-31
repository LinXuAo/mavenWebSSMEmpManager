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
		//����Ա�����
		int code=daoService.getEmpMapper().save(emp);
		if (code>0) {
			//����н�ʺ�Ա�������������ȡ��Ա���ı��
			int eid=daoService.getEmpMapper().findMaxEid();
			    /*н�ʱ���begin*/
			Salary sa=new Salary(eid,emp.getEmoney());
			daoService.getSalaryMapper().save(sa);
			    /*н�ʱ���end*/
			   /*Ա����������begin*/
			//��ȡԱ����ŵ�����
			String[] wids=emp.getWids();
			if (wids!=null && wids.length>0) {
				for (int i = 0; i < wids.length; i++) {
					EmpWelfare ewf= new EmpWelfare(eid,new Integer(wids[i]));
					daoService.getEmpWelfareMapper().save(ewf);
				}
				
			}
			    /*Ա����������end*/
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
		//Ҫɾ������ɾ�ӱ�
		daoService.getSalaryMapper().delete(eid);
		daoService.getEmpWelfareMapper().delete(eid);
		int code=daoService.getEmpMapper().delete(eid);
		if (code>0) {
			System.out.println("ɾ���ɹ���.....");
			return code;
		}else{
			System.out.println("ɾ��ʧ�ܣ�.....");
			return null;
		}

	}


	@Override
	public Emp findById(Integer eid) {
		//�Ȼ�ȡҪ�鿴��Ա������
		Emp oldemp=daoService.getEmpMapper().findById(eid);
		//��ȡ��Ա����н��
		Salary salary=daoService.getSalaryMapper().findByEid(eid);
		if (salary!=null && salary.getEmoney()!=null) {
			oldemp.setEmoney(salary.getEmoney());
		}
		//��ȡԱ����������
		List<Welfare> lswf=daoService.getEmpWelfareMapper().findByEid(eid);
		System.out.println("Ա���������ϣ�"+lswf.size());
		//��������ID
		String [] wids=new String[lswf.size()];
		for (int i = 0; i < wids.length; i++) {
			//��ȡ��Ա�������������ó���ÿһ����������
			Welfare wf=lswf.get(i);
			//���ó�����ÿһ�������еĸ�����ŷ��븣��ID������
			wids[i]=wf.getWid().toString();
		}
		oldemp.setLswf(lswf);
		oldemp.setWids(wids);

		return oldemp;
	}


	@Override
	public boolean update(Emp emp) {
		//�޸�Ա����Ϣ
		Integer code=daoService.getEmpMapper().update(emp);
		//System.out.println("333333333333");
		//System.out.println("code00:"+code+"");
		if (code>0) {
		 /*---------�޸�н��------------*/
		   //�Ȼ�ȡ֮ǰ��������н��
			Salary oldsalary=daoService.getSalaryMapper().findByEid(emp.getEid());
			if (oldsalary!=null && oldsalary.getEmoney()!=null) {
				//�����޸�н�ʵ����
				System.out.println("�޸�н�ʣ�");
				oldsalary.setEmoney(emp.getEmoney());
				daoService.getSalaryMapper().update(oldsalary);
			}else{
				//û��н�ʵ����
				System.out.println("û��н�ʣ�");
				Salary salary=new Salary(emp.getEid(),emp.getEmoney());
				daoService.getSalaryMapper().save(salary);
			}
		/*---------�޸ĸ���-----------*/
			//�Ȼ�ȡ֮ǰ����ĸ���
			List<Welfare> lswf=daoService.getEmpWelfareMapper().findByEid(emp.getEid());
			if (lswf!=null && lswf.size()>0) {
				//ɾ��ԭ�еĸ���
				daoService.getEmpWelfareMapper().delete(emp.getEid());
			}
			//���������޸ĺ�ĸ���
			  //��ȡԱ��������ŵ�����
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
