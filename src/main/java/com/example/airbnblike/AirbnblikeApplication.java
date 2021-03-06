package com.example.airbnblike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@EnableSwagger2
public class AirbnblikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirbnblikeApplication.class, args);
	}

}
