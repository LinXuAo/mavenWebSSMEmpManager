package com.mapper;

import com.po.EmpWelfare;
import com.po.Welfare;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Ա��������ӽӿ�
 * */
@Service("EmpWelfareDAO")
public interface IEmpWelfareMapper {
	public Integer save(EmpWelfare empWelfare);
	//ͨ��Ա�����ɾ��Ա����������
	public Integer delete(Integer eid);
	//ͨ��Ա����Ų�ѯ��Ա��������ϵ
	public List<Welfare>  findByEid(Integer eid);

}
