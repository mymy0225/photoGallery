package com.example.photoGallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.photoGallery.forms.SignUpForm;
import com.example.photoGallery.services.UserService;

@RequestMapping("/users")
@Controller
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/sign_up")
	public String signUp(
			@ModelAttribute("sign_up") SignUpForm signUpForm,
			Model model
			) {
		model.addAttribute("signUpForm", signUpForm);
		model.addAttribute("title", "サインアップ");
		model.addAttribute("main", "users/sign_up::main");
		return "layout/not_logged_in";
		}
	
	@PostMapping("/sign_up")
	public String signUpProcess(
			@ModelAttribute("sign_up") SignUpForm signUpForm,
			Model model
			) {
		String[] roles = {"ROLE_USER", "ROLE_ADMIN"};
		userService.register(
				signUpForm.getName(),
				signUpForm.getEmail(),
				signUpForm.getPassword(),
				roles);
		return "redirect:/users/login";
	}
	
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("title", "ログイン");
		model.addAttribute("main", "users/login::main");
		return "layout/not_logged_in";
	}

	@GetMapping("/detail/{id}")
	public String detail(Model model) {
		model.addAttribute("title", "ユーザープロフィール");
		model.addAttribute("main", "users/detail::main");
		return "layout/logged_in";
	 }
	
	@GetMapping("/edit/{id}")
	public String edit(Model model) {
		 model.addAttribute("title", "ユーザー情報を編集");
		 model.addAttribute("main", "users/edit::main");
		 return "layout/logged_in";
	}
}