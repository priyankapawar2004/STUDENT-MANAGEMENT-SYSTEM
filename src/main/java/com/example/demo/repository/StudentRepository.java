package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Students;

public interface StudentRepository extends  JpaRepository <Students, Long>{

	boolean existsByEmailIgnoreCase(String email);
	
	boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
	
	Page<Students> findByActiveTrue(Pageable pageable);
	
	List<Students> findByActiveTrue();
}
