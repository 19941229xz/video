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

import com.video.dao.TagMapper;
import com.video.service.RedisService;
import com.video.util.MapUtil;

@Controller
@RequestMapping("/tags")
public class TagController {

	@Autowired
	RedisService rs;
	
	@Autowired
	TagMapper tm;
	
	private Map<String, Object> rm;

	/**
	 * @visitLevel warm need cache
	 * @param cpage
	 *            pagesize somecondition
	 * @type post
	 * @commet getallvidos
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> all(@RequestParam Map<String, Object> params) {
		rm = new HashMap<String, Object>();
		rm = MapUtil.transToResultMap(rm);

		// get from cache if exist
		String key = rs.TAGLIST + params.get("cpage") + "-" + params.get("pagesize");
		if (rs.exists(key)) {
			rm.put(rs.TAGLIST, rs.get(key));
			System.out.println("exist tagList from cache");
		} else {
			List<Map<String, Object>> userList = tm.all(params);
			rs.set(key, userList);
			rm.put(rs.TAGLIST, userList);
			System.out.println("not exist tagList from database");
		}

		return rm;
	}

	/**
	 * @visitLevel cold
	 * @param id
	 * @type get
	 * @commet get video* by id
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> one(@RequestParam Map<String, Object> params) {
		rm = new HashMap<String, Object>();
		rm = MapUtil.transToResultMap(rm);
		rm.put("tagList", tm.one(params));
		return rm;
	}

	/**
	 * @visitLevel cold
	 * @param name
	 * @type put
	 * @commet insert video* by id
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> insert(@RequestParam Map<String, Object> params) {
		rm = new HashMap<String, Object>();
		rm = MapUtil.transToResultMap(rm);
		rm.put("status", tm.insert(params) == 1 ? "0" : "1");
		return rm;
	}

	/**
	 * @visitLevel cold
	 * @param id
	 * @type delete
	 * @commet delete video by id url拼接
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(@RequestParam Map<String, Object> params) {
		rm = new HashMap<String, Object>();
		rm = MapUtil.transToResultMap(rm);
		rm.put("status", tm.del(params) == 1 ? "0" : "1");
		return rm;
	}
	
}
