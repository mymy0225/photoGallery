package com.example.photoGallery.services;

import java.util.List;
import java.util.Optional;

import com.example.photoGallery.entities.User;

public interface UserService {
	List<User> findAll();
	Optional<User> findById(long id);
	void register(String name, String email, String password, String[] roles);
}
