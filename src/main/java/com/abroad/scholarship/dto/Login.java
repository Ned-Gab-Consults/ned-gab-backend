package com.abroad.scholarship.dto;

import com.abroad.scholarship.enums.Gender;
import com.abroad.scholarship.enums.Role;
import com.abroad.scholarship.enums.Status;
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
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Status status;
    private Gender gender;
    private String token;
}
