package com.abroad.scholarship.controller;

import com.abroad.scholarship.dto.Login;
import com.abroad.scholarship.dto.LoginDto;
import com.abroad.scholarship.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/acc")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Login> login(@RequestBody LoginDto loginDto) throws Exception {
        return loginService.login(loginDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(String token) {

        return loginService.logout(token);
    }
}
