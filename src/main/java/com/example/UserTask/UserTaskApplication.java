package com.example.UserTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
//@EnableSwagger2
public class UserTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTaskApplication.class, args);
	}

}

