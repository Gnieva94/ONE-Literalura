package com.gnievassj.literarula;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterarulaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterarulaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World!");
	}
}
