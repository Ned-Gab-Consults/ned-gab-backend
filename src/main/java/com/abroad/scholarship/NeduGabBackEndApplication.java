package com.abroad.scholarship;

import com.abroad.scholarship.dto.PersonDto;
import com.abroad.scholarship.enums.Gender;
import com.abroad.scholarship.enums.Role;
import com.abroad.scholarship.enums.Status;
import com.abroad.scholarship.models.Person;
import com.abroad.scholarship.repository.PersonRepository;
import com.abroad.scholarship.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
@Slf4j
@SpringBootApplication
public class NeduGabBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeduGabBackEndApplication.class, args);
    }


//    @Bean
//    CommandLineRunner run (PersonRepository personRepo, PasswordEncoder encoder){
//        return  args -> {
//
//
//
//
//            Person p = Person.builder()
//                    .firstName("Darlington")
//                    .lastName("Olelewe")
//                    .email("o.darlington@gmail.com")
//                    .password(encoder.encode("12345"))
//                    .role(Role.ADMIN)
//                    .gender(Gender.MALE)
//                    .status(Status.ALL)
//                    .build();
//            Person p2 = Person.builder()
//                    .firstName("Dennis")
//                    .lastName("Okoye")
//                    .gender(Gender.MALE)
//                    .email("okoyedennis7@gmail.com")
//                    .password(encoder.encode("12345"))
//                    .role(Role.USER)
//                    .status(Status.ALL)
//                    .build();
//
//            personRepo.save(p);
//            personRepo.save(p2);
//
//            log.info("{} {}",p, p2);
//
//        };
//    }
}
