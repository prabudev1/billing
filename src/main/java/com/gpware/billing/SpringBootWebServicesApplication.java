package com.gpware.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.gpware.billing", "com.gpware.billing.controller", "com.gpware.billing.services", "com.gpware.billing.dao", "com.gpware.billing.helper" })
@EntityScan("com.gpware.billing.model")
public class SpringBootWebServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebServicesApplication.class, args);
	}
}