package com.abroad.scholarship.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Funding extends Audit{
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    private String backgound;
    private String reason;
    private String contactPerson;
    private String contactLink;
    private String feedBack;
}
