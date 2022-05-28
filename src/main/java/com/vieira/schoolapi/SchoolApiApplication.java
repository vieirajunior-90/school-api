package com.vieira.schoolapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SchoolApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApiApplication.class, args);
	}

	@GetMapping("/")
	public String schoolApi() {
		return "<h1>School API</h1>";
	}

}
