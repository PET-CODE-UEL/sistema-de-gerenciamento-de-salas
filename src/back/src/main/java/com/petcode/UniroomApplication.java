package com.petcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.petcode")
public class UniroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniroomApplication.class, args);
	}

}
