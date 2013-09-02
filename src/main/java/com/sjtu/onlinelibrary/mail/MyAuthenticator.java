package com.sjtu.onlinelibrary.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {
	String userName=null;
	String password=null;
	 
	public MyAuthenticator(){
	}
	public MyAuthenticator(String username, String password) { 
		this.userName = username; 
		this.password = password; 
	} 
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(userName, password);
	}
	
//	public static void main(String[] args){
//        //这个类主要是设置邮件
//	  MailSenderInfo mailInfo = new MailSenderInfo(); 
//	  mailInfo.setMailServerHost("smtp.126.com"); 
//	  mailInfo.setMailServerPort("25"); 
//	  mailInfo.setValidate(true); 
//	  mailInfo.setUserName("chenmj4444@126.com"); 
//	  mailInfo.setPassword("4360449");//您的邮箱密码 
//	  mailInfo.setFromAddress("chenmj4444@126.com"); 
//	  mailInfo.setToAddress("chenmj4444@126.com"); //用户邮箱
//	  mailInfo.setSubject("在线数字图书馆系统新密码"); 
//	  mailInfo.setContent("这是您的新密码，请登录后修改。"); 
//        //这个类主要来发送邮件
//	  SimpleMailSender sms = new SimpleMailSender();
//         sms.sendTextMail(mailInfo);//发送文体格式 
//         sms.sendHtmlMail(mailInfo);//发送html格式
//	}
}
