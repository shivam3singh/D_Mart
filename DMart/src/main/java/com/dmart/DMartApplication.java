package com.dmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;



@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "D-Mart", version = "1.0" , description = "DMart Stock Managment") )
public class DMartApplication {

	public static void main(String[] args) {
		SpringApplication.run(DMartApplication.class, args);
	}

}
