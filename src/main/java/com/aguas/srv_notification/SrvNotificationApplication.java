package com.aguas.srv_notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class SrvNotificationApplication {

	private static final Logger logger = LoggerFactory.getLogger(SrvNotificationApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando aplicação...");
		Dotenv dotenv = Dotenv.configure().load();
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
            logger.debug("Carregando variável de ambiente: {}", entry.getKey());
        });
		logger.info("Iniciando Spring Application...");
		SpringApplication.run(SrvNotificationApplication.class, args);
		logger.info("Aplicação iniciada com sucesso!");
	}

}
