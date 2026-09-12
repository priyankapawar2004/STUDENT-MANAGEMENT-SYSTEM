package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.EnrollmentDTO;
import com.example.demo.dto.EnrollmentSummaryDTO;
import com.example.demo.service.CourseService;
import com.example.demo.service.EnrollmentService;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentController {
	
	private static final Logger Log = LoggerFactory.getLogger(EnrollmentController.class);
	
	private final CourseService courseService;
	private final StudentService studentService;
	private final EnrollmentService enrollmentService;
	
	public EnrollmentController(CourseService courseService,
			StudentService studentService, 
			EnrollmentService enrollmentService)
	{
		this.courseService = courseService;
		this.studentService = studentService;
		this.enrollmentService = enrollmentService;
	}
	
	@GetMapping("/showEnroll")
	public String showEnroll(Model model) {
		Log.info("Get/enrollments/showEnroll- showing enrollment page");
		
		model.addAttribute("enrollmentDto" , new EnrollmentDTO ());
		model.addAttribute("courseList", courseService.getAllCourses());
		model.addAttribute("studentList", studentService.getAllStudents());
		
		return "enroll-course";
	}
	
	@GetMapping("/enrollmentList")
	public String enrollmentList(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            Model model,
	 @RequestParam(value="message", required = false)String message)
    {
    	Log.info("Get/students- showing enrolled student list page");
    	
    	Page <EnrollmentSummaryDTO> students = enrollmentService.getEnrolledStudents(page, size);
		model.addAttribute("students" , students);
		model.addAttribute("message" ,message);
    	
    	return "enrolled-students";
	}
	
	
	
	
	@PostMapping("/enrollCourse")
	public String enrollCourse(@Valid @ModelAttribute("enrollmentDto") EnrollmentDTO enrollmentDto, 
		BindingResult bindingResult,
		Model model,
		RedirectAttributes redirectAttributes) {
		
		Log.info("post/enrollments/enrollCourse - create course request received");
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("courseList", courseService.getAllCourses());
			model.addAttribute("studentList", studentService.getAllStudents());
		
			return "enroll-course";
		}
		
		enrollmentService.enrollStudentToCourses(enrollmentDto);
		
		
		redirectAttributes.addFlashAttribute("message" , "Enrollment succesfully!!");
		
		 Log.info("post/enrollments/enrollCourse - Enrollment succesfully.");
		
		
		return"redirect:/enrollments/enrollmentList";
		
	}
	
	@GetMapping("/getStudentEnrollmentDetails/{id}")
	public String getStudentEnrollmentDetails (@PathVariable Long id, Model model,
			@RequestParam(defaultValue = "enrollments") String source) {
		
		EnrollmentSummaryDTO 	enrollmentSummaryDTO 
		= enrollmentService.findEnrolledStudentCourseDetails(id);
		
		model.addAttribute("enrollmentSummaryDTO" , enrollmentSummaryDTO);
		model.addAttribute("source" , source);
		
		
		return "enrollment-details";
		
		
	}
		
		
		
		
		
		
		
	
}
