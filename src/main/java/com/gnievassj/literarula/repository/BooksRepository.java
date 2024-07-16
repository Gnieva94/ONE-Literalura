package com.gnievassj.literarula.repository;

import com.gnievassj.literarula.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books,Long> {
//    @Query("SELECT b FROM Books b")
//    List<Books> getAllBooks();
}
