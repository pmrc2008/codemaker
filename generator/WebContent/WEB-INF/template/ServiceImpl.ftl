package com.data.citic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.citic.dao.${model_name}Mapper;
import com.data.citic.entity.${model_name};
import com.data.citic.service.I${model_name}Service;

@Service
public class ${model_name}ServiceImpl implements I${model_name}Service {

	@Autowired
	private ${model_name}Mapper ${model_name?uncap_first}Mapper;

	@Override
	public int insert(${model_name} record) {
		return ${model_name?uncap_first}Mapper.insert(record);
	}

	@Override
	public int insertSelective(${model_name} record) {
		return ${model_name?uncap_first}Mapper.insertSelective(record);
	}
	
	@Override
	public int count${model_name}List(Map<String, Object> param) {
		return ${model_name?uncap_first}Mapper.count${model_name}List(param);
	}
	@Override
	public List<${model_name}> select${model_name}List(Map<String, Object> param) {
		return ${model_name?uncap_first}Mapper.select${model_name}List(param);
	}
}
