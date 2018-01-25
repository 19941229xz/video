package com.video;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.video.service.RedisService;
import com.video.util.EmailUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoApplicationTests {
	
	@Autowired
	RedisService redisService;
	

	@Test
	public void contextLoads() {
		
		redisService.set("test", "hahhahahah", 10l);//设置键值对  和有效期  秒
	}
	
	
	@Test
	public void send() {//测试邮件发送
		//et.sendTest();
		//et.sendOneNomal("972031129", "test", "content");
		//et.sendOneNomal("972031129", "test", "<a style='color:red;font-size:30px;' href='http://www.baidu.com/'>百度</a> ");
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		//map.put("qqs", new String[]{"972031129","1902782804"});
		map.put("qq", "972031129");
		map.put("title", "title");
		map.put("url", "http://www.baidu.com/");
		map.put("img", "http://119.29.172.118:80/videoDemo/pic/test.png");
		map.put("text1", "还在为网上找不到资源而抓狂？还在为下载时间过长而焦虑，没关系一波福利来袭，私人影院为您打造属于您个人的小电影私密空间！解决您的所有烦恼！");
		map.put("text2", "快点击下面的按钮开启你的全新之旅吧！");
		map.put("btn1", "老司机入口");
		map.put("btn2", "新司机入口");
		
		
		map.put("content", EmailUtil.getModel2(map));
		
		EmailUtil.sendOneNomal(map);
		
	}
	

}
