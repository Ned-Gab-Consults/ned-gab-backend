package com.abroad.scholarship.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

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
    private String reason;
    private BigDecimal amountInDollar;
    private String contactLink;
    private String feedBack;
    private boolean notify;
}
