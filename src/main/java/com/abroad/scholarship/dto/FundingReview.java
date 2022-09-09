package com.abroad.scholarship.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FundingReview {
    private String contactLink;
    private String feedBack;
    private boolean notify;
}
