package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EnrollmentDTO;
import com.example.demo.model.Courses;
import com.example.demo.model.Enrollment;
import com.example.demo.model.Students;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements  EnrollmentService{
	
	private static final Logger Log = LoggerFactory.getLogger(EnrollmentServiceImpl.class);
	
	private final EnrollmentRepository enrollmentRepository;
	private final StudentRepository  studentRepository;
	private final CourseRepository courseRepository;
	
	EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository , StudentRepository  studentRepository , CourseRepository courseRepository){
		this.enrollmentRepository = enrollmentRepository;
		this. studentRepository = studentRepository;
		this.courseRepository = courseRepository;
		
	}
	
	@Override
	public void enrollStudentToCourses(EnrollmentDTO enrollmentDTO) {
		
		Log.info("request from enrollStudentToCourses");
		Students student = studentRepository.findById(enrollmentDTO.getStudentId())
				.orElseThrow(() -> new RuntimeException("student not found"));
		
		for (Long courseId : enrollmentDTO.getCourseIds ()) {
			Courses course = courseRepository.findById(courseId)
					.orElseThrow(() -> new RuntimeException("course not found"));	
			
			if(enrollmentRepository.existsByStudentIdAndCourseId(enrollmentDTO.getStudentId() , courseId)) {
				continue;
			}
			
			Enrollment enrollment = new Enrollment();
			enrollment.setStudent(student);
			enrollment.setCourse(course);
			
			enrollmentRepository.save(enrollment);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
				
		
	}

}
