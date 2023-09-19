package com.example.photoGallery.services.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.photoGallery.entities.Photo;
import com.example.photoGallery.repositories.PhotoRepository;
import com.example.photoGallery.services.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService{

	private final PhotoRepository photoRepository;
	
	@Autowired
	private Environment environment;
	
	public PhotoServiceImpl(PhotoRepository photoRepository) {
		this.photoRepository = photoRepository;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Photo> findAll(){
		return photoRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Photo> findByShotat(LocalDate date){
		return photoRepository.findByShotat(date);
	}
	
	@Transactional
	@Override
	public void register(MultipartFile image, String date) {
		if(image.getOriginalFilename().isEmpty()) {
			throw new RuntimeException("ファイルが設定されていません");
		}
		String extension = FilenameUtils.getExtension(image.getOriginalFilename());
		String randomFileName = RandomStringUtils.randomAlphanumeric(20) + "." + extension;
		uploadImage(image, randomFileName);
		LocalDate shotAt = LocalDate.parse(date);
		Photo photo = new Photo(null, randomFileName, shotAt, null, null, null);
		photoRepository.saveAndFlush(photo);
	}
	
	private void uploadImage(MultipartFile multipartFile, String fileName) {
		Path filePath = Paths.get(environment.getProperty("sample.images.imagedir") + fileName);
		try {
			byte[] bytes = multipartFile.getBytes();
			OutputStream stream = Files.newOutputStream(filePath);
			stream.write(bytes);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
