package com.abroad.scholarship.repository;

import com.abroad.scholarship.models.FileDoc;
import com.abroad.scholarship.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDocRepository extends JpaRepository<FileDoc, Long> {
    List<FileDoc> findFileDocsByPerson(Person person);
}
