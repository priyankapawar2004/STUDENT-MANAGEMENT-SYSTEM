package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.CourseDTO;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@GetMapping("/new")
	public String showCreateCourse(Model model) {
		model.addAttribute("courseDto", new CourseDTO ());
		return "add-course";
	}
 
	@GetMapping("/list")
	public String listCourses() {
		return "courses";
	}
}
