package com.csscv.auth.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class BugMail {

	private static JavaMailSender mailSender;

	@Autowired
	public BugMail(JavaMailSender mailSender) {
		BugMail.mailSender = mailSender;
	}

	
	/**
	 * This is static method used to send mail which does not have return type.
	 * It takes three string parameters. First parameter is the email address whom we want
	 * to send mail. Second parameter is the subject of email.
	 * Third parameter is the actual message we want to send.
	 * @param to
	 * @param subject
	 * @param messages
	 */
	public static synchronized void mailSender(String to, String subject, String messages) {
		try {
			
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("noreply@paytime.com.np");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText("text/html; charset=utf-8", messages);
			mailSender.send(message);
			
			
			
		} catch (Exception ex) {}

	}


	

}
