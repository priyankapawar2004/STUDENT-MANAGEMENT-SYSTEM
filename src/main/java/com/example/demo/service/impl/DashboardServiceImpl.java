package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DashboardStatsDTO;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.DashboardService;


@Service

public class DashboardServiceImpl implements DashboardService {
	
	private final EnrollmentRepository enrollmentRepository;
	private final StudentRepository  studentRepository;
	private final CourseRepository courseRepository;
	
	
	DashboardServiceImpl(EnrollmentRepository enrollmentRepository , 
			StudentRepository  studentRepository ,
			CourseRepository courseRepository,
			ModelMapper mapper)
	{
		this.enrollmentRepository = enrollmentRepository;
		this. studentRepository = studentRepository;
		this.courseRepository = courseRepository;
		
	}

	@Override
	 public DashboardStatsDTO getDashboardStats () { //{java 6},{mysql 2}
		long totalStudents = studentRepository.count();
		long totalCourse = courseRepository.count();
		
		String topPerformingCourse = getTopPerformingCourse();
		
		YearMonth currentMonth = YearMonth.now ();
		LocalDateTime startDate = currentMonth.atDay(1).atStartOfDay();
		LocalDateTime endDate = currentMonth.atEndOfMonth().atTime(LocalTime.MAX);
		
		
		long studentEnrolledThisMonth = enrollmentRepository.countDistinctStudentByEnrollDateBetween(startDate, endDate);
		
		DashboardStatsDTO getDashboardStatsDTO = new DashboardStatsDTO();
		
		getDashboardStatsDTO.setTotalStudents(totalStudents);
		getDashboardStatsDTO.setTotalCourses(totalCourse);
		getDashboardStatsDTO.setTopPerformingCourse(topPerformingCourse);
		getDashboardStatsDTO.setStudentsEnrolledThisMonth(studentEnrolledThisMonth);
		
		return getDashboardStatsDTO;
	}
	
	private String getTopPerformingCourse() {
		
		
		return enrollmentRepository.findAll()
				.stream()
				.collect(Collectors.groupingBy(e -> e.getCourse().getCourseName(), Collectors.counting()))
				.entrySet()
				.stream() 
				.max(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey)
				.orElse("N/A");
				
	}

}




























