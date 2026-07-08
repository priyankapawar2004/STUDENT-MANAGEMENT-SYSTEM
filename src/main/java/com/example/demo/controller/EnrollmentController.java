package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.EnrollmentDTO;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {
	
	private static final Logger Log = LoggerFactory.getLogger(EnrollmentController.class);
	
	private final CourseService courseService;
	private final StudentService studentService;
	
	public EnrollmentController(CourseService courseService,StudentService studentService) {
		this.courseService = courseService;
		this.studentService = studentService;
	}
	
	@GetMapping("/showEnroll")
	public String showEnroll(Model model) {
		Log.info("Get/enrollments/showEnroll- showing enrollment page");
		
		model.addAttribute("enrollmentDto" , new EnrollmentDTO ());
		model.addAttribute("courseList", courseService.getAllCourses());
		model.addAttribute("studentList", studentService.getAllStudents());
		
		return "enroll-course";
	}

}
