package com.video.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.video.service.RedisService;

@Component  
@Aspect 
public class RedisAop {
	
	@Autowired
	RedisService rs;
	
	//匹配com.video.web包及其子包下的所有类的insert  delete 
    @Pointcut("execution(* com.video.web..*.insert(..)) || execution(* com.video.web..*.delete(..))")  
    public void executeService(){  
  
    } 
    
    
    /** 
     * 前置通知，方法调用前被调用 
     * @param joinPoint 
     */  
    @Before("executeService()")  
    public void doBeforeAdvice(JoinPoint joinPoint){
    	
    }
	
    
    /** 
     * 后置返回通知 
     * 这里需要注意的是: 
     *      如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息 
     *      如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数 
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值 
     * @param joinPoint 
     * @param keys 
     */  
    @AfterReturning(value = "execution(* com.video.web..*.insert(..)) || execution(* com.video.web..*.delete(..))",returning = "keys")  
    public void doAfterReturningAdvice1(JoinPoint joinPoint,Object keys){  
  
    	//用的最多 通知的签名  
        Signature signature = joinPoint.getSignature();  
        //代理的是哪一个方法  
        String method=signature.getName();
//        System.out.println("redis代理的方法："+signature.getName());  
        //AOP代理类的名字  
        String className=signature.getDeclaringTypeName();
        String[] arr=className.split("\\.");//split  特殊情况 特殊符号
        String str=arr[arr.length-1].replace("Controller", "List");
        String rkp=(new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
//        System.out.println(redisKeyPattern);
//        System.out.println("redis代理类："+signature.getDeclaringTypeName());
//        System.out.println("redisaop"+keys);  
//        System.out.println();
        if(keys.toString().equals("{status=0}")){
        	rs.removePattern(rkp+"*");
        	System.out.println(className+" excute "+method+" so delete "+rkp+"* form reids cache！"  );
        }
    } 
    
}
