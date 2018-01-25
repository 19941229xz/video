package com.video.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class MapUtil {

	private static Map<String,Object> map;
	
	public static Map<String,Object> transToResultMap(Map<String,Object> map){
		map.put("status", "0");
		return map;
	}
	/**
	 * @comment status=1
	 * @param errorTip
	 * @return
	 */
	public static String errorMap(String errorTip){
		map=new HashMap<String, Object>();
		
		map.put("status", "1");
		map.put("tip", errorTip);
		
		return JSON.toJSONString(map);
		
	}
	
	
	
	
}
