package com.video.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.video.dao.PosterMapper;
import com.video.service.RedisService;
import com.video.util.MapUtil;

@Controller
@RequestMapping("/posters")
public class PosterController {

	@Autowired
	RedisService rs;
	
	@Autowired
	PosterMapper pm;
	
	
	private Map<String, Object> rm;

	/**
	 * @visitLevel warm need cache
	 * @param cpage
	 *            pagesize somecondition
	 * @type post
	 * @commet getall posters
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> all(@RequestParam Map<String, Object> params) {
		rm = new HashMap<String, Object>();
		rm = MapUtil.transToResultMap(rm);

		// get from cache if exist
		String key = rs.POSTERLIST + params.get("cpage") + "-" + params.get("pagesize");
		if (rs.exists(key)) {
			rm.put(rs.POSTERLIST, rs.get(key));
			System.out.println("exist posterList from cache");
		} else {
			List<Map<String, Object>> userList = pm.all(params);
			rs.set(key, userList);
			rm.put(rs.POSTERLIST, userList);
			System.out.println("not exist posterList from database");
		}

		return rm;
	}

	/**
	 * @visitLevel cold
	 * @param id
	 * @type get
	 * @commet get poster* by id
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> one(@RequestParam Map<String, Object> params) {
		rm = new HashMap<String, Object>();
		rm = MapUtil.transToResultMap(rm);
		rm.put("posterList", pm.one(params));
		return rm;
	}

	/**
	 * @visitLevel cold
	 * @param 
	 * @type put
	 * @commet insert poster* by id
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> insert(@RequestParam Map<String, Object> params) {
		rm = new HashMap<String, Object>();
		rm = MapUtil.transToResultMap(rm);
		rm.put("status", pm.insert(params) == 1 ? "0" : "1");
		return rm;
	}

	/**
	 * @visitLevel cold
	 * @param id
	 * @type delete
	 * @commet delete poster by id url拼接
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(@RequestParam Map<String, Object> params) {
		rm = new HashMap<String, Object>();
		rm = MapUtil.transToResultMap(rm);
		
		File file=new File(pm.one(params).get(0).get("src")+"");
		
		if(file.exists()&&file.delete()){
			rm.put("status", pm.del(params) == 1 ? "0" : "1");
		}else{
			rm.put("status", "1");
		}
		
		
		return rm;
	}
	
	
}
