package com.abroad.scholarship.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public
class Login {
    private long id;
    private String role;
    private String token;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String location;
    private String initail;
    private String supervisor;
    private LocalDate joinDate;
}
