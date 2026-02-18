package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Configuration
public class DataIntializer {
	@Bean
	
	CommandLineRunner loadSampleData (UserRepository userRepository ,PasswordEncoder passwordEncoder) {
		
		return args -> {
			if( !userRepository.existsByUsername("Admin")) {
			User user = new User();
            user.setUsername("Admin");
            user.setPassword(passwordEncoder.encode("admin@123"));
            user.setActive(true);
            userRepository.save(user);
			
		}
		};
	}
}
