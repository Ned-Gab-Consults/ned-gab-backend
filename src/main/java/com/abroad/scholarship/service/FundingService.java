package com.abroad.scholarship.service;


import com.abroad.scholarship.dto.FundingDto;
import com.abroad.scholarship.dto.FundingReview;
import com.abroad.scholarship.models.Funding;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FundingService {
    ResponseEntity<Funding> applyForFunding(FundingDto fundingDto);

    ResponseEntity<Funding> reviewFunding(FundingReview fundingReview, long id);

    ResponseEntity<List<Funding>> fetchFundingList(long id);

    ResponseEntity<Funding> toogleFunding(long id);
}
