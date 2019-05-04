package com.survey.microservice.surveydefinitionservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
 
@Configuration
public class BeanConfig {

	
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasenames("classpath:messages");
	    // setting default encoding
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	
	
	
	@Bean
	public ModelMapper modelMapper()
	{
		
		return new ModelMapper();
	}
}
