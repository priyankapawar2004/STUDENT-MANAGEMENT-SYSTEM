package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EnrollmentDTO;
import com.example.demo.dto.EnrollmentSummaryDTO;
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
			
			if(enrollmentRepository.existsByStudent_IdAndCourse_Id(enrollmentDTO.getStudentId() , courseId)) {
				continue;
			}
			
			
			
			Enrollment enrollment = new Enrollment();
			enrollment.setStudent(student);
			enrollment.setCourse(course);
			
			//student.getEnrollments().add(enrollment);
		     //course.getEnrollments().add(enrollment);
			
			
			//studentRepository.save(student);
			
			enrollmentRepository.save(enrollment);
		}
		
	}
	  @Override
	    public List<Enrollment> getAllEnrollments() {
	        return enrollmentRepository.findAll();
		
		
				
		
	}

	  @Override
	  public Page<EnrollmentSummaryDTO> getEnrolledStudents(int page, int size) {
		  Log.info("list of enrolled students from : {}", page );
			
			PageRequest pagerequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "id"));
			
			
			return  studentRepository.findEnrolledStudents(pagerequest)
			.map(student -> {
				EnrollmentSummaryDTO dto = new EnrollmentSummaryDTO();
				dto.setStudentId(student.getId());
				dto.setStudentName(student.getFirstName() + " " +student.getLastName());
				dto.setEmail(student.getEmail());
				
				dto.setCourseCount(student.getEnrollments().size());
				BigDecimal totalFee = student.getEnrollments().stream()
						.map(enrollment -> enrollment.getCourse().getFee())
						.filter(fee -> fee != null)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
				         dto.setTotalFee(totalFee);
				
			return dto;
			});
			
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

}
