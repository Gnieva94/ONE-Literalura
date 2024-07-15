package com.gnievassj.literarula;

import com.gnievassj.literarula.principal.Principal;
import com.gnievassj.literarula.repository.AuthorsRepository;
import com.gnievassj.literarula.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterarulaApplication implements CommandLineRunner {

	@Autowired
	private BooksRepository booksRepository;
	@Autowired
	private AuthorsRepository authorsRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiterarulaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(booksRepository,authorsRepository);
		principal.menu();
	}
}
