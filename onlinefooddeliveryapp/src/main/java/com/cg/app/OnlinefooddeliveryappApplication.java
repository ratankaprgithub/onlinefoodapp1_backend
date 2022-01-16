package com.cg.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class OnlinefooddeliveryappApplication {

	
	public static void main(String[] args) {
	
		log.info("application starts...");
		SpringApplication.run(OnlinefooddeliveryappApplication.class, args);
	}

}
