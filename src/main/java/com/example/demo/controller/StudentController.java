package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;

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
    public String listStudent() {
    	log.info("Get/students- showing list student page");
    	
    	return "students";
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

