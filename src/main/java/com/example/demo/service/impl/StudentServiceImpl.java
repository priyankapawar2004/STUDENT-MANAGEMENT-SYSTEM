package com.example.demo.service.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

	@Override
	public Page<StudentDTO> getStudents(int page, int size) {
		Log.info("list of student from : {}", page );
		
		PageRequest pagerequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "id"));
		
		
		return  studentRepository.findByActiveTrue(pagerequest)
		.map(student -> mapper.map(student, StudentDTO.class));
		
	}

	@Override
	@Transactional(readOnly = true)
	public StudentDTO getStudentById(Long id) {
		Students student = studentRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("No student found"));

           return mapper.map(student,  StudentDTO.class);

	}

	@Override
	public boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id) {
		Log.info("email from update student");
		
		return studentRepository. existsByEmailIgnoreCaseAndIdNot(email,id);
		
	}

	@Override
	public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
		Students student  = studentRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("No course found"));
		
		mapper.map(studentDTO, student);
		
		Students updated = studentRepository.save( student);
		
		return mapper.map(updated,StudentDTO.class);
	}
	@Override
	public List<StudentDTO> getAllStudents() {
		
		return studentRepository.findByActiveTrue().stream()
				.map(student -> mapper.map(student, StudentDTO.class))
		         .collect(Collectors.toList());
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
