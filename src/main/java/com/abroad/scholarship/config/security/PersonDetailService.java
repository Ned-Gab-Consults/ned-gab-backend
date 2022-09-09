package com.abroad.scholarship.config.security;

import com.abroad.scholarship.models.Person;
import com.abroad.scholarship.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PersonDetailService implements UserDetailsService {
    private PersonRepository personRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person user = personRepository.findPersonByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user with " + email+" found"));

        log.info("We are in get userby usename");
        List<SimpleGrantedAuthority> permissions = new ArrayList<>();

        permissions.add(new SimpleGrantedAuthority(user.getRole().name()));

        UserDetails userDetails = new User(user.getEmail(),user.getPassword(),permissions);

        log.info("This is form get userbyusername {}",userDetails);

        return userDetails;
    }

}
