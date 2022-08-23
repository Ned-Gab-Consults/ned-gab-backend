package com.abroad.scholarship.controller;

import com.abroad.scholarship.dto.Login;
import com.abroad.scholarship.dto.LoginDto;
import com.abroad.scholarship.dto.PasswordDto;
import com.abroad.scholarship.dto.PersonDto;
import com.abroad.scholarship.models.Person;
import com.abroad.scholarship.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/person") @RestController @RequiredArgsConstructor
public class PersonController {
private final PersonService personService;



    @PostMapping("/")
    public ResponseEntity<Person> createAccount(@RequestBody PersonDto personDto){
        return personService.createAccount(personDto);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Person> changePassword(@RequestBody PasswordDto passwordDto){
        return personService.changePassword(passwordDto);
    }
}
