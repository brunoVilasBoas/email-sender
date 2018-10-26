package com.javaee.bruno.email;

public class mail {

	public static void main(String[] args) {
		final String fromEmail = "brunoordoni@gmail.com";
		final String password = "1994brunao";
		final String toEmail = "brunoordoni@hotmail.com";
		
		System.out.println("Iniciando o envio de email");
		
		EmailConfig config = new EmailConfig();
		
		config.sendEmail(fromEmail, password, toEmail, "Teste Primeira Aula!", "");
		

	}

}
