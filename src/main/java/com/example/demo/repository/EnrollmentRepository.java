package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
	
	boolean existsByStudent_IdAndCourse_Id(Long studentId, Long courseId);

	@Query("""
			select count (distinct e.student.id) from
			Enrollment e
			where e.enrolledDate between :startDate and :endDate
			""")
	
	long countDistinctStudentByEnrollDateBetween
	       (@Param ("startDate") LocalDateTime startDate,
			@Param ("endDate") LocalDateTime endDate);
	
	@EntityGraph(attributePaths = {"student", "course"})
    List<Enrollment> findAll();
}
