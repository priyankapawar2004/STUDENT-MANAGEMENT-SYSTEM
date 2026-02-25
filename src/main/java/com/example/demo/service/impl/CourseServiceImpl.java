package com.example.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CourseDTO;
import com.example.demo.model.Courses;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{
	
	private final CourseRepository courseRepository;
	private final ModelMapper mapper;
	
	CourseServiceImpl(CourseRepository courseRepository, ModelMapper mapper){
		this.courseRepository = courseRepository;
		this.mapper = mapper;
		
	}
	
	@Override
	public CourseDTO createCourse(CourseDTO courseDTO) {
		Courses courses = mapper.map(courseDTO , Courses.class);
		courseRepository.save(courses);
		return mapper.map(courses, CourseDTO.class);
	}
	@Override
	public boolean existsByCode(String code) {
		
		return courseRepository.existsByCourseCodeIgnoreCase(code);
	}

}
