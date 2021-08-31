package com.service;

import com.po.Dep;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDepService {
	public List<Dep> findAll();
	//��Ӳ��ŵķ���
	public Integer save(Dep dep);
	//�޸ĵķ���
	public int update(Dep dep);
	//��ѯ��������
	public Dep findById(Integer depid);
	//��ѯ���еķ���(������ҳչʾ)
	public List<Dep> findPageAll(@Param(value = "page") Integer page, @Param(value = "rows") Integer rows);
    //�ܼ�¼��������������ҳ���ģ��ܼ�¼��/��¼����
	public Integer findMaxRows();

}
