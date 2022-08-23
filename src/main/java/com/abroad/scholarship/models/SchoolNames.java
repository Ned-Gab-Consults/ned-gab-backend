package com.abroad.scholarship.models;

import lombok.*;

import javax.persistence.Entity;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolNames extends Audit{
    private String schoolName;
}
