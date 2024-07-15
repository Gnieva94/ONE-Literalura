package com.gnievassj.literarula.repository;

import com.gnievassj.literarula.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books,Long> {
}
