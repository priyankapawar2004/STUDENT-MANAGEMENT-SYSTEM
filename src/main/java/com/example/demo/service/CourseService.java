package com.example.demo.service;

import com.example.demo.dto.CourseDTO;

public interface CourseService {
	CourseDTO createCourse(CourseDTO courseDTO);
	
	boolean existsByCode(String code);
	
	

}
