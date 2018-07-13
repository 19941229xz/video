package com.video.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.video.dao.UserMapper;
import com.video.dao.VideoMapper;
import com.video.util.StringUtil;
import com.video.util.TimeUtil;

@Component
public class InitService implements  ApplicationRunner {

	
	@Autowired
	VideoMapper vm;
	@Autowired
	UserMapper um;
	
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		// TODO Auto-generated method stub
		
		/*String video_dir=um.getSys().get(0).get("video_dir")+"";
		
		File dir=new File(video_dir);
		File[] files=null;
		String name="";
		String src="";
		Map<String,Object> params=new HashMap<String,Object>();
		
		if(dir!=null&&dir.isDirectory()){
			files=dir.listFiles();
			for (File file : files) {
				 name=StringUtil.getPrefixName(file.getName());
				 src=TimeUtil.createNowTimeStr()+".mp4";
				 file.renameTo(new File(video_dir+"/"+src));
				 params.put("name", name);
				 params.put("src", src);
				 vm.insert(params);
				System.out.println(name);
			}
		}*/
		
		
	}

	
	
	
	
}
