package com.video.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.video.dao.PosterMapper;
import com.video.dao.UserMapper;
import com.video.service.RedisService;
import com.video.util.MapUtil;
import com.video.util.StringUtil;
import com.video.util.TimeUtil;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	RedisService rs;
	
	@Autowired
	UserMapper um;
	@Autowired
	PosterMapper pm;
	
	private Map<String, Object> rm;
	
	@RequestMapping(value="/poster",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> upPoster(HttpServletRequest request){
		rm = new HashMap<String, Object>();
		rm = MapUtil.transToResultMap(rm);
		
		//String poster_path=um.getSys().get(0).get("poster_path")+"";
		
//		String realpath=request.getServletContext().getRealPath("/");
		
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        Map<String, Object> params=new HashMap<String,Object>();
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
//                  stream = new BufferedOutputStream(new FileOutputStream(new File("C:\\Users\\xz\\Desktop\\"+i+file.getOriginalFilename())));
                    
                    String str=file.getOriginalFilename();
                    String str2="E:\\FFOutput"+str;
                    params.put("src", str2);
                    //posters  insert
                    rm.put("status", pm.insert(params) == 1 ? "0" : "1");
                    stream = new BufferedOutputStream(new FileOutputStream(new File(str2)));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    rm.put("status", "1");
                }
            } else {
            	rm.put("status", "1");
            }
        }
		return rm;
	}
	
	
	
}
