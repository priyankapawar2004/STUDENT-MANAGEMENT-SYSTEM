package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CourseDTO;
import com.example.demo.model.Courses;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{
	
	private static final Logger Log = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	
	private final CourseRepository courseRepository;
	private final ModelMapper mapper;
	
	CourseServiceImpl(CourseRepository courseRepository, ModelMapper mapper){
		this.courseRepository = courseRepository;
		this.mapper = mapper;
		
	}
	
	@Override
	public CourseDTO createCourse(CourseDTO courseDTO) {
		Log.info("creating course with code : {}", courseDTO.getCourseCode());
		Courses courses = mapper.map(courseDTO , Courses.class);
		courseRepository.save(courses);
		return mapper.map(courses, CourseDTO.class);
	}
	@Override
	public boolean existsByCode(String code) {
		Log.info("checking if code exists : {}", code);
		return courseRepository.existsByCourseCodeIgnoreCase(code);
	}
	
	@Override
	@Transactional(readOnly = true)
     public  Page<CourseDTO> getCourses(int page, int size) {
	Log.info("list of courses from : {}", page );
	
	PageRequest pagerequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "id"));
	
	
	return courseRepository.findByActiveTrue(pagerequest)
	.map(course -> mapper.map(course, CourseDTO.class));
	
	}
	@Override
	@Transactional(readOnly = true)
	public CourseDTO getCourseById(Long id) {
	Courses course = courseRepository.findById(id)
	                   .orElseThrow(() ->  new RuntimeException("No course found"));
	
	return mapper.map(course, CourseDTO.class);
}

	@Override
	public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
		Courses course = courseRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("No course found"));
		
		mapper.map(courseDTO, course);
		
		Courses updated = courseRepository.save(course);
		
		return mapper.map(updated,CourseDTO.class);
	}

	@Override
	public boolean existsByCourseCodeAndIdNot(String code, Long id) {
		Log.info("code from update page : {}, id :{} " , code , id);
		return courseRepository.existsByCourseCodeIgnoreCaseAndIdNot(code,id);
	}

	@Override
	public List<CourseDTO> getAllCourses() {
		
		return courseRepository.findByActiveTrue(Sort.by("courseName")).stream()
				.map(course -> mapper.map(course, CourseDTO.class))
		         .collect(Collectors.toList());
	}
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


