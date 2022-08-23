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
public class StepsVisa extends Audit{
    private String Step;
    private boolean completed;
}
