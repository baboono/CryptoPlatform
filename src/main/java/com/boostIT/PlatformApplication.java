package com.boostIT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
//import org.springframework.context.ApplicationContext;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Properties;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class PlatformApplication  {

	
//	
//	public static ConfigurableApplicationContext ctx;
//	public static Properties properties;
//	static Properties props = new Properties();
//	
//	static {
//		properties = new Properties();
//		try {
//			properties.load(PlatformApplication.class.getResourceAsStream("/application.properties"));
//		} catch (IOException e) {
//		throw new RuntimeException("Cannot read properties file");
//		}
//	}
//	
//	public static ConfigurableApplicationContext getCtx() {
//		return ctx;
//	}
//	public static void setCtx(ConfigurableApplicationContext ctx) {
//		PlatformApplication.ctx = ctx;
//	}
//	 @Bean
//	    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//	        return args -> {
//
//	            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//	            String[] beanNames = ctx.getBeanDefinitionNames();
//	            Arrays.sort(beanNames);
//	            for (String beanName : beanNames) {
//	                System.out.println(beanName);
//	            }
//
//	        };
//	    }
	
	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);   


	}
}