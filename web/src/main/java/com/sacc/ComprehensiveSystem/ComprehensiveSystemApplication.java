package com.sacc.ComprehensiveSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jms.annotation.EnableJms;

@EnableCaching
@EnableJms
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ComprehensiveSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComprehensiveSystemApplication.class, args);
	}

}
