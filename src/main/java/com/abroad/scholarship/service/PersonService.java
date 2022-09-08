package com.abroad.scholarship.service;

import com.abroad.scholarship.dto.EmailDto;
import com.abroad.scholarship.dto.PasswordDto;
import com.abroad.scholarship.dto.PersonDto;
import com.abroad.scholarship.models.Person;
import org.springframework.http.ResponseEntity;

public interface PersonService {
    ResponseEntity<Person> createAccount(PersonDto personDto);
    ResponseEntity<Person> changePassword(PasswordDto passwordDto);

    ResponseEntity<Person> updateDetails(PersonDto personDto);

    ResponseEntity<String> forgotPassword(EmailDto emailDto);
}
