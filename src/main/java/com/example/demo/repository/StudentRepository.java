package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Students;

public interface StudentRepository extends  JpaRepository <Students, Long>{

	boolean existsByEmailIgnoreCase(String email);
	
	boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
	
	Page<Students> findByActiveTrue(Pageable pageable);
	
	List<Students> findByActiveTrue();
	
	@EntityGraph(attributePaths = {"enrollments" , "enrollments.course"})
	@Query(value = """
			        select  distinct s
			        from Students s
			        join s.enrollments e
			""" ,
			
		countQuery = """
					  select count (distinct s)
			          from Students s
			          join s.enrollments e
					""")
	Page<Students> findEnrolledStudents(Pageable pageable);
}
