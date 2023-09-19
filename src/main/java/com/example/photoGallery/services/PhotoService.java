package com.example.photoGallery.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.photoGallery.entities.Photo;

public interface PhotoService {
	List<Photo> findAll();
	List<Photo> findByShotat(LocalDate date);
	void register(MultipartFile image, String date);
	
}
