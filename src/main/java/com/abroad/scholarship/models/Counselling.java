package com.abroad.scholarship.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Counselling extends Audit{
    private String topic;
    private String personalBackground;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    private String link;
    private LocalDateTime counsellingTime;
    private String feedback;

}
