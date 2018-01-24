package com.video.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

	
	
	public List<Map<String,Object>> all(Map<String,Object> m);

	public List<Map<String,Object>> one(Map<String,Object> m);//exist or getOne
	
	public int update(Map<String,Object> m);
	
	public int del(Map<String,Object> m);
	
	public int insert(Map<String,Object> m);
	
	//还需要一个批量插入
	
	
}
