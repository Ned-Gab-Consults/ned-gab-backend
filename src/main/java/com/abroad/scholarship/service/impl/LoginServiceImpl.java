package com.abroad.scholarship.service.impl;

import com.abroad.scholarship.config.security.JwtTokenProvider;
import com.abroad.scholarship.dto.Dashboard;
import com.abroad.scholarship.dto.Login;
import com.abroad.scholarship.dto.LoginDto;
import com.abroad.scholarship.repository.BlackListedTokenRepository;
import com.abroad.scholarship.repository.PersonRepository;
import com.abroad.scholarship.service.BlackListService;
import com.abroad.scholarship.service.LoginService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final HttpServletResponse httpServletResponse;
    private final BlackListService blackListService;
    private final BlackListedTokenRepository blackListedTokenRepository;
    private final HttpServletRequest httpServletRequest;

    private final PersonRepository PersonRepository;


    @Override
    public ResponseEntity<Login> login(LoginDto loginDto) throws Exception {
        return null;
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