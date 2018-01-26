package com.video.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.video.dao.UserMapper;
import com.video.util.MapUtil;

@Controller
@RequestMapping("sys")
public class SysController {
	
	@Autowired
	UserMapper um;
	
	private Map<String, Object> rm;

	
	/**
	 * 
	 * @param params  ( video_dir  server_path  poster_path     )
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(@RequestParam Map<String, Object> params){
		rm = new HashMap<String, Object>();
		rm = MapUtil.transToResultMap(rm);
		
		rm.put("status", um.updateSys(params) == 1 ? "0" : "1");
		
		return rm;
	}
	
	
	
}
