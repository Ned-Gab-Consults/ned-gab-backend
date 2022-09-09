package com.abroad.scholarship.service.impl;

import com.abroad.scholarship.dto.FundingDto;
import com.abroad.scholarship.dto.FundingReview;
import com.abroad.scholarship.models.Funding;
import com.abroad.scholarship.models.Person;
import com.abroad.scholarship.repository.FundingRepository;
import com.abroad.scholarship.repository.PersonRepository;
import com.abroad.scholarship.service.FundingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FundingServiceImpl implements FundingService {
    private final FundingRepository fundingRepo;
    private final PersonRepository personRepo;

    @Override
    public ResponseEntity<Funding> applyForFunding(FundingDto fundingDto) {
        UserDetails loggedInUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person p = personRepo.findPersonByEmail(loggedInUser.getUsername()).get();
        Funding funding = Funding.builder()
                .person(p)
                .amountInDollar(fundingDto.getAmountInDollar())
                .reason(fundingDto.getReason())
                .notify(true)
                .build();
        return ResponseEntity.ok(fundingRepo.save(funding));
    }

    @Override
    public ResponseEntity<Funding> reviewFunding(FundingReview r, long id) {
        Funding funding = fundingRepo.findById(id).get();
        funding.setContactLink(r.getContactLink());
        funding.setFeedBack(r.getFeedBack());
        funding.setNotify(r.isNotify());
        return ResponseEntity.ok(fundingRepo.save(funding));
    }

    @Override
    public ResponseEntity<List<Funding>> fetchFundingList(long id) {
        Person p = personRepo.findById(id).get();
        List<Funding> fundingList = fundingRepo.findFundingByPerson(p);
        return ResponseEntity.ok(fundingList);
    }

    @Override
    public ResponseEntity<Funding> toogleFunding(long id) {
        Funding f = fundingRepo.findById(id).get();
        f.setNotify(!f.isNotify());
        return ResponseEntity.ok().body(fundingRepo.save(f));
    }
}
