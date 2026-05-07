package com.example.demo.service;
import com.example.demo.dto.StudentDTO;

public interface StudentService {
	
	boolean existsByEmailIgnoreCase(String email);
	
	StudentDTO createStudent(StudentDTO studentDTO);
	

}
