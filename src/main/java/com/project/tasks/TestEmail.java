package com.project.tasks;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestEmail {
	public static void main(String args[])  
	{  
		System.out.println("Response code is: " );
		
			
			   String to = "techtret.team@gmail.com";
		        String toq = "guljana@gmail.com";
				   String from = "guljana@gmail.com";

		        String password1="techtret1234_";
		        String password="..biS0786!..";

			//Get properties object    
	        Properties props = new Properties();    
	        props.put("mail.smtp.host", "smtp.gmail.com");    
	        props.put("mail.smtp.socketFactory.port", "465");    
	        props.put("mail.smtp.socketFactory.class",    
	                  "javax.net.ssl.SSLSocketFactory");    
	        props.put("mail.smtp.auth", "true");    
	        props.put("mail.smtp.port", "465");    
	        //get Session   
	        Session session = Session.getDefaultInstance(props,    
	         new javax.mail.Authenticator() {    
	         protected PasswordAuthentication getPasswordAuthentication() {    
	         return new PasswordAuthentication(from,password);  
	         }    
	        });    
	        //compose message    
	        try {    
	         MimeMessage message = new MimeMessage(session);    
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
	         message.setSubject("hello guljana");    
	         message.setText("how are you");    
	         //send message  
	         Transport.send(message);    
	         System.out.println("message sent successfully");    
	        } catch (MessagingException e) {throw new RuntimeException(e);}    
		}

	}  

