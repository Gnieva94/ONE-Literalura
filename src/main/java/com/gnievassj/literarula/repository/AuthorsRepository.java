package com.gnievassj.literarula.repository;

import com.gnievassj.literarula.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorsRepository extends JpaRepository<Authors,Long> {
    Optional<Authors> findByName(String name);
}
