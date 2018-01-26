package com.video.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.video.dao.VideoMapper;
import com.video.service.RedisService;
import com.video.util.MapUtil;

@Controller
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	RedisService rs;
	
	@Autowired
	VideoMapper vm;
	
	private Map<String,Object> rm;
	
	/**
	 * @visitLevel warm need cache
	 * @param cpage pagesize somecondition
	 * @type post
	 * @commet getallvidos
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> all(@RequestParam Map<String,Object> params){
		rm=new HashMap<String,Object>();
		rm=MapUtil.transToResultMap(rm);
		
		//get from cache if exist
		String key=rs.VIDEOLIST+params.get("cpage")+"-"
				+params.get("pagesize");
		if(rs.exists(key)){
			rm.put(rs.VIDEOLIST, rs.get(key));
			System.out.println("exist videoList from cache");
		}else{
			List<Map<String,Object>> userList=vm.all(params);
			rs.set(key, userList);
			rm.put(rs.VIDEOLIST, userList);
			System.out.println("not exist videoList from database");
		}
		
		
		return rm;
	}
	
	
	/**
	 * @visitLevel cold
	 * @param id
	 * @type get
	 * @commet get video* by id
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> one(@RequestParam Map<String,Object> params){
		rm=new HashMap<String,Object>();
		rm=MapUtil.transToResultMap(rm);
		rm.put("videoList", vm.one(params));
		return rm;
	}
	
	/**
	 * @visitLevel cold
	 * @param  name time
	 * @type put
	 * @commet insert video* by id
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Map<String,Object> insert(@RequestParam Map<String,Object> params){
		rm=new HashMap<String,Object>();
		rm=MapUtil.transToResultMap(rm);
		rm.put("status", vm.insert(params)==1?"0":"1");
		return rm;
	}
	
	/**
	 * @visitLevel cold
	 * @param id 
	 * @type delete
	 * @commet delete video by  id  url拼接
	 */
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public Map<String,Object> delete(@RequestParam Map<String,Object> params){
		rm=new HashMap<String,Object>();
		rm=MapUtil.transToResultMap(rm);
		rm.put("status", vm.del(params)==1?"0":"1");
		return rm;
	}
	
	
	
}
