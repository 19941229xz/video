package com.video.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.video.interceptor.PageInterceptor;
import com.video.interceptor.TestInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		
		registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
		
		registry.addInterceptor(new PageInterceptor()).addPathPatterns("/*s");
		
		
		//ir.excludePathPatterns("/test");//添加不拦截的路径
		
		
		
		super.addInterceptors(registry);
	}

	
	
	
	
}
