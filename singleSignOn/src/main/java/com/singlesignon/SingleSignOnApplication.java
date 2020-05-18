package com.singlesignon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.singlesignon.rest.LoginController;

@SpringBootApplication
@ComponentScan(basePackages = "com.singlesignon")
public class SingleSignOnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SingleSignOnApplication.class, args);
	}

}
