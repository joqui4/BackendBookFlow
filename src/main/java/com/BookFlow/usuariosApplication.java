package com.BookFlow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class usuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(usuariosApplication.class, args);
	}

}