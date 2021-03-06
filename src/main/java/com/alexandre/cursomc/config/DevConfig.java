package com.alexandre.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.alexandre.cursomc.services.DBService;
import com.alexandre.cursomc.services.EmailService;
import com.alexandre.cursomc.services.SmtpEmailService;

/**
 * Classe responsável por definições do perfil de desenvolvimento
 * @author Telles
 *
 */
@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	/**
	 * Pega o valor da chave do arquivo application-dev.properties
	 */
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instanciateDatabase() throws ParseException {
		if (!"create".equals(strategy))
			return false;
		
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}