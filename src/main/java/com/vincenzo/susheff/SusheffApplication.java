package com.vincenzo.susheff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SusheffApplication {

	public static void main(String[] args) {
		SpringApplication.run(SusheffApplication.class, args);
	}

}
