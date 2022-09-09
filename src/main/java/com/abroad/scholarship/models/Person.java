package com.abroad.scholarship.models;

import com.abroad.scholarship.enums.Gender;
import com.abroad.scholarship.enums.Role;
import com.abroad.scholarship.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person extends Audit{
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    private List<Chat> charList = new ArrayList<>(50);

}
