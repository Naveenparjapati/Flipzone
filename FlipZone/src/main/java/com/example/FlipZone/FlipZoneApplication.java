package com.example.FlipZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
public class FlipZoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlipZoneApplication.class, args);
	}

	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
