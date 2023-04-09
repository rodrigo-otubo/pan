package com.bank.pan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PanApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanApplication.class, args);
	}

}
