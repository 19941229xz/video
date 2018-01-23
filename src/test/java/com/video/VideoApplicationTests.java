package com.video;

import javax.mail.MessagingException;

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
	
	@Autowired
	EmailUtil et;

	@Test
	public void contextLoads() {
		
		redisService.set("test", "hahhahahah", 10l);//设置键值对  和有效期  秒
	}
	
	
	@Test
	public void sendEmail() throws MessagingException{
		et.send();
	}

}
