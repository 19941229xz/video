package com.video.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.video.dao.UserMapper;
import com.video.service.RedisService;
import com.video.util.MapUtil;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	MapUtil mu;
	@Autowired
	RedisService rs;
	
	@Autowired
	UserMapper um;
	
	private Map<String,Object> rm;
	
	
	
	/**
	 * @visitLevel cold
	 * @param cpage pagesize somecondition
	 * @type post
	 * @commet getallusers
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> all(@RequestParam Map<String,Object> params){
		rm=new HashMap<String,Object>();
		rm=mu.transToResultMap(rm);
		
		rm.put("userList", um.all(params));
		
		return rm;
	}
	
	
	
	
	/**
	 * 
	 * @param qq pwd ip device token
	 * @type post
	 */
	@RequestMapping(value="/login")
	@ResponseBody
	public Map<String,Object> login(@RequestParam Map<String,Object> params){
		rm=new HashMap<String,Object>();
		
		rm=mu.transToResultMap(rm);
		
		
		return rm;
	}
	
	
	/**
	 * @visitLevel cold
	 * @param qq pwd ip device
	 * @type any
	 * @coment front validate
	 */
	@RequestMapping(value="/register")
	@ResponseBody
	public Map<String,Object> register(@RequestParam Map<String,Object> params){
		rm=new HashMap<String,Object>();
		rm=mu.transToResultMap(rm);
		
		rm.put("status", um.insert(params)==1?"0":"1");
		
		return rm;
	}
	
	
}
