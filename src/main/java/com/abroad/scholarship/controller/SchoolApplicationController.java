package com.abroad.scholarship.controller;

import com.abroad.scholarship.dto.ApplyDto;
import com.abroad.scholarship.dto.ReviewDto;
import com.abroad.scholarship.models.SchoolApp;
import com.abroad.scholarship.repository.SchoolAppRepository;
import com.abroad.scholarship.service.ApplySchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/application")
public class SchoolApplicationController {
    private final ApplySchoolService applySchoolService;

    @PostMapping("/apply")
    public ResponseEntity<SchoolApp> applyForASchool(ApplyDto applyDto){
     return applySchoolService.applyForSchool(applyDto);
    }

    @PostMapping("/give_review/{id}")
    public ResponseEntity<SchoolApp> giveReview(@RequestBody ReviewDto reviewDto, @PathVariable long id){
        return applySchoolService.giveReview(reviewDto, id);
    }

    @GetMapping("/{id}")
    ResponseEntity<List<SchoolApp>> fetchApplications(@PathVariable long id){
        return applySchoolService.fetchApplicationsOnId(id);
    }

    @PostMapping("/toggleNotice/{id}")
    ResponseEntity<SchoolApp> toogleNotice(@PathVariable long id){
        return applySchoolService.toggleNotice(id);
    }
}
