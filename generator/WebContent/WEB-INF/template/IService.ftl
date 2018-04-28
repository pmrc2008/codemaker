package com.data.citic.service;

import java.util.List;
import java.util.Map;

import com.data.citic.entity.${model_name};

public interface I${model_name}Service {

    int insert(${model_name} record);

    int insertSelective(${model_name} record);

	int count${model_name}List(Map<String,Object> param);
	
	List<${model_name}> select${model_name}List(Map<String,Object> param);
}