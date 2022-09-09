package com.abroad.scholarship.repository;

import com.abroad.scholarship.models.Funding;
import com.abroad.scholarship.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundingRepository extends JpaRepository<Funding, Long> {
    List<Funding> findFundingByPerson(Person person);
}
