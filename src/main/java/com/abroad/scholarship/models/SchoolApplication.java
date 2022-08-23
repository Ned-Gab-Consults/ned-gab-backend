package com.abroad.scholarship.models;

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
public class SchoolApplication extends Audit{
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @ManyToMany
    private List<SchoolNames> listOfSchools = new ArrayList<>();

}
