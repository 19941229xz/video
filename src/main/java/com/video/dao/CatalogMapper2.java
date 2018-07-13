package com.video.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CatalogMapper2 {

	
	/*public List<Map<String,Object>> all(Map<String,Object> m);

	public List<Map<String,Object>> one(Map<String,Object> m);//exist or getOne
	
	public int update(Map<String,Object> m);
	
	public int del(Map<String,Object> m);
	
	public int insert(Map<String,Object> m);*/
	
	//还需要一个批量插入
	
	@Select("select count(id) from store_cate_info where category_name=#{category_name}")
	public int count(String category_name);
	
	@Select("select id from category_info where category_name=#{category_name}")
	public String getIdByName(String category_name);
	
	@Insert("insert into store_cate_info(id,organize_id,category_id,store_id,category_name)"
			+ "values(#{id},#{organize_id},#{category_id},#{store_id},#{category_name})")
	public int insert(Map<String,Object> map);
	
	/**
	 * map.put("goods_name", goods_name);
					map.put("category_id", category_id);
					map.put("goods_code", goods_code);
					map.put("goods_state", goods_state);
	 * @param map
	 * @return
	 */
	@Insert("insert into store_goods_info(id,organize_id,goods_unit,store_id,goods_price,"
			+ "goods_name,category_id,goods_code,goods_state)"
			+ "values(#{id}"
			+ ",#{organize_id},#{goods_unit},#{store_id},"
			+ "#{goods_price},#{goods_name},#{category_id},#{goods_code},#{goods_state})")
	public int insertGood(Map<String,Object> map);
	
	
}
