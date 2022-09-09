package com.abroad.scholarship.controller;

import com.abroad.scholarship.dto.*;
import com.abroad.scholarship.models.Person;
import com.abroad.scholarship.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/person") @RestController @RequiredArgsConstructor
public class PersonController {
private final PersonService personService;



    @PostMapping("")
    public ResponseEntity<Person> createAccount(@RequestBody PersonDto personDto){
        return personService.createAccount(personDto);
    }
    @PatchMapping("")
    public ResponseEntity<Person> editAccount(@RequestBody PersonDto personDto){
        return personService.updateDetails(personDto);
    }

    @PostMapping("/change_password")
    public ResponseEntity<Person> changePassword(@RequestBody PasswordDto passwordDto){
        return personService.changePassword(passwordDto);
    }

    @PostMapping("/forgot_password")
    public ResponseEntity<String> forgotPassword(@RequestBody EmailDto emailDto){
        return personService.forgotPassword(emailDto);
    }

    @PostMapping("/change_role/{id}")
    public ResponseEntity<Person> changeUserType(@RequestBody UserTypeDto userTypeDto, @PathVariable long id){
        return personService.changeRole(id, userTypeDto);
    }


}
