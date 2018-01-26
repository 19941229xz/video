package com.video.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

	
	
	public static String createFileName(String str){
		
		String[] arr=str.split("\\.");
		String suffix=arr[arr.length-1];
		
		
		
		return "poster"+TimeUtil.createNowTimeStr()+"."+suffix;
		
	}
	
	
	
}
