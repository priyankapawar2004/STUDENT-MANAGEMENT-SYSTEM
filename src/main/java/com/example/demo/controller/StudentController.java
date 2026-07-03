package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.CourseDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
        }
    
    @GetMapping("/new")
    public String showCreateStudent(Model model) {
    	log.info("Get/new- showing create student page");
    	model.addAttribute("studentDto", new StudentDTO());
    	return "add-student";
    	
    }
    @GetMapping("/list")
    public String listStudent(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model)
    {
    	log.info("Get/students- showing list student page");
    	
    	Page <StudentDTO> students = studentService.getStudents(page, size);
		model.addAttribute("students" , students);
    	
    	return "students";
    	
    }
    @PostMapping("/save")
    public String createStudent(@Valid @ModelAttribute("studentDto") StudentDTO studentDTO , 
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {
    	
    	log.info("post/save -create student request received");
    	
    	if(bindingResult.hasErrors()) {
    		return "add-student";
    	}
    	
    	if( studentService.existsByEmailIgnoreCase(studentDTO.getEmail())) {
    		log.error("post/save - email must be unique");
    		
    		bindingResult.rejectValue("email",null, "email must be unique");
    		return "add-student";
    	}
    	
    	 studentService.createStudent(studentDTO);
    	 redirectAttributes.addFlashAttribute("message" , "student is added succesfully!!");
			
    	
    	
    	return"redirect:/students/list";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

