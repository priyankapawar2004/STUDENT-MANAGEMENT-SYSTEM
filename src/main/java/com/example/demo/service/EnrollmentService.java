package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EnrollmentDTO;
import com.example.demo.model.Enrollment;

public interface EnrollmentService {

    void enrollStudentToCourses(EnrollmentDTO enrollmentDTO);
    
    List<Enrollment> getAllEnrollments();

}