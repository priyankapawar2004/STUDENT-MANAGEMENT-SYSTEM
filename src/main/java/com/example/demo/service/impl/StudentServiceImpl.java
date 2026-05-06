package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.service.StudentService;

	@Service
	@Transactional
	
	
	public class StudentServiceImpl implements StudentService{
		private final StudentRepository  studentRopository;
		
	@Override
	public boolean existsByEmailIgnoreCase(String email) {
		
	return false;
	
}
	}
