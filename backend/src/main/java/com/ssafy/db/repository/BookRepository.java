package com.ssafy.db.repository;

import com.ssafy.db.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookCategory,Long> {
    Optional<BookCategory> findByBookCategoryId(Long bookCategoryId);
}
