package com.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling   //开启task  
public class VideoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoApplication.class, args); 
	}
}
