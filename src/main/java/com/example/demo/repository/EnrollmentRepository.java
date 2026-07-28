package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
	
	boolean existsByStudent_IdAndCourse_Id(Long studentId, Long courseId);

	
	@EntityGraph(attributePaths = {"student", "course"})
    List<Enrollment> findAll();
}
