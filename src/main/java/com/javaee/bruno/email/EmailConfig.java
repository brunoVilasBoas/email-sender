package com.javaee.bruno.email;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailConfig {
	
	public Properties getProperties() throws IOException {
		InputStream inputStream = null;
		try {
			Properties properties = new Properties();
			String propertyFile = "config.properties";
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propertyFile);
			
			if(inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("File '" + propertyFile + "' not found on classpath");
			}
			
			return properties;
		
		}catch (Exception e) {
			
			System.out.println("Exception: " + e);
		}finally {
			inputStream.close();
		}
		
		return null;
	}
	

	public void sendEmail( final String fromEmail, 
						final String password,
						String toEmail,
						String subject,
						String body){
		try {
			Authenticator auth = configAuth(fromEmail, password);
			
			Session session = Session.getInstance(getProperties(), auth);
			
			MimeMessage message = new MimeMessage(session);
			configHeaders(message);
			configContent(toEmail, subject, body, message);
			
			System.out.println("Mensagem Pronta!");
			
			Transport.send(message);
			System.out.println("Email enviado com sucesso!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	
	}


	private void configContent(String toEmail, String subject, String body, MimeMessage message) throws MessagingException, UnsupportedEncodingException {
		try {
			message.setFrom(new InternetAddress("brunoordoni@gmail.com", "Bruno Vilas Boas"));
			message.setReplyTo(InternetAddress.parse("brunoordoni@gmail.com", false));
			message.setSubject(subject, "UTF-8");
			message.setText(body, "UTF-8");
			message.setSentDate(new Date());
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void configHeaders(MimeMessage message) throws MessagingException{
		message.addHeader("content-type", "text/HTML; charset=UTF-8");
		message.addHeader("format", "flowed");
		message.addHeader("Content-Transfer-Encoding", "8bit");
	}


	private Authenticator configAuth(final String fromEmail, final String password) {
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		return auth;
	}
	

}
