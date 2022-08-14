package com.abroad.scholarship.config.security;

import com.abroad.scholarship.models.Person;
import com.abroad.scholarship.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
@AllArgsConstructor
public class PersonDetailService implements UserDetailsService {
    private PersonRepository personRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person user = personRepository.findPersonByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user with " + email+" found"));
        return new User(user.getEmail(),user.getPassword(),new ArrayList(Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()))));
    }

}
