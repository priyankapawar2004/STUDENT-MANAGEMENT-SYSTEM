package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.dto.CourseDTO;

public interface CourseService {
	CourseDTO createCourse(CourseDTO courseDTO);
	
	boolean existsByCode(String code);
	
	Page<CourseDTO> getCourses(int page, int size);
	
	CourseDTO getCourseById(Long id);

}
