package com.abroad.scholarship.dto;

import com.abroad.scholarship.models.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
public class ApplyDto {
    private String schoolName;
    private String country;
    private String city;
    private String course;
}
