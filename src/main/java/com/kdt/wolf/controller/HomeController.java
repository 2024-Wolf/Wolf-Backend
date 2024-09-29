package com.kdt.wolf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    
	@GetMapping("/adminLogin")
	public String home() {
		return "pages/adminLogin";
	}
	// http://localhost:8080/adminLogin
}