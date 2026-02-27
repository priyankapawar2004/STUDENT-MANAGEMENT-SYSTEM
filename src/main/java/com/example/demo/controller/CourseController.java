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

import com.example.demo.dto.CourseDTO;
import com.example.demo.service.CourseService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/course")
public class CourseController {
	private static final Logger Log = LoggerFactory.getLogger(CourseController.class);
	
	
	private final CourseService courseService;
	
	CourseController(CourseService courseService){
		this.courseService = courseService;
		
	}
	
	@GetMapping("/new")
	public String showCreateCourse(Model model) {
		Log.info("Get/course/new - showing create course page");
		model.addAttribute("courseDto", new CourseDTO ());
		return "add-course";
	}
 
	@GetMapping("/list")
	public String listCourses(@RequestParam(defaultValue = "0") int page,
	                          @RequestParam(defaultValue = "5") int size,
	                          Model model) 
	{
		Log.info("Get/course/list - showing course list page");
		
		Page <CourseDTO> courses = courseService.getCourses(page, size);
		model.addAttribute("courses" , courses);
		
		return "courses";
	}
	
	@PostMapping("/save")
	public String createCourse(@Valid @ModelAttribute("courseDto") CourseDTO courseDTO, 
		BindingResult bindingResult,
		Model model,
		RedirectAttributes redirectAttributes) {
		
		Log.info("post/course - create course request received");
			
			if(bindingResult.hasErrors()) {
				Log.error("post/course - page return due to validation error");
				return "add-course";
			}
			if(courseService.existsByCode(courseDTO.getCourseCode())) {
				Log.error("post/course - code must be unique");
				
				bindingResult.rejectValue("courseCode",null, "Code must be unique");
				return "add-course";
				
			}
			
			courseService.createCourse(courseDTO);
			 redirectAttributes.addFlashAttribute("message" , "Course is created succesfully!!");
			
			 Log.info("post/course - create course successfully created.");
			
			
			return"redirect:/course/list";
		}
	@GetMapping("/view/{id}")
	public String getCourseById(@PathVariable Long id, Model model) {
		CourseDTO course = courseService.getCourseById(id);
		model.addAttribute("course", course);
		
		return "view-course";
		
	}
	@GetMapping("/edit/{id}")
	public String editCourse(@PathVariable Long id, Model model) {
		CourseDTO course = courseService.getCourseById(id);
		model.addAttribute("courseDto", course);
		
		return "edit-course";
		
	}
	@PostMapping("/update/{id}")
	public String updateCourse(@PathVariable Long id,
			@Valid @ModelAttribute("courseDto") CourseDTO courseDTO, 
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {
		
	
	Log.info("post/update/{id} - update course request received.{}", id);
	
	if(bindingResult.hasErrors()) {
		Log.error("post/update/{id} - page return due to validation error");
		return "edit-course";
	}
	if(courseService. existsByCourseCodeAndIdNot(courseDTO.getCourseCode(), id)) {
		Log.error("post/update/{id} - code must be unique");
		
		bindingResult.rejectValue("courseCode",null, "Code must be unique");
		return "edit-course";
		
	}
	
	courseService.updateCourse(id , courseDTO);
	 redirectAttributes.addFlashAttribute("message" , "Course is updated succesfully!!");
	
	 Log.info("post/update/{id} - updated course successfully created.");
	
	
	return"redirect:/course/list";
}
	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	