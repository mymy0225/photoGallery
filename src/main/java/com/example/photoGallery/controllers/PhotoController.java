package com.example.photoGallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/photos")
@Controller
public class PhotoController {
	@GetMapping("/")    
    public String index(Model model) {
        model.addAttribute("title", "投稿一覧");
        model.addAttribute("main", "photos/index::main");
        return "layout/logged_in";    
    }
 
    @GetMapping("/create")    
    public String create(Model model) {
        model.addAttribute("title", "投稿の新規作成");
        model.addAttribute("main", "photos/create::main");
        return "layout/logged_in";    
    }
 
    @GetMapping("/detail/{id}")    
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
}