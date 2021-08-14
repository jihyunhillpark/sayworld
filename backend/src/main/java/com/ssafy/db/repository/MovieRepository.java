package com.ssafy.db.repository;

import com.ssafy.db.entity.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieCategory,Long> {
    Optional<MovieCategory> findByMovieCategoryId(Long movieCategoryId);
}
