package com.gl.scheduler;



import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SendMail {

	private  final String HOST_NAME = "smtp.gmail.com";
	private final String USER_MAIL_ID ="noreplyatexpensetracker";  // noreplyatexpensetracker username
	private final  String AUTH_PWD = "expense@123#";    // passwd
	public  boolean  sendMessageToMail(String mailid,String subject , String msg)	{

		try {
			
			Properties props = System.getProperties();
			props.put("mail.smtp.host", HOST_NAME);
			props.put("mail.smtp.user", USER_MAIL_ID);
		    props.put("mail.smtp.password", AUTH_PWD);
		    props.put("mail.smtp.port", "587");

			props.put("mail.smtp.starttls.enable","true");

			String[] to = {mailid}; // Put id of the receiver here

			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);
		
			message.setFrom(new InternetAddress(USER_MAIL_ID));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			for( int i=0; i < to.length; i++ ) { 
				toAddress[i] = new InternetAddress(to[i]);
			}
			System.out.println(Message.RecipientType.TO);

			for( int i=0; i < toAddress.length; i++) { 
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);	
			}
			message.setSubject(subject);
			message.setContent(msg, "text/html; charset=utf-8");


			Transport transport = session.getTransport("smtps");
			transport.connect(HOST_NAME, "noreplyatexpensetracker", "expense@123#");
			transport.sendMessage(message, message.getAllRecipients());
			
			transport.close();
			return true;	
			

		}catch(Exception e)	{
			e.printStackTrace();
			return false;
		}
	}


}