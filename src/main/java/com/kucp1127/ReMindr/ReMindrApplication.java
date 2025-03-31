package com.kucp1127.ReMindr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReMindrApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReMindrApplication.class, args);
	}

}
