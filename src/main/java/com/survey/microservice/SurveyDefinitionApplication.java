package com.survey.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaAuditing
@PropertySource(value = { "classpath:db.properties" })
public class SurveyDefinitionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurveyDefinitionApplication.class, args);
	}

}
