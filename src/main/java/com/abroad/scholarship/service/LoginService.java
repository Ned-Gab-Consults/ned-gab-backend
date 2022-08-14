package com.abroad.scholarship.service;



import com.abroad.scholarship.dto.Dashboard;
import com.abroad.scholarship.dto.Login;
import com.abroad.scholarship.dto.LoginDto;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<Login> login(LoginDto loginDto) throws Exception;
    ResponseEntity<?> logout(String token);
    ResponseEntity<Dashboard> getDashboard();

}
