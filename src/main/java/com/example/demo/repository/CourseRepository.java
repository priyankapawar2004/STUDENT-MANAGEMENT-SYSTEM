package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Courses;

public interface CourseRepository extends JpaRepository<Courses, Long> {
	
	boolean existsByCourseCodeIgnoreCase(String coursecode);
	
	boolean existsByCourseCodeIgnoreCaseAndIdNot(String coursecode, Long id);
	
	 Page<Courses> findByActiveTrue(Pageable pageable);
	 
	 List<Courses> findByActiveTrue(Sort sort);

}
