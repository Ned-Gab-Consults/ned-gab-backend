package com.abroad.scholarship.models;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chat extends Audit{
    private String firstName;
    private String message;
}
