package com.video.util;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MapUtil {

	
	public Map<String,Object> transToResultMap(Map<String,Object> map){
		
		map.put("status", "0");
		
		
		return map;
	}
	
	
}
