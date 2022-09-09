package com.abroad.scholarship.service;

import com.abroad.scholarship.dto.ApplyDto;
import com.abroad.scholarship.dto.ReviewDto;
import com.abroad.scholarship.models.SchoolApp;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplySchoolService {
    ResponseEntity<SchoolApp> applyForSchool(ApplyDto applyDto);

    ResponseEntity<SchoolApp> giveReview(ReviewDto reviewDto, long id);

    ResponseEntity<List<SchoolApp>> fetchApplicationsOnId(long id);

    ResponseEntity<SchoolApp> toggleNotice(long id);
}
