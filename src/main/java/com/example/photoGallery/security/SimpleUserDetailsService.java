package com.example.photoGallery.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.photoGallery.repositories.UserRepository;

@Service
public class SimpleUserDetailsService implements UserDetailsService{
	private final UserRepository userRepository;
	
	public SimpleUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		assert(email != null);
		return userRepository
				.findByEmail(email)
				.map(SimpleLoginUser::new)
				.orElseThrow();
	}
}
