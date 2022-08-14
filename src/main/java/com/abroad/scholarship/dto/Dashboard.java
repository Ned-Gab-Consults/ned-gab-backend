package com.abroad.scholarship.dto;

import lombok.*;

@Builder @Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class Dashboard {
    private long id;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String location;
    private String initail;
    private String supervisor;
    private String joinDate;
    private String grade;
}
