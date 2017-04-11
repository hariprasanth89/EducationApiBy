package com.MyComp.EducationApi.utility;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;



/**
 * @author
 *
 */
public class ApplicationMailer {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void setMailSender(JavaMailSender mailSender) {
		System.out.println(".........setMailSender.......");
		this.mailSender = mailSender;
	}
	
		public int sendMail(String path,  final String to,
			final String subject, final String msg) {
		MimeMessage message = mailSender.createMimeMessage();
		System.out.println("Welcome to sender.......");

		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
		
			helper.setTo(to);
			helper.setFrom("dewmobilitynest@gmail.com");
			helper.setSubject(subject);
			helper.setText(msg, true);

			
			/*FileSystemResource logo= new FileSystemResource(new File(
					path+Constants.LOGO_PATH));
			System.out.println("filePath:"+new File(path+Constants.LOGO_PATH));
			helper.addInline("logo", logo);*/
			
			
			mailSender.send(message);
			return 1; // positive number for Success

		} catch (MessagingException e1) {
			e1.printStackTrace();
		}

		return -1; // negative number for failed
	}
}
