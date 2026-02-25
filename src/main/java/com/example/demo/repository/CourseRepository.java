package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Courses;

public interface CourseRepository extends JpaRepository<Courses, Long> {
	
	boolean existsByCourseCodeIgnoreCase(String coursecode);

}
