package com.gnievassj.literarula.repository;

import com.gnievassj.literarula.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorsRepository extends JpaRepository<Authors,Long> {
    Optional<Authors> findByName(String name);
    @Query("SELECT a FROM Authors a WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear > :year)")
    List<Authors> getAuthorsAliveByYear(Integer year);
}
