package com.example.demo.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;



@Service
public class UserServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException("Invalid username"));
		
		
		return org.springframework.security.core.userdetails.User.withUsername(username)
				.password(user.getPassword())
				.disabled(!user.isActive())
				.build();
	}


}
