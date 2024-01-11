package com.security.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages = {"com.security.boot"})
public class SecurityProgectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityProgectApplication.class, args);
	}

}
