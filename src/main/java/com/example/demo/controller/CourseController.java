package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.CourseDTO;
import com.example.demo.service.CourseService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	private CourseService courseService;
	
	CourseController(CourseService courseService){
		this.courseService = courseService;
		
	}
	
	@GetMapping("/new")
	public String showCreateCourse(Model model) {
		model.addAttribute("courseDto", new CourseDTO ());
		return "add-course";
	}
 
	@GetMapping("/list")
	public String listCourses() {
		return "courses";
	}
	
	@PostMapping("/save")
	public String createCourse(@Valid @ModelAttribute("courseDto") CourseDTO courseDTO, 
		BindingResult bindingResult,
		Model model,
		RedirectAttributes redirectAttributes) {
			
			if(bindingResult.hasErrors()) {
				return "add-course";
			}
			if(courseService.existsByCode(courseDTO.getCourseCode())) {
				bindingResult.rejectValue("coursecode",null, "Code must be unique");
				return "add-course";
				
			}
			courseService.createCourse(courseDTO);
			 redirectAttributes.addAttribute("message" , "Course is created succesfully!!");
			
			
			
			
			return"/course//list";
		}
	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	