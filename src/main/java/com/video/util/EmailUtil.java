package com.video.util;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class EmailUtil {

	public static void sendTest() {
		// 创建Properties 类用于记录邮箱的一些属性
		Properties props = new Properties();
		// 表示SMTP发送邮件，必须进行身份验证
		props.put("mail.smtp.auth", "true");
		// 此处填写SMTP服务器
		props.put("mail.smtp.host", "smtp.qq.com");
		// 端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
		props.put("mail.smtp.port", "587");
		// 此处填写你的账号
		props.put("mail.user", "2300819486@qq.com");
		// 此处的密码就是前面说的16位STMP口令
		props.put("mail.password", "aovjvlftfmwlecbf");

		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);

		// 设置邮件标题
		try {
			// 设置发件人
			InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
			message.setFrom(form);

			// 设置收件人的邮箱
			InternetAddress to = new InternetAddress("972031129@qq.com");
			message.setRecipient(RecipientType.TO, to);
			message.setSubject("测试邮件");
			// 设置邮件的内容体
			message.setContent("这是一封测试邮件", "text/html;charset=UTF-8");

			// 最后当然就是发送邮件啦
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 
	 * @param qq title content
	 */
	public static void sendOneNomal(Map<String,Object> params) {// 给一个人
																		// 普通邮件
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.qq.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.user", "2300819486@qq.com");
		props.put("mail.password", "aovjvlftfmwlecbf");
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		Session mailSession = Session.getInstance(props, authenticator);
		MimeMessage message = new MimeMessage(mailSession);
		try {
			InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
			message.setFrom(form);
			// 设置收件人的邮箱
			InternetAddress to = new InternetAddress(params.get("qq") + "@qq.com");
			message.setRecipient(RecipientType.TO, to);
			message.setSubject(params.get("title")+"");
			// 设置邮件的内容体
			message.setContent(params.get("content")+"", "text/html;charset=UTF-8");
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void sendOneHtml(String qq, String title, String content) {// 给一个人
																		// html邮件
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.qq.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.user", "2300819486@qq.com");
		props.put("mail.password", "aovjvlftfmwlecbf");
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		Session mailSession = Session.getInstance(props, authenticator);
		MimeMessage message = new MimeMessage(mailSession);
		try {
			InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
			message.setFrom(form);
			// 设置收件人的邮箱
			InternetAddress to = new InternetAddress(qq + "@qq.com");
			message.setRecipient(RecipientType.TO, to);
			message.setSubject(title);
			// 设置邮件的内容体
			message.setContent(content, "text/html;charset=UTF-8");
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void sendManyNormal(Map<String,Object> params) {// 群发普通邮件
		String [] qqs=(String[])params.get("qqs");
		
		for (int i = 0; i < qqs.length; i++) {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", "smtp.qq.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.user", "2300819486@qq.com");
			props.put("mail.password", "aovjvlftfmwlecbf");
			Authenticator authenticator = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					String userName = props.getProperty("mail.user");
					String password = props.getProperty("mail.password");
					return new PasswordAuthentication(userName, password);
				}
			};
			Session mailSession = Session.getInstance(props, authenticator);
			MimeMessage message = new MimeMessage(mailSession);
			try {
				InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
				message.setFrom(form);
				// 设置收件人的邮箱
				InternetAddress to = new InternetAddress(qqs[i] + "@qq.com");
				message.setRecipient(RecipientType.TO, to);
				message.setSubject(params.get("title")+"");
				// 设置邮件的内容体(这里可以从模板方法中获取content)
				message.setContent(params.get("content")+"", "text/html;charset=UTF-8");
				Transport.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			System.out.println("send successful");
		}

	}
	
	/**
	 * 获取content模板的方法  制定参数为map  方便后面参数拓展
	 */
	//模板一
	public static String getModel1(Map<String,Object> params){
		String content="<div style='width:100%;height:1000px;background-image: url(http://119.29.172.118:80/videoDemo/pic/backgorund.png);background-size:cover;color: #A99595;margin:0px auto;'><center><h1 style='margin:100px auto;color:#A99595;font-size:50px;paddin-top:56%;'><a style='color:red' href='"+params.get("url")+"?qq='"+params.get("qq")+"'>点我</a>进入您的私人影院!</h1></center></div>";
		return content;
	}
	
	/**
	 * 
	 * @param img  text1  text2  url btn1  btn2
	 * @return  content
	 */
	public static String getModel2(Map<String,Object> params){
	
		String content="<center><div style='clear:both'><img src='"+params.get("img")+"'></div><div>'"+params.get("text1")+"'</div><div>'"+params.get("text2")+"'</div><div style='text-align:center;'><div style='margin:10px auto;width:330px;height:26px'><div class='qqmail_postcard_reply_thanksbtn' style='border:1px solid #488825;-moz-border-radius:3px;-khtml-border-radius:3px;-webkit-border-radius:3px; border-radius:3px;float:left;margin-left:4px;'><a class='qqmail_postcard_reply_thanksbtn' style='font-size:14px;width: 150px;border:2px solid #488825;-moz-border-radius:3px;-khtml-border-radius:3px;-webkit-border-radius:3px; border-radius:1px;height:44px;line-height:44px; font-size:20px;cursor:pointer; background:#62A026; color:#FFF; font-weight:bold; border:1px solid #7CB04A;  padding:0 0 0px;display:block;text-decoration:none;' href='"+params.get("url")+"'>'"+params.get("btn1")+"' </a></div><div style='border:1px solid #488825;-moz-border-radius:3px;-khtml-border-radius:3px;-webkit-border-radius:3px; border-radius:3px;float:left;margin-left:10px;'><a class='qqmail_postcard_reply2' style='font-size:14px;width: 150px;border:2px solid #488825;-moz-border-radius:3px;-khtml-border-radius:3px;-webkit-border-radius:3px; border-radius:1px;height:44px;line-height:44px; font-size:20px;cursor:pointer; background:#62A026; color:#FFF; font-weight:bold; border:1px solid #7CB04A;  padding:0 0 0px;display:block;text-decoration:none;' href='"+params.get("url")+"' >'"+params.get("btn2")+"'</a></div><div class='clr'></div></div></div></div></center>";
		
		return content;
	}
	
	
	
	
	
	
	

}
