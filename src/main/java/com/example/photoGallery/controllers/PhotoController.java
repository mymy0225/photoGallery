package com.example.photoGallery.controllers;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.photoGallery.entities.Photo;
import com.example.photoGallery.forms.UploadImageForm;
import com.example.photoGallery.services.PhotoService;

@RequestMapping("/photos")
@Controller
public class PhotoController {

	private final PhotoService photoService;
	
	public PhotoController(PhotoService photoService) {
		this.photoService = photoService;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		List<Photo> photos = photoService.findAll();
		Set<String> shotDates = new HashSet<String>();
		for(Photo photo : photos) {
			shotDates.add(photo.getShotat().toString());
		}
		System.out.println(shotDates);
		model.addAttribute("title", "投稿一覧");
		model.addAttribute("main", "photos/index::main");
		model.addAttribute("shotDates", shotDates);
		return "layout/logged_in";
    }
 
	@GetMapping("/{date}")
	public String gallery(
			@PathVariable("date") String date,
			Model model
			) {
		LocalDate thisDate = LocalDate.parse(date);
		System.out.println(thisDate.toString());
//		List<Photo> photos = photoService.findAll();
		List<Photo> photos = photoService.findByShotat(thisDate);
		System.out.println(photos.isEmpty());
//		System.out.println(photos.toString());
		model.addAttribute("title", date);
		model.addAttribute("main", "photos/gallery::main");
		model.addAttribute("photos", photos);
		return "layout/logged_in";
    }
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("title", "投稿の新規作成");
		model.addAttribute("main", "photos/create::main");
		return "layout/logged_in";
		}

	
	@GetMapping("/detail/{a}")
	public String detail(Model model) {
		model.addAttribute("title", "投稿の詳細");
		model.addAttribute("main", "photos/detail::main");
		return "layout/logged_in";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model) {
		model.addAttribute("title", "投稿の編集");
		model.addAttribute("main", "photos/edit::main");
		return "layout/logged_in";
		}
	
	@GetMapping("/upload")
	public String upload(
			@ModelAttribute("upload") UploadImageForm uploadImageForm,
			Model model) {
		model.addAttribute("uploadImageForm", uploadImageForm);
		model.addAttribute("title", "アップロード");
		model.addAttribute("main", "photos/upload::main");
		return "layout/logged_in";
	}
	
	@PostMapping("/upload")
	public String uploadProcess(
			@Valid UploadImageForm uploadImageForm,
			BindingResult bindingResult,
			Model model) {
		if(bindingResult.hasErrors()) {
			return upload(uploadImageForm, model);
			}
		for(MultipartFile image: uploadImageForm.getImages()) {
			photoService.register(
					image,
					uploadImageForm.getDate()
					);
			}
		return "redirect:/photos/";
		}
}