package com.abroad.scholarship.dto;

import com.abroad.scholarship.enums.Gender;
import com.abroad.scholarship.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PersonDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Gender gender;
    private Status status;

}
