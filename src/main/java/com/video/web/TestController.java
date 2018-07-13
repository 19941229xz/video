package com.video.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api")
@Api("swaggerDemoController相关的api")
public class TestController {

	
	
	@RequestMapping(value="/test")
	@ApiOperation(value = "springboot测试", notes = "返回ok代表成功")
//    @ApiImplicitParam(name = "id", value = "学生ID", paramType = "path", required = false, dataType = "Integer")
	@ResponseBody
	public String test(String name){
		
		System.out.println(name);
		
		
		return "ok1";
	}
	
	
	
}
