package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.dto.EnrollmentDTO;
import com.example.demo.dto.EnrollmentSummaryDTO;
import com.example.demo.model.Enrollment;

public interface EnrollmentService {

    void enrollStudentToCourses(EnrollmentDTO enrollmentDTO);
    
    List<Enrollment> getAllEnrollments();
    
    Page<EnrollmentSummaryDTO> getEnrolledStudents(int page, int size);

}