package com.javaee.bruno.email;

public class mail {

	public static void main(String[] args) {
		final String fromEmail = "brunoordoni@gmail.com";
		final String password = "***********";
		final String toEmail = "brunoordoni@hotmail.com";
		
		System.out.println("Iniciando o envio de email");
		
		EmailConfig config = new EmailConfig();
		
		config.sendEmail(fromEmail, password, toEmail, "Teste Primeira Aula!",
				"A ​ API do JavaMail​​ provê um ​ framework​​ independente de plataforma e protocolo para\n" + 
				"construir aplicações de email e mensageria. Neste tutorial, iremos construir uma aplicação\n" + 
				"para enviar emails aproveitando o servidor ​ SMTP​​ do Gmail.\n" + 
				"Iremos passar pela configuração do projeto com ​ Maven​​ e o envio de emails neste tutorial.");
		

	}

}
