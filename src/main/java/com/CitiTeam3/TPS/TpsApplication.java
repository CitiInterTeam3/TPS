package com.CitiTeam3.TPS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TpsApplication {

	@RequestMapping("/")
	String hello(){
		return "hello world";
	}

	public static void main(String[] args) {
		SpringApplication.run(TpsApplication.class, args);
	}
}
