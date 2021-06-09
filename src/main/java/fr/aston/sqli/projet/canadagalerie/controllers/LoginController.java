package fr.aston.sqli.projet.canadagalerie.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

	
	@GetMapping("login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping("works")
	public String getWorks() {
		return "works";
	}
}
