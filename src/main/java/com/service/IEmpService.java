package com.service;

import com.po.Emp;
import com.po.PageBean;

import java.util.List;

public interface IEmpService {
	public Integer save(Emp emp);
	//��ѯ���еķ���(������ҳչʾ)
		public List<Emp> findPageAll(PageBean pb);
	    //�ܼ�¼��������������ҳ���ģ��ܼ�¼��/��¼����
		public Integer findMaxRows();
		//ɾ��Ա������
		public Integer delete(Integer eid);
		//��ѯ��������
		public Emp findById(Integer eid);
		//�޸ĵķ���
		public boolean update(Emp emp);

}
