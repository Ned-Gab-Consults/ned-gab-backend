package com.abroad.scholarship.service.impl;

import com.abroad.scholarship.config.mail.NedGabSender;
import com.abroad.scholarship.dto.*;
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
    private final NedGabSender sender;

    @Override
    public ResponseEntity<Person> createAccount(PersonDto p) {
        Person person = Person.builder()
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .email(p.getEmail())
                .password(encoder.encode(p.getPassword()))
                .gender(p.getGender())
                .role(Role.USER)
                .status(p.getStatus())
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

    @Override
    public ResponseEntity<Person> updateDetails(PersonDto pdto) {
        UserDetails loggedInUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person p = personRepo.findPersonByEmail(loggedInUser.getUsername()).get();
        p.setStatus(pdto.getStatus());
        p.setEmail(pdto.getEmail());
        p.setGender(pdto.getGender());


        return ResponseEntity.ok(personRepo.save(p));
    }

    @Override
    public ResponseEntity<String> forgotPassword(EmailDto emailDto) {
        Person p = personRepo.findPersonByEmail(emailDto.getEmail()).get();
        String  password = getAlphaNumericString();
        p.setPassword(encoder.encode(password));
        personRepo.save(p);
        EmailSenderDto emailSenderDto = EmailSenderDto.builder()
                .subject("Change Of Password")
                .to(emailDto.getEmail())
                .content("Your password have been changed to "+password+", Please keep this email safe")
                .build();

        Thread t = new Thread(()->sender.send(emailSenderDto));
        t.start();

        return ResponseEntity.ok("Check your Registered Mail for Your new Password");
    }

    @Override
    public ResponseEntity<Person> changeRole(long id, UserTypeDto userTypeDto) {
        Person p = personRepo.findById(id).get();
        p.setRole(userTypeDto.getRole());
        return ResponseEntity.ok(personRepo.save(p));
    }

    private String getAlphaNumericString(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        log.info("here is the generated password {}",sb.toString());

        return sb.toString();
    }
}
