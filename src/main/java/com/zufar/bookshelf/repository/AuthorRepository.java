package com.zufar.bookshelf.repository;

import com.zufar.bookshelf.entity.Authorr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Authorr, Long> {}