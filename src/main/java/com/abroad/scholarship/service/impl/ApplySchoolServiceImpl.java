package com.abroad.scholarship.service.impl;

import com.abroad.scholarship.dto.ApplyDto;
import com.abroad.scholarship.dto.ReviewDto;
import com.abroad.scholarship.models.Person;
import com.abroad.scholarship.models.SchoolApp;
import com.abroad.scholarship.repository.PersonRepository;
import com.abroad.scholarship.repository.SchoolAppRepository;
import com.abroad.scholarship.service.ApplySchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j @RequiredArgsConstructor
public class ApplySchoolServiceImpl implements ApplySchoolService {
    private final PersonRepository personRepo;
    private final SchoolAppRepository schoolAppRepo;


    @Override
    public ResponseEntity<SchoolApp> applyForSchool(ApplyDto applyDto) {
        UserDetails loggedInUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person p = personRepo.findPersonByEmail(loggedInUser.getUsername()).get();
        SchoolApp sa = SchoolApp.builder()
                .schoolName(applyDto.getSchoolName())
                .country(applyDto.getCountry())
                .city(applyDto.getCity())
                .course(applyDto.getCourse())
                .notify(true)
                .build();
        return ResponseEntity.ok(schoolAppRepo.save(sa));
    }

    @Override
    public ResponseEntity<SchoolApp> giveReview(ReviewDto reviewDto, long id) {
        SchoolApp sa = schoolAppRepo.findById(id).get();
        sa.setReviewMessage(reviewDto.getReviewMessage());
        sa.setNotify(reviewDto.isNotify());
        return ResponseEntity.ok(schoolAppRepo.save(sa));
    }

    @Override
    public ResponseEntity<List<SchoolApp>> fetchApplicationsOnId(long id) {
        Person p = personRepo.findById(id).get();
        List<SchoolApp> schoolApps = schoolAppRepo.findSchoolAppsByPerson(p);
        return ResponseEntity.ok(schoolApps);
    }

    @Override
    public ResponseEntity<SchoolApp> toggleNotice(long id) {
        SchoolApp sa = schoolAppRepo.findById(id).get();
        sa.setNotify(!sa.isNotify());
        return ResponseEntity.ok(schoolAppRepo.save(sa));
    }
}
