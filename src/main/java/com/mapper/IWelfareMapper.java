package com.mapper;

import com.po.Welfare;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ��ѯ�������еĽӿ�
 * */
@Service("WelfareDAO")
public interface IWelfareMapper {
	public List<Welfare> findAll();

}
