package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Students;

public interface StudentRopository extends  JpaRepository <Students, Long>{

	boolean existsByEmailIgnoreCase(String email);
}
