package com.mapper;

import com.po.Dep;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ��ѯ�������еĽӿ�
 * */
@Service("DepDAO")
public interface IDepMapper {
	//��ѯ�������еķ���
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
