package com.examplemvc.example_16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.examplemvc.example_16.repository")
@EntityScan("com.examplemvc.example_16.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class Example16Application {

	public static void main(String[] args) {
		SpringApplication.run(Example16Application.class, args);
	}

}
