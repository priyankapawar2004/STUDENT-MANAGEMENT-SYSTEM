package com.example.demo.service.impl;

import com.example.demo.dto.CourseDTO;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;

public class CourseServiceImpl implements CourseService{
	
	private final CourseRepository courseRepository;
	
	CourseServiceImpl(CourseRepository courseRepository){
		this.courseRepository = courseRepository;
		
	}
	
	@Override
	public CourseDTO createCourse(CourseDTO courseDTO) {
		courseRepository.save()
		return null;
	}

}
