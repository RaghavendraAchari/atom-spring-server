package com.raghav.atom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@ComponentScan({"com.raghav.atom"})
public class AtomApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtomApplication.class, args);
	}

}
