package com.nhom5.blogonline.apiblogonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ApiBlogOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBlogOnlineApplication.class, args);
	}

}
