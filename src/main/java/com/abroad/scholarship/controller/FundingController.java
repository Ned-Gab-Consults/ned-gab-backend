package com.abroad.scholarship.controller;

import com.abroad.scholarship.dto.FundingDto;
import com.abroad.scholarship.dto.FundingReview;
import com.abroad.scholarship.models.Funding;
import com.abroad.scholarship.service.FundingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@Slf4j
@RequiredArgsConstructor
public class FundingController {
    private final FundingService fundingService;

    @PostMapping("/apply")
    public ResponseEntity<Funding> applyFunding(@RequestBody FundingDto fundingDto){
        return fundingService.applyForFunding(fundingDto);
    }

    @PostMapping("/review/{id}")
    public ResponseEntity<Funding> reviewFunding(@RequestBody FundingReview fundingReview, @PathVariable long id){
        return fundingService.reviewFunding(fundingReview, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Funding>> fetchFundingList(@PathVariable long id){
        return  fundingService.fetchFundingList(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Funding> toogleFunding(@PathVariable long id){
        return fundingService.toogleFunding(id);
    }


}
