package com.example.tj.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tj.model.SecurityUser;

@RestController
public class HomeController {
	
	@GetMapping("/")
	String home(@AuthenticationPrincipal SecurityUser user) {
		return user.getUsername();
	}
	
}
