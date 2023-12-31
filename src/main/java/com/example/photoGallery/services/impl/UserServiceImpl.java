package com.example.photoGallery.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.photoGallery.entities.User;
import com.example.photoGallery.repositories.UserRepository;
import com.example.photoGallery.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Optional<User> findById(long id){
		return userRepository.findById(id);
	}
	
	@Transactional
	@Override
	public void register(String name, String email, String password, String[] roles) {
		if(userRepository.findByEmail(email).isPresent()) {
			throw new RuntimeException("該当のメールアドレスは登録済みです。");
		}
		String encodedPassword = passwordEncode(password);
		String joinedRoles = joinRoles(roles);
		User user = new User(null, name, email, encodedPassword, joinedRoles, Boolean.TRUE, null, null, null);
		
		userRepository.saveAndFlush(user);
	}
	
	private String passwordEncode(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
	
	private String joinRoles(String[] roles) {
		if(roles == null || roles.length == 0) {
			return "";
		}
		return Stream.of(roles)
				.map(String::trim)
				.map(String::toUpperCase)
				.collect(Collectors.joining(","));
	}
}
