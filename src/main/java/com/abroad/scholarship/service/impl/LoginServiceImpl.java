package com.abroad.scholarship.service.impl;

import com.abroad.scholarship.config.security.JwtTokenProvider;
import com.abroad.scholarship.dto.Dashboard;
import com.abroad.scholarship.dto.Login;
import com.abroad.scholarship.dto.LoginDto;
import com.abroad.scholarship.models.Person;
import com.abroad.scholarship.repository.BlackListedTokenRepository;
import com.abroad.scholarship.repository.PersonRepository;
import com.abroad.scholarship.service.BlackListService;
import com.abroad.scholarship.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final HttpServletResponse httpServletResponse;
    private final BlackListService blackListService;
    private final BlackListedTokenRepository blackListedTokenRepository;
    private final HttpServletRequest httpServletRequest;
    private final PersonRepository personRepo;


    @Override
    public ResponseEntity<Login> login(LoginDto loginDto) throws Exception {
        Authentication authentication ;
        String token;
        Person person = null;

        try{
            Authentication auth =  new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),loginDto.getPassword());
            System.out.println(auth);
            log.info("here is the Auth");
            authentication = authenticationManager.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenProvider.generateToken(authentication);
            httpServletResponse.setHeader("Authorization", token);
            person = personRepo.findPersonByEmail(loginDto.getEmail()).get();

        }
        catch (
                BadCredentialsException ex){
            log.error("can't validate login check email {} and password {}",loginDto.getEmail(), loginDto.getPassword());
            throw new Exception("incorrect user credentials", ex);

        }
        log.info("Jwt Auth Response {}", token);

        Login login = Login.builder()
                .id(person.getId())
                .token(token)
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .role(person.getRole())
                .status(person.getStatus())
                .gender(person.getGender())
                .build();
        return ResponseEntity.ok(login);
    }

    @Override
    public ResponseEntity<?> logout(String token) {
        return null;
    }

    @Override
    public ResponseEntity<Dashboard> getDashboard() {
        return null;
    }
}