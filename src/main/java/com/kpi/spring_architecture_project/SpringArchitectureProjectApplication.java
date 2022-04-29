package com.kpi.spring_architecture_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
public class SpringArchitectureProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringArchitectureProjectApplication.class, args);

	}

}
