package com.example.demo.service.impl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Students;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

	@Service
	@Transactional
	
	
	public class StudentServiceImpl implements StudentService{
		
		private static final Logger Log = LoggerFactory.getLogger(StudentServiceImpl.class);
		
		private final StudentRepository  studentRepository;
		private final ModelMapper mapper;
		
		public  StudentServiceImpl(StudentRepository  studentRepository,ModelMapper mapper) {
			this. studentRepository = studentRepository;
			this.mapper = mapper;
			
		}
		
	@Override
	public boolean existsByEmailIgnoreCase(String email) {
		Log.info("email from create student");
		
	return studentRepository. existsByEmailIgnoreCase(email);
	
}
	@Override
	public StudentDTO createStudent(StudentDTO studentDTO) {
		Log.info("saving student data");
		
		Students student=mapper.map(studentDTO, Students.class);
		Students saved = studentRepository.save(student);
		
		return mapper.map(saved, StudentDTO.class);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
