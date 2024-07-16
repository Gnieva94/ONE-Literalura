package com.gnievassj.literarula.repository;

import com.gnievassj.literarula.model.Books;
import com.gnievassj.literarula.model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books,Long> {
    List<Books> findByLanguage(Languages languages);
}
