package com.ssafy.sai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaiApplication.class, args);
	}

}
