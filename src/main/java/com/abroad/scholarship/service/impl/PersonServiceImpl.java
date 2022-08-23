package com.abroad.scholarship.service.impl;

import com.abroad.scholarship.dto.PasswordDto;
import com.abroad.scholarship.dto.PersonDto;
import com.abroad.scholarship.enums.Role;
import com.abroad.scholarship.models.Person;
import com.abroad.scholarship.repository.PersonRepository;
import com.abroad.scholarship.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j @Service @RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepo;
    private final PasswordEncoder encoder;

    @Override
    public ResponseEntity<Person> createAccount(PersonDto p) {
        Person person = Person.builder()
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .email(p.getEmail())
                .password(encoder.encode(p.getPassword()))
                .gender(p.getGender())
                .role(Role.USER)
                .build();

        personRepo.save(person);
        return ResponseEntity.ok().body(person);
    }

    @Override
    public ResponseEntity<Person> changePassword(PasswordDto passwordDto) {
        UserDetails loggedInUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person p = personRepo.findPersonByEmail(loggedInUser.getUsername()).get();
        if(!encoder.matches(passwordDto.getOldPassword(),p.getPassword())) throw new RuntimeException("Old password is wrong");
        if(!passwordDto.getConfirmPassword().equals(passwordDto.getNewPassword())){
            log.error("Inconsistent new password");
            throw new RuntimeException("Old password is wrong");}

        p.setPassword(encoder.encode(passwordDto.getConfirmPassword()));
        log.info("{} set to new password", passwordDto.getNewPassword());
        return ResponseEntity.ok().body(personRepo.save(p));
    }
}
