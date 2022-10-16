package com.example.tj.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.tj.model.SecurityUser;
import com.example.tj.repository.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	
	public JpaUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		return this.userRepository
				.findByUsername(username)
				.map(SecurityUser::new)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
	}
	
}
