package com.video.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class EmailUtil {
	
	
	@Autowired  
    JavaMailSender mailSender;
	
	
	public void send() throws MessagingException{
		
		
		
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();  
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);  
            message.setFrom("2300819486@qq.com");  
            message.setTo("972031129@qq.com");  
            message.setSubject("测试邮件主题");  
            message.setText("测试邮件内容");  
            this.mailSender.send(mimeMessage);  
              
		
		
		
	}
	
	
	
}
