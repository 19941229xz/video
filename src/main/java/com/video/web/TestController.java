package com.video.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	
	
	@RequestMapping(value="/test")
	@ResponseBody
	public String test(String name){
		
		System.out.println(name);
		
		
		return "ok";
	}
	
	
	
}
