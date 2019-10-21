package com.CitiTeam3.TPS;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintStream;

@SpringBootApplication
public class TpsApplication {


	public static void main(String[] args) {
		SpringApplication myApp=new SpringApplication(TpsApplication.class);
//		myApp.setBanner(new Banner() {
//			@Override
//			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
//				out.println("\n\n===CREATED BY CITI TEAM3=== \n\n");
//			}
//		});
		myApp.run(args);
	}
}
