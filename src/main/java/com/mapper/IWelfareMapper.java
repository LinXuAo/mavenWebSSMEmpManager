package com.mapper;

import com.po.Welfare;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询福利所有的接口
 * */
@Service("WelfareDAO")
public interface IWelfareMapper {
	public List<Welfare> findAll();

}
