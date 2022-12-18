package com.avdeenko_mironov.HousingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication//(scanBasePackages = "com.")
public class HousingSystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HousingSystemApplication.class);
	}
}
