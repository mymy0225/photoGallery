package com.example.photoGallery.forms;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadImageForm {
	
	@NotNull
	private List<MultipartFile> images;
	
	@NotNull
	private String date;
	
}
