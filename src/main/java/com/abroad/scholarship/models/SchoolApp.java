package com.abroad.scholarship.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolApp extends Audit{
    private String schoolName;
    private String country;
    private String city;
    private String course;
    private String reviewMessage;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    private boolean notify;


}
