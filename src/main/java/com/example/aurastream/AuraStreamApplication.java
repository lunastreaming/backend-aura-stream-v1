package com.example.aurastream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AuraStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuraStreamApplication.class, args);
	}

}
