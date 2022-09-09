package com.abroad.scholarship.dto;

import com.abroad.scholarship.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserTypeDto {
    private Role role;
}
