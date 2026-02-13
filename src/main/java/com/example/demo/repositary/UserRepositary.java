package com.example.demo.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface UserRepositary extends JpaRepository<User , Long>{
	
	boolean existsByUsername(String username);
	

}
