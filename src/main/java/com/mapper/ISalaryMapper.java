package com.mapper;

import com.po.Salary;
import org.springframework.stereotype.Service;

/**
 * н����ӵĽӿ�
 * */
@Service("SalaryDAO")
public interface ISalaryMapper {
	public Integer save(Salary salary);
	//ͨ��Ա�����ɾ��н�ʷ���
	public Integer delete(Integer eid);
	//����Ա����Ų�SalaryѯԱ����н��
	public Salary findByEid(Integer eid);
	//����Ա������޸�Ա��н��
	public Integer update(Salary salary);

}
