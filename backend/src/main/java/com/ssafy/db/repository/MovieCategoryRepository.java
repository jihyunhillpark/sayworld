package com.ssafy.db.repository;

import com.ssafy.db.entity.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieCategoryRepository extends JpaRepository<MovieCategory, Long> {
}
