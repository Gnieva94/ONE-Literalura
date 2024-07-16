package com.gnievassj.literarula.service;

import com.gnievassj.literarula.model.Authors;
import com.gnievassj.literarula.model.Books;
import com.gnievassj.literarula.repository.AuthorsRepository;
import com.gnievassj.literarula.repository.BooksRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.List;

@Service
public class BooksService {
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private AuthorsRepository authorsRepository;
    @Transactional
    public void save(Books book) throws UnexpectedRollbackException {
        Authors author = authorsRepository.findByName(book.getAuthor().getName())
                .orElseGet(()-> authorsRepository.save(book.getAuthor()));
        book.setAuthor(author);
        booksRepository.save(book);
    }
    public List<Books> getAllBooks(){
        return booksRepository.findAll();
    }
}
