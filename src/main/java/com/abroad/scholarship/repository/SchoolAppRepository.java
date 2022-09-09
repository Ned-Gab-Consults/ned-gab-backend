package com.abroad.scholarship.repository;

import com.abroad.scholarship.models.Person;
import com.abroad.scholarship.models.SchoolApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolAppRepository extends JpaRepository<SchoolApp,Long> {

    List<SchoolApp> findSchoolAppsByPerson(Person person);
}
