package com.abroad.scholarship.repository;


import com.abroad.scholarship.models.BlackListedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListedTokenRepository extends JpaRepository<BlackListedToken, Long> {
    boolean existsByToken(String token);
}
