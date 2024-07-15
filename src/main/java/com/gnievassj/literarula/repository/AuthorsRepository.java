package com.gnievassj.literarula.repository;

import com.gnievassj.literarula.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<Authors,Long> {
}
