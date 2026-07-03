package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Students;

public interface StudentRepository extends  JpaRepository <Students, Long>{

	boolean existsByEmailIgnoreCase(String email);
	
	Page<Students> findByActiveTrue(Pageable pageable);
}
