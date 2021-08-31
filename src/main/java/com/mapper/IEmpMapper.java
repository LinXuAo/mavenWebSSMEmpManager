package com.mapper;

import com.po.Emp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Ա����ӵĽӿ�
 * */
@Service("EmpDAO")
public interface IEmpMapper {
	//���Ա���ķ���
	public Integer save(Emp emp);
	//��ѯ����ŵķ�����Ҳ�������һ����ӵ����ݣ���Ϊ����������ģ����к�߱���н�ʵ�ʱ����Ҫ��ѯ��
	public Integer findMaxEid();
	//��ѯ���еķ���(������ҳչʾ)
	public List<Emp> findPageAll(@Param(value = "page") Integer page, @Param(value = "rows") Integer rows);
    //�ܼ�¼��������������ҳ���ģ��ܼ�¼��/��¼����
	public Integer findMaxRows();
	//ɾ��Ա������
	public Integer delete(Integer eid);
	//��ѯ��������
	public Emp findById(Integer eid);
	//�޸ĵķ���
	public int update(Emp emp);
}
