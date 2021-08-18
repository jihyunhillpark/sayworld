package com.ssafy.db.repository;

import com.ssafy.db.entity.Culture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CultureRepository extends JpaRepository<Culture, Long> {
    Optional<Culture> findByCultureTitleAndCultureCategory(String cultureTitle, Long cultureCategory);
}
