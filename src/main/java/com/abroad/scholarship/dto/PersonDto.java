package com.abroad.scholarship.dto;

import com.abroad.scholarship.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PersonDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Gender gender;
}
