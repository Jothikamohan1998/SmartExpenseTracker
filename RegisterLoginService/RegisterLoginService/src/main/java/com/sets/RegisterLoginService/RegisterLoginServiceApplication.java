package com.sets.RegisterLoginService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RegisterLoginServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterLoginServiceApplication.class, args);
	}

}
