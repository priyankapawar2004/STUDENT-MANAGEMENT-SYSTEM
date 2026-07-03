package com.example.demo.service;
import org.springframework.data.domain.Page;


import com.example.demo.dto.StudentDTO;

public interface StudentService {
	
	boolean existsByEmailIgnoreCase(String email);
	
	StudentDTO createStudent(StudentDTO studentDTO);
	
	Page<StudentDTO> getStudents(int page, int size);
	

}
