package com.example.airbnblike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AirbnblikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirbnblikeApplication.class, args);
	}

}
