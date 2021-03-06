package com.video.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.video.dao.UserMapper;
import com.video.service.RedisService;
import com.video.util.EmailUtil;
import com.video.util.MapUtil;

@Controller
@RequestMapping("/users")
public class UserController {
	
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
		rm=MapUtil.transToResultMap(rm);
		
		//get from cache if exist
				String key=rs.USERLIST+params.get("cpage")+"-"
						+params.get("pagesize");
				if(rs.exists(key)){
					rm.put(rs.USERLIST, rs.get(key));
					System.out.println("exist USERLIST from cache");
				}else{
					List<Map<String,Object>> userList=um.all(params);
					rs.set(key, userList);
					rm.put(rs.USERLIST, userList);
					System.out.println("not exist USERLIST from database");
				}
		
		return rm;
	}
	
	/**
	 * @visitLevel cold
	 * @param qq
	 * @type get
	 * @commet get user* by qq
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> one(@RequestParam Map<String,Object> params){
		rm=new HashMap<String,Object>();
		rm=MapUtil.transToResultMap(rm);
		rm.put("userList", um.one(params));
		return rm;
	}
	
	/**
	 * @visitLevel cold
	 * @param qq pwd tel name
	 * @type put
	 * @commet get user* by qq
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Map<String,Object> insert(@RequestParam Map<String,Object> params){
		rm=new HashMap<String,Object>();
		rm=MapUtil.transToResultMap(rm);
		rm.put("status", um.insert(params)==1?"0":"1");
		return rm;
	}
	
	/**
	 * @visitLevel cold
	 * @param qq 
	 * @type delete
	 * @commet delete user by  qq  url拼接
	 */
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public Map<String,Object> delete(@RequestParam Map<String,Object> params){
		rm=new HashMap<String,Object>();
		rm=MapUtil.transToResultMap(rm);
		rm.put("status", um.del(params)==1?"0":"1");
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
		
		rm=MapUtil.transToResultMap(rm);
		rm.put("userList", um.one(params));
		
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
		rm=MapUtil.transToResultMap(rm);
		
		rm.put("status", um.insert(params)==1?"0":"1");
		if((rm.get("status")+"").equals("0")){
			//email
			params.put("title", "注册成功");
			params.put("content", "恭喜您成为私人影院的用户!你的账号为您的QQ:"+params.get("qq")+",密码是"+params.get("pwd"));
			EmailUtil.sendOneNomal(params);
		}
		
		return rm;
	}
	
	
	/**
	 * @visitLevel cold
	 * @param qq pwd ip device
	 * @type any
	 * @coment front validate
	 */
	@RequestMapping(value="/forget")
	@ResponseBody
	public Map<String,Object> forget(@RequestParam Map<String,Object> params){
		rm=new HashMap<String,Object>();
		rm=MapUtil.transToResultMap(rm);
		
		List<Map<String,Object>> userList=new ArrayList<Map<String,Object>>();
		userList=um.one(params);
		
		params.put("title", "找回密码");
		params.put("content", "您账号为"+params.get("qq")+"的密码是"+userList.get(0).get("pwd"));
		EmailUtil.sendOneNomal(params);
		
		return rm;
	}
	
	
}
