package com.gnievassj.literarula.service;

import com.gnievassj.literarula.model.Authors;
import com.gnievassj.literarula.repository.AuthorsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.List;

@Service
public class AuthorsService {
    @Autowired
    private AuthorsRepository authorsRepository;
    public List<Authors> getAllAuthors(){
        return authorsRepository.findAll();
    }
    public List<Authors> getAuthorsAliveByYear(Integer year){
        return authorsRepository.getAuthorsAliveByYear(year);
    }
}
