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

import com.video.dao.CommmentMapper;
import com.video.service.RedisService;
import com.video.util.MapUtil;

@Controller
@RequestMapping("/comments")
public class CommentController {

	
	@Autowired
	RedisService rs;
	
	@Autowired
	CommmentMapper commentM;
	
	
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
		String key = rs.COMMENTLIST + params.get("cpage") + "-" + params.get("pagesize");
		if (rs.exists(key)) {
			rm.put(rs.COMMENTLIST, rs.get(key));
			System.out.println("exist commentList from cache");
		} else {
			List<Map<String, Object>> userList = commentM.all(params);
			rs.set(key, userList);
			rm.put(rs.COMMENTLIST, userList);
			System.out.println("not exist commentList from database");
		}

		return rm;
	}

	/**
	 * @visitLevel cold
	 * @param id
	 * @type get
	 * @commet get comment* by id
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> one(@RequestParam Map<String, Object> params) {
		rm = new HashMap<String, Object>();
		rm = MapUtil.transToResultMap(rm);
		rm.put("commentList", commentM.one(params));
		return rm;
	}

	/**
	 * @visitLevel cold
	 * @param user_id  user_name content time(timestamp) video_id
	 * @type put
	 * @commet insert comment* by id
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> insert(@RequestParam Map<String, Object> params) {
		rm = new HashMap<String, Object>();
		rm = MapUtil.transToResultMap(rm);
		rm.put("status", commentM.insert(params) == 1 ? "0" : "1");
		return rm;
	}

	/**
	 * @visitLevel cold
	 * @param id
	 * @type delete
	 * @commet delete comment by id url拼接
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(@RequestParam Map<String, Object> params) {
		rm = new HashMap<String, Object>();
		rm = MapUtil.transToResultMap(rm);
		rm.put("status", commentM.del(params) == 1 ? "0" : "1");
		return rm;
	}
	
	
}
